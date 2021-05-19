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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import kiss.I;
import kiss.Managed;
import kiss.Singleton;
import kiss.Ⅱ;
import stylist.CSSValue;
import stylist.Vendor;
import stylist.value.Color;
import stylist.value.Font;
import stylist.value.Numeric;

@Managed(Singleton.class)
public abstract class DesignScheme {

    public Color primary;

    public Color secandary;

    public Color accent;

    public Color text;

    public Color link;

    public Color back;

    public Color surface;

    public Font base;

    public Font title;

    public Font condensed;

    public Font mono;

    public Font icon;

    public Numeric font;

    public Numeric line;

    public Numeric border;

    public Numeric radius;

    /** The theme manager. */
    final List<UserTheme> themes = new ArrayList();

    /**
     * Initialization.
     */
    protected DesignScheme() {
        List<Field> fields = I.signal(getClass().getFields()).take(field -> CSSValue.class.isAssignableFrom(field.getType())).toList();

        for (Method method : getClass().getDeclaredMethods()) {
            Theme theme = method.getAnnotation(Theme.class);
            if (theme != null) {
                themes.add(new UserTheme(method, theme, fields));
            }
        }

        // replace by variable value
        for (Field field : fields) {
            try {
                String name = field.getName();

                if (field.getType() == Color.class) {
                    field.set(this, new VariableColor(name, I.signal(themes).map(t -> I.pair(t, (Color) t.variables.get(name))).toList()));
                }
            } catch (Exception e) {
                throw I.quiet(e);
            }
        }
    }

    private static String sanitize(Object value) {
        return Objects.toString(value).replaceAll("\\.", "");
    }

    /**
     * Internal theme.
     */
    class UserTheme {

        /** The name of this theme. */
        final String name;

        /** The configurator. */
        final Method method;

        /** The theme type. */
        final boolean isMain;

        /** The variable manager. */
        final Map<String, CSSValue> variables = new HashMap();

        /**
         * @param method
         * @param theme
         */
        UserTheme(Method method, Theme theme, List<Field> fields) {
            this.name = method.getName();
            this.method = method;
            this.isMain = theme.main();

            try {
                // apply
                method.invoke(DesignScheme.this);

                for (Field field : fields) {
                    CSSValue value = (CSSValue) field.get(DesignScheme.this);

                    // collect
                    if (value != null) {
                        variables.put(field.getName(), value);
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
                return (Color) variables.get(((VariableColor) color).name);
            } else {
                return color;
            }
        }
    }

    /**
     * 
     */
    class CSSVariable extends CSSValue {

        /** The name of this variable. */
        private final String name;

        /**
         * @param name
         */
        CSSVariable(String name) {
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            return "var(" + name + ")";
        }
    }

    private static class VariableColor extends Color {

        private final String name;

        private final List<Ⅱ<UserTheme, Color>> original;

        /**
         * 
         */
        private VariableColor(String name, List<Ⅱ<UserTheme, Color>> original) {
            super(0, 0, 0, 0);

            this.name = name;
            this.original = original;

            for (Ⅱ<UserTheme, Color> o : original) {
                o.ⅰ.variables.put(name, o.ⅱ);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color lighten(int amount) {
            return new VariableColor(name + "-lighten" + sanitize(amount), I.signal(original)
                    .map(o -> o.map((t, c) -> I.pair(t, c.lighten(amount))))
                    .toList());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color lighten(Color direction, int amount) {
            return new VariableColor(name + "-lightenD" + sanitize(amount), I.signal(original)
                    .map(o -> o.map((t, c) -> I.pair(t, c.lighten(t.raw(direction), amount))))
                    .toList());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color opacify(double amount) {
            return new VariableColor(name + "-opacify-" + sanitize(amount), I.signal(original)
                    .map(o -> o.map((t, c) -> I.pair(t, c.opacify(amount))))
                    .toList());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            return "var(--" + name + ")";
        }
    }
}
