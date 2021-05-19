/*
 * Copyright (C) 2021 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.StringJoiner;

import icy.manipulator.Icy;
import stylist.value.Color;
import stylist.value.Font;
import stylist.value.Numeric;
import stylist.value.Unit;

@Icy
abstract class ThemeModel {

    final LinkedHashMap<String, String> variables = new LinkedHashMap();

    /**
     * Sepcify the name.
     */
    @Icy.Property
    public abstract String name();

    /**
     * Sepcify the primary color.
     */
    @Icy.Property
    public abstract Color primary();

    @Icy.Intercept("primary")
    private Color parameterizePrimary(Color color) {
        return parameterize("--theme-primary", color);
    }

    /**
     * Sepcify the secondary color.
     */
    @Icy.Property
    public abstract Color secondary();

    @Icy.Intercept("secondary")
    private Color parameterizeSecondary(Color color) {
        return parameterize("--theme-secondary", color);
    }

    /**
     * Sepcify the accent color.
     */
    @Icy.Property
    public abstract Color accent();

    @Icy.Intercept("accent")
    private Color parameterizeAccent(Color color) {
        return parameterize("--theme-accent", color);
    }

    /**
     * Sepcify the font color.
     */
    @Icy.Property
    public abstract Color front();

    @Icy.Intercept("front")
    private Color parameterizeFront(Color color) {
        return parameterize("--theme-front", color);
    }

    /**
     * Sepcify the surface color.
     */
    @Icy.Property
    public abstract Color surface();

    @Icy.Intercept("surface")
    private Color parameterizeSurface(Color color) {
        return parameterize("--theme-surface", color);
    }

    /**
     * Sepcify the background color.
     */
    @Icy.Property
    public abstract Color back();

    @Icy.Intercept("back")
    private Color parameterizeBack(Color color) {
        return parameterize("--theme-back", color);
    }

    /**
     * Sepcify the font color on linkable item.
     */
    @Icy.Property
    public abstract Color link();

    @Icy.Intercept("link")
    private Color parameterizeLink(Color color) {
        return parameterize("--theme-link", color);
    }

    /**
     * Calculate the raw value.
     * 
     * @param color
     * @return
     */
    Color raw(Color color) {
        if (color instanceof ParameterizedColor) {
            return Color.of(variables.get(((ParameterizedColor) color).id));
        } else {
            return color;
        }
    }

    /**
     * Parameterize.
     */
    private Color parameterize(String name, Color color) {
        return new ParameterizedColor(name, color);
    }

    private class ParameterizedColor extends Color {

        /** The identifier. */
        private final String id;

        private ParameterizedColor(String id, Color color) {
            super(color.hue, color.saturation, color.lightness, color.alpha);

            this.id = id;
            variables.put(id, color.toHSL());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color lighten(int amount) {
            String nextID = id(id, "lighten", amount);

            for (Theme theme : Stylist.themes) {
                theme.variables.put(nextID, theme.raw(this).lighten(amount).toHSL());
            }
            return new ParameterizedColor(nextID, Color.of(variables.get(nextID)));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color lighten(Color direction, int amount) {
            String nextID = id(id, "lightenD", amount);

            for (Theme theme : Stylist.themes) {
                theme.variables.put(nextID, theme.raw(this).lighten(theme.raw(direction), amount).toHSL());
            }
            return new ParameterizedColor(nextID, Color.of(variables.get(nextID)));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Color opacify(double amount) {
            String nextID = id(id, "opacify", amount);

            for (Theme theme : Stylist.themes) {
                theme.variables.put(nextID, theme.raw(this).opacify(amount).toHSL());
            }
            return new ParameterizedColor(nextID, super.opacify(amount));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            return "var(" + id + ")";
        }
    }

    /**
     * Specify the base font.
     */
    @Icy.Property
    public abstract Font[] baseFont();

    /**
     * Specify the base font by using system.
     */
    @Icy.Overload("baseFont")
    private Font[] baseBySystem() {
        return new Font[] {
                // Generic
                Font.SystemUI,
                // For Mac
                Font.of("-apple-system"), Font.of("BlinkMacSystemFont"), Font.of("Helvetica Neue"),
                // For Windows
                Font.of("Yu Gothic UI"), Font.of("Verdana"), Font.of("Meiryo"),
                // fallback
                Font.SansSerif};
    }

    /**
     * Specify the condensed font.
     */
    @Icy.Property
    public abstract Font condensedFont();

    @Icy.Intercept("condensedFont")
    private Font parameterizeCondensedFont(Font font) {
        return parameterize("--theme-font-condensed", font);
    }

    /**
     * Specify the title font.
     */
    @Icy.Property
    public abstract Font titleFont();

    @Icy.Intercept("titleFont")
    private Font parameterizeTitleFont(Font font) {
        return parameterize("--theme-font-title", font);
    }

    /**
     * Specify the monospace font.
     */
    @Icy.Property
    public abstract Font[] monoFont();

    /**
     * Specify the monospace font by using system.
     */
    @Icy.Overload("monoFont")
    private Font[] monoBySystem() {
        return new Font[] {
                // For Mac
                Font.of("Menlo"),
                // For Windows
                Font.of("Consolas"),
                // fallback
                Font.Monospace};
    }

    /**
     * Specify the icon font.
     */
    @Icy.Property
    public Font iconFont() {
        return Font.fromGoogle("Material Icons");
    }

    /**
     * Parameterize.
     */
    private Font parameterize(String name, Font font) {
        class ParameterizedFont extends Font {

            /** The identifier. */
            private final String id;

            private ParameterizedFont(String id, Font font) {
                super(font.name, font.uri);

                this.id = id;
                variables.put(id, super.toString());
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String toString() {
                return "var(" + id + ")";
            }
        }
        return new ParameterizedFont(name, font);
    }

    /**
     * Specify the border radius.
     * 
     * @return
     */
    @Icy.Property
    public Numeric borderRadius() {
        return Numeric.Zero;
    }

    @Icy.Overload("borderRadius")
    private Numeric borderRadius(double size, Unit unit) {
        return Numeric.of(size, unit);
    }

    private static String sanitize(Object value) {
        return Objects.toString(value).replaceAll("\\.", "");
    }

    private static String id(Object... values) {
        StringJoiner join = new StringJoiner("-");
        for (Object value : values) {
            join.add(Objects.toString(value));
        }
        return join.toString().replaceAll("\\.", "");
    }
}
