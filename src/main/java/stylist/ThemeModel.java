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

import icy.manipulator.Icy;
import stylist.value.Color;
import stylist.value.Font;

@Icy
interface ThemeModel {

    /**
     * s Sepcify the primary color.
     */
    @Icy.Property
    Color primary();

    @Icy.Intercept("primary")
    private Color parameterizePrimary(Color color) {
        return parameterize("theme-primary", color);
    }

    /**
     * Sepcify the secondary color.
     */
    @Icy.Property
    Color secondary();

    @Icy.Intercept("secondary")
    private Color parameterizeSecondary(Color color) {
        return parameterize("theme-secondary", color);
    }

    /**
     * Sepcify the accent color.
     */
    @Icy.Property
    Color accent();

    @Icy.Intercept("accent")
    private Color parameterizeAccent(Color color) {
        return parameterize("theme-accent", color);
    }

    /**
     * Sepcify the font color.
     */
    @Icy.Property
    Color front();

    @Icy.Intercept("front")
    private Color parameterizeFront(Color color) {
        return parameterize("theme-front", color);
    }

    /**
     * Sepcify the background color.
     */
    @Icy.Property
    Color back();

    @Icy.Intercept("back")
    private Color parameterizeBack(Color color) {
        return parameterize("theme-back", color);
    }

    /**
     * Sepcify the font color on linkable item.
     */
    @Icy.Property
    Color link();

    @Icy.Intercept("link")
    private Color parameterizeLink(Color color) {
        return parameterize("theme-link", color);
    }

    /**
     * Parameterize.
     */
    private static Color parameterize(String name, Color color) {
        class ParameterizedColor extends Color {

            /** The identifier. */
            private final String id;

            private ParameterizedColor(String id, Color color) {
                super(color.hue, color.saturation, color.lightness, color.alpha);

                this.id = id;
                Stylist.variables.put(id, super.valueFor(Vendor.Standard));
            }

            /**
             * {@inheritDoc}
             */
            @Override
            protected String valueFor(Vendor vendor) {
                return "var(--" + id + ")";
            }
        }
        return new ParameterizedColor(name, color);
    }

    /**
     * Specify the base font.
     */
    @Icy.Property
    Font[] baseFont();

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
    Font condensedFont();

    @Icy.Intercept("condensedFont")
    private Font parameterizeCondensedFont(Font font) {
        return parameterize("theme-font-condensed", font);
    }

    /**
     * Specify the title font.
     */
    @Icy.Property
    Font titleFont();

    @Icy.Intercept("titleFont")
    private Font parameterizeTitleFont(Font font) {
        return parameterize("theme-font-title", font);
    }

    /**
     * Specify the monospace font.
     */
    @Icy.Property
    Font[] monoFont();

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
    default Font iconFont() {
        return Font.fromGoogle("Material Icons");
    }

    /**
     * Parameterize.
     */
    private static Font parameterize(String name, Font font) {
        class ParameterizedFont extends Font {

            /** The identifier. */
            private final String id;

            private ParameterizedFont(String id, Font font) {
                super(font.name, font.uri);

                this.id = id;
                Stylist.variables.put(id, super.toString());
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String toString() {
                return "var(--" + id + ")";
            }
        }
        return new ParameterizedFont(name, font);
    }
}
