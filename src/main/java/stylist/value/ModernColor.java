/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

/**
 * A family of modern CSS color functions which are not expressible as a plain {@link Color}
 * (e.g. {@code oklch()}, {@code color-mix()} or {@code light-dark()}). The rendered expression is
 * emitted verbatim.
 */
public class ModernColor extends CSSValue {

    /** The rendered expression. */
    private final String value;

    /**
     * @param value A rendered expression.
     */
    protected ModernColor(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String valueFor(Vendor vendor) {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ModernColor && value.equals(((ModernColor) obj).value);
    }

    /**
     * Format a numeric channel.
     * 
     * @param value A channel value.
     * @return A formatted number.
     */
    private static String num(double value) {
        if (value == Math.rint(value) && Math.abs(value) < 1e15) {
            return Long.toString((long) value);
        }
        return Double.toString(value).replace("E-", "e-").replace("E", "e");
    }

    /**
     * Format an optional alpha channel.
     * 
     * @param alpha An alpha value or {@link Double#NaN} when unspecified.
     * @return A formatted suffix.
     */
    private static String alphaPart(double alpha) {
        return Double.isNaN(alpha) ? "" : " / " + num(alpha);
    }

    /**
     * The CSS oklch() function.
     */
    public static ModernColor oklch(double lightness, double chroma, double hue) {
        return oklch(lightness, chroma, hue, Double.NaN);
    }

    /**
     * The CSS oklch() function.
     */
    public static ModernColor oklch(double lightness, double chroma, double hue, double alpha) {
        return new ModernColor("oklch(" + num(lightness) + " " + num(chroma) + " " + num(hue) + alphaPart(alpha) + ")");
    }

    /**
     * The CSS oklab() function.
     */
    public static ModernColor oklab(double lightness, double a, double b) {
        return oklab(lightness, a, b, Double.NaN);
    }

    /**
     * The CSS oklab() function.
     */
    public static ModernColor oklab(double lightness, double a, double b, double alpha) {
        return new ModernColor("oklab(" + num(lightness) + " " + num(a) + " " + num(b) + alphaPart(alpha) + ")");
    }

    /**
     * The CSS lch() function.
     */
    public static ModernColor lch(double lightness, double chroma, double hue) {
        return lch(lightness, chroma, hue, Double.NaN);
    }

    /**
     * The CSS lch() function.
     */
    public static ModernColor lch(double lightness, double chroma, double hue, double alpha) {
        return new ModernColor("lch(" + num(lightness) + "% " + num(chroma) + " " + num(hue) + alphaPart(alpha) + ")");
    }

    /**
     * The CSS lab() function.
     */
    public static ModernColor lab(double lightness, double a, double b) {
        return lab(lightness, a, b, Double.NaN);
    }

    /**
     * The CSS lab() function.
     */
    public static ModernColor lab(double lightness, double a, double b, double alpha) {
        return new ModernColor("lab(" + num(lightness) + "% " + num(a) + " " + num(b) + alphaPart(alpha) + ")");
    }

    /**
     * The CSS hwb() function.
     */
    public static ModernColor hwb(double hue, double whiteness, double blackness) {
        return hwb(hue, whiteness, blackness, Double.NaN);
    }

    /**
     * The CSS hwb() function.
     */
    public static ModernColor hwb(double hue, double whiteness, double blackness, double alpha) {
        return new ModernColor("hwb(" + num(hue) + " " + num(whiteness) + "% " + num(blackness) + "%" + alphaPart(alpha) + ")");
    }

    /**
     * The CSS color() function.
     */
    public static ModernColor color(String colorSpace, String channels) {
        return new ModernColor("color(" + colorSpace + " " + channels + ")");
    }

    /**
     * The CSS color() function.
     */
    public static ModernColor color(String colorSpace, double... channels) {
        StringBuilder builder = new StringBuilder("color(").append(colorSpace);
        for (double channel : channels) {
            builder.append(' ').append(num(channel));
        }
        return new ModernColor(builder.append(')').toString());
    }

    /**
     * The CSS color-mix() function.
     */
    public static ModernColor colorMix(CSSValue first, CSSValue second) {
        return colorMix("srgb", first, second);
    }

    /**
     * The CSS color-mix() function.
     */
    public static ModernColor colorMix(String colorSpace, CSSValue first, CSSValue second) {
        return new ModernColor("color-mix(in " + colorSpace + ", " + first + ", " + second + ")");
    }

    /**
     * The CSS color-mix() function with weights.
     */
    public static ModernColor colorMix(String colorSpace, CSSValue first, double firstWeight, CSSValue second, double secondWeight) {
        return new ModernColor("color-mix(in " + colorSpace + ", " + first + " " + num(firstWeight) + "%, " + second + " " + num(secondWeight) + "%)");
    }

    /**
     * The CSS light-dark() function.
     */
    public static ModernColor lightDark(CSSValue light, CSSValue dark) {
        return new ModernColor("light-dark(" + light + ", " + dark + ")");
    }
}