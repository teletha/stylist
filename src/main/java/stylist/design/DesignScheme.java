/*
 * Copyright (C) 2021 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.design;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import kiss.I;
import kiss.Managed;
import kiss.Singleton;
import stylist.util.Properties;
import stylist.value.CSSValue;
import stylist.value.Color;
import stylist.value.Font;
import stylist.value.FontSet;
import stylist.value.Numeric;
import stylist.value.Vendor;

@Managed(Singleton.class)
public abstract class DesignScheme {

    /** The theme manager. */
    public final List<DefinedTheme> themes = new ArrayList();

    /**
     * Initialization.
     */
    protected DesignScheme() {
        List<Field> fields = I.signal(getClass().getFields())
                .take(field -> CSSValue.class.isAssignableFrom(field.getType()) || field.getType() == String.class)
                .toList();

        for (Method method : getClass().getDeclaredMethods()) {
            Theme theme = method.getAnnotation(Theme.class);
            if (theme != null) {
                themes.add(new DefinedTheme(method, theme, fields));
            }
        }

        // replace by variable value
        for (Field field : fields) {
            try {
                String name = field.getName();

                if (field.getType() == Color.class) {
                    field.set(this, new VariableColor(name));
                } else if (field.getType() == Numeric.class) {
                    field.set(this, new VariableNumeric(name));
                } else if (field.getType() == FontSet.class) {
                    field.set(this, new VariableFontSet(name));
                } else if (field.getType() == String.class) {
                    field.set(this, "var(--" + name + ")");
                }
            } catch (Exception e) {
                throw I.quiet(e);
            }
        }
    }

    /**
     * Utility to define font family.
     * 
     * @param fontNames
     * @return
     */
    protected final FontSet font(String... fontNames) {
        FontSet set = new FontSet();
        for (String name : fontNames) {
            set.local(name);
        }
        return set;
    }

    /**
     * Utility to define font family.
     * 
     * @param fontNames
     * @return
     */
    protected final FontSet fontFromGoogle(String... fontNames) {
        FontSet set = new FontSet();
        for (String name : fontNames) {
            set.fromGoogle(name);
        }
        return set;
    }

    /**
     * Utility to define font family.
     * 
     * @return
     */
    protected final FontSet fontSystemBase() {
        return new FontSet()
                // Generic
                .local(Font.SystemUI)
                // For Mac
                .local("-apple-system")
                .local("BlinkMacSystemFont")
                .local("Helvetica Neue")
                // For Windows
                .local("Yu Gothic UI")
                .local("Verdana")
                .local("Meiryo")
                // fallback
                .local(Font.SansSerif);
    }

    /**
     * Utility to define font family.
     * 
     * @return
     */
    protected final FontSet fontSystemMono() {
        return new FontSet()
                // For Mac
                .local("Menlo")
                // For Windows
                .local("Consolas")
                // fallback
                .local(Font.Monospace);
    }

    /**
     * @param name A name of the target theme.
     */
    Properties variablesFor(String name) {
        for (DefinedTheme theme : themes) {
            if (theme.name.equals(name)) {
                return theme.variables;
            }
        }
        throw new Error("The theme [" + name + "] is not found.");
    }

    /**
     * Sanitize the value for CSS Variable.
     * 
     * @param value
     * @return
     */
    private static String sanitize(Object value) {
        return Objects.toString(value).replaceAll("\\.", "");
    }

    /**
     * Internal theme.
     */
    public class DefinedTheme {

        /** The name of this theme. */
        public final String name;

        /** The theme type. */
        public final boolean isMain;

        /** The variable manager. */
        public final Properties variables = new Properties();

        /**
         * @param method
         * @param theme
         */
        private DefinedTheme(Method method, Theme theme, List<Field> fields) {
            this.name = method.getName();
            this.isMain = theme.main();

            try {
                // apply
                method.setAccessible(true);
                method.invoke(DesignScheme.this);

                for (Field field : fields) {
                    String name = field.getName();
                    Object value = field.get(DesignScheme.this);

                    // collect
                    if (value instanceof CSSValue) {
                        variables.set(name, (CSSValue) value);
                    } else if (value instanceof String) {
                        variables.set(name, CSSValue.of(value));
                    }

                    // clear
                    field.set(DesignScheme.this, null);
                }
            } catch (Exception e) {
                throw I.quiet(e);
            }
        }

        private Color raw(Color color) {
            if (color instanceof VariableColor) {
                return (Color) variables.get(((VariableColor) color).name).exact();
            } else {
                return color;
            }
        }
    }

    /**
     * 
     */
    protected static class CSSVariable<T> extends CSSValue {

        /** The variable name. */
        private final String name;

        /**
         * @param name
         */
        private CSSVariable(String name) {
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String valueFor(Vendor vendor) {
            return "var(--" + name + ")";
        }
    }

    /**
     * 
     */
    private class VariableColor extends Color {

        private final String name;

        /**
         * Define the base variable color.
         * 
         * @param name
         */
        private VariableColor(String name) {
            super(0, 0, 0, 0);
            this.name = name;
        }

        /**
         * Define the derivative variable color.
         */
        private VariableColor(String base, String suffix, BiFunction<DefinedTheme, Color, Color> operation) {
            super(0, 0, 0, 0);
            this.name = base + "-" + suffix;

            for (DefinedTheme theme : themes) {
                theme.variables.get(base).to(raw -> {
                    theme.variables.set(name, operation.apply(theme, (Color) raw));
                });
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color adjustHue(int amount) {
            return new VariableColor(name, "adjustHue-" + sanitize(amount), (theme, color) -> color.adjustHue(amount));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color saturate(int amount) {
            return new VariableColor(name, "saturate-" + sanitize(amount), (theme, color) -> color.saturate(amount));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color lighten(int amount) {
            return new VariableColor(name, "lighten-" + sanitize(amount), (theme, color) -> color.lighten(amount));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color lighten(Color direction, int amount) {
            return new VariableColor(name, "lightenD-" + sanitize(amount), (theme, color) -> color.lighten(theme.raw(direction), amount));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color opacify(double amount) {
            return new VariableColor(name, "opacify-" + sanitize(amount), (theme, color) -> color.opacify(amount));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String valueFor(Vendor vendor) {
            return "var(--" + name + ")";
        }
    }

    /**
     * 
     */
    private class VariableNumeric extends Numeric {

        /** The name of this variable. */
        private final String name;

        /**
         * 
         */
        private VariableNumeric(String name) {
            super();
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String valueFor(Vendor vendor) {
            return "var(--" + name + ")";
        }
    }

    /**
     * 
     */
    private class VariableFontSet extends FontSet {

        /** The variable name. */
        private final String name;

        /**
         */
        public VariableFontSet(String name) {
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String valueFor(Vendor vendor) {
            return "var(--" + name + ")";
        }
    }
}
