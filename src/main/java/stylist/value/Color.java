/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import stylist.CSSValue;
import stylist.Stylist;
import stylist.Vendor;

/**
 * @version 2018/09/11 15:53:19
 */
public class Color extends CSSValue {

    private static final String number = "\\s*([+-]?[\\d\\.%]+)[\\s,]*";

    private static final Pattern ColorSpace = Pattern
            .compile("\\s*(?:(rgb|hs[lvb])a?)\\s*\\(" + number + number + number + "(?:" + number + ")?\\)\\s*");

    /** The frequently used color. */
    public static final Color White = hsl(0, 0, 100);

    /** The frequently used color. */
    public static final Color Whity = hsl(0, 0, 97);

    /** The frequently used color. */
    public static final Color WhiteGray = hsl(0, 0, 93);

    /** The frequently used color. */
    public static final Color Black = hsl(0, 0, 0);

    /** The frequently used color. */
    public static final Color Transparent = hsl(0, 0, 0, 0);

    /** The built-in color. */
    public static final Color Inherit = new BuiltinColor("inherit");

    /** The built-in color. */
    public static final Color Initial = new BuiltinColor("initial");

    /** The built-in color. */
    public static final Color Unset = new BuiltinColor("unset");

    private static class BuiltinColor extends Color {

        private final String value;

        public BuiltinColor(String value) {
            super(1, 1, 1, 1);
            this.value = value;
        }

        @Override
        public String toHSL() {
            return value;
        }

        @Override
        public String toRGB() {
            return value;
        }
    }

    /**
     * The attribute of a visual sensation according to which an area appears to be similar to one
     * of the perceived colors: red, yellow, green, and blue, or to a combination of two of them .
     */
    public final int hue;

    /**
     * The colorfulness of a stimulus relative to its own brightness.
     */
    public final int saturation;

    /**
     * The brightness relative to the brightness of a similarly illuminated white.
     */
    public final int lightness;

    /**
     * The transparency.
     */
    public final double alpha;

    /**
     * <p>
     * Create new color.
     * </p>
     * 
     * @param hue The attribute of a visual sensation according to which an area appears to be
     *            similar to one of the perceived colors: red, yellow, green, and blue, or to a
     *            combination of two of them .
     * @param saturation The colorfulness of a stimulus relative to its own brightness.
     * @param lightness The brightness relative to the brightness of a similarly illuminated white.
     * @param alpha The transparency.
     */
    protected Color(int hue, int saturation, int lightness, double alpha) {
        this.hue = checkRange(0, hue, 360);
        this.saturation = checkRange(0, saturation, 100);
        this.lightness = checkRange(0, lightness, 100);
        this.alpha = checkRange(0, alpha, 1d);
    }

    /**
     * Validate number range.
     * 
     * @param from Start point.
     * @param target A target number to check.
     * @param to End point.
     * @return A result.
     */
    private static int checkRange(int from, int target, int to) {
        if (target < from) {
            target = from;
        }
        if (to < target) {
            target = to;
        }
        return target;
    }

    /**
     * Validate number range.
     * 
     * @param from Start point.
     * @param target A target number to check.
     * @param to End point.
     * @return A result.
     */
    private static double checkRange(double from, double target, double to) {
        if (target < from) {
            target = from;
        }
        if (to < target) {
            target = to;
        }
        return target;
    }

    /**
     * <p>
     * Changes the hue of a color while retaining the lightness and saturation.
     * </p>
     * 
     * @param amount An amount of hue.
     * @return A new color.
     */
    public Color adjustHue(int amount) {
        return new Color(hue + amount, saturation, lightness, alpha);
    }

    /**
     * <p>
     * Makes a color more saturated.
     * </p>
     * 
     * @param amount An amount of saturation.
     * @return A new color.
     */
    public Color saturate(int amount) {
        return new Color(hue, saturation + amount, lightness, alpha);
    }

    /**
     * <p>
     * Makes a color lighter or darker. Positive value makes this color lighter, negative value
     * makes this color darker.
     * </p>
     * 
     * @param amount An amount of lightness.
     * @return A new color.
     */
    public Color lighten(int amount) {
        return new Color(hue, saturation, lightness + amount, alpha);
    }

    /**
     * <p>
     * Makes a color more opaque.
     * </p>
     * 
     * @param amount An amount of transparency.
     * @return A new color.
     */
    public Color opacify(double amount) {
        return new Color(hue, saturation, lightness, new BigDecimal(alpha).add(new BigDecimal(amount)).doubleValue());
    }

    /**
     * <p>
     * Converts a color to grayscale. This is qeuivalent to the following method call:
     * </p>
     * <pre>
     * color.saturate(-100);
     * </pre>
     * 
     * @return A grascaled color.
     */
    public Color grayscale() {
        return saturate(-100);
    }

    /**
     * <p>
     * Returns the complement of a color. This is qeuivalent to the following method call:
     * </p>
     * <pre>
     * color.adjustHue(180);
     * </pre>
     * 
     * @return A grascaled color.
     */
    public Color complement() {
        return adjustHue(180);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return hue + (saturation << 8) + (lightness << 16) + ((int) (alpha * 100) << 24);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Color == false) {
            return false;
        }

        Color other = (Color) obj;
        return hue == other.hue && saturation == other.saturation && lightness == other.lightness && alpha == other.alpha;
    }

    /**
     * Compute HSL expression.
     * 
     * @return
     */
    public String toHSL() {
        if (alpha == 1) {
            return "hsl(" + hue + "," + saturation + "%," + lightness + "%)";
        } else {
            return "hsla(" + hue + "," + saturation + "%," + lightness + "%," + (alpha == 0 ? "0" : alpha) + ")";
        }
    }

    /**
     * Compute RGB expression.
     * 
     * @return
     */
    public String toRGB() {
        double max = 2.55 * (lightness + (lightness < 50 ? lightness : 100 - lightness) * (saturation / 100d));
        double min = 2.55 * (lightness - (lightness < 50 ? lightness : 100 - lightness) * (saturation / 100d));
        double diff = max - min;
        double[] rgb;

        if (0 <= hue && hue < 60) {
            rgb = new double[] {max, min + (max - min) * (hue / 60d), min};
        } else if (60 <= hue && hue < 120) {
            rgb = new double[] {((120 - hue) / 60d) * diff + min, max, min};
        } else if (120 <= hue && hue < 180) {
            rgb = new double[] {min, max, ((hue - 120) / 60d) * diff + min};
        } else if (180 <= hue && hue < 240) {
            rgb = new double[] {min, ((240 - hue) / 60d) * diff + min, max};
        } else if (240 <= hue && hue < 300) {
            rgb = new double[] {((hue - 240) / 60d) * diff + min, min, max};
        } else {
            rgb = new double[] {max, min, ((360 - hue) / 60d) * diff + min};
        }

        long[] rounded = new long[3];

        for (int i = 0; i < rgb.length; i++) {
            rounded[i] = Math.round(rgb[i]);
        }

        StringBuilder builder = new StringBuilder(alpha == 1 ? "rgb" : "rgba").append("(")
                .append(rounded[0])
                .append(',')
                .append(rounded[1])
                .append(',')
                .append(rounded[2]);

        if (alpha != 1) {
            builder.append(',').append(formatDecimal(alpha));
        }
        builder.append(")");

        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String valueFor(Vendor vendor) {
        if (alpha == 0) {
            return "transparent";
        } else if (alpha == 1) {
            if (hue == 0 && saturation == 0) {
                if (lightness == 0) {
                    return "black";
                } else if (lightness == 100) {
                    return "white";
                }
            }
        }
        return toHSL();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(Stylist formatter) {
        return formatter.color().apply(this);
    }

    /**
     * <p>
     * Create new color.
     * </p>
     * 
     * @param hue The attribute of a visual sensation according to which an area appears to be
     *            similar to one of the perceived colors: red, yellow, green, and blue, or to a
     *            combination of two of them .
     * @param saturation The colorfulness of a stimulus relative to its own brightness.
     * @param lightness The brightness relative to the brightness of a similarly illuminated white.
     */
    public static Color hsl(int hue, int saturation, int lightness) {
        return hsl(hue, saturation, lightness, 1);
    }

    /**
     * <p>
     * Create new color.
     * </p>
     * 
     * @param hue The attribute of a visual sensation according to which an area appears to be
     *            similar to one of the perceived colors: red, yellow, green, and blue, or to a
     *            combination of two of them .
     * @param saturation The colorfulness of a stimulus relative to its own brightness.
     * @param lightness The brightness relative to the brightness of a similarly illuminated white.
     * @param alpha The transparency.
     */
    public static Color hsl(int hue, int saturation, int lightness, double alpha) {
        return new Color(hue, saturation, lightness, alpha);
    }

    /**
     * <p>
     * Create Color without alpha channel.
     * </p>
     * 
     * @param red A red component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param green A green component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param blue A blue component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @return A new color.
     */
    public static Color rgb(int red, int green, int blue) {
        return rgb(red, green, blue, 1d);
    }

    /**
     * <p>
     * Create Color with alpha channel.
     * </p>
     * 
     * @param red A red component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param green A green component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param blue A blue component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @return A new color.
     */
    public static Color rgb(int red, int green, int blue, double alpha) {
        return rgb2hsl(checkRange(0, red, 255), checkRange(0, green, 255), checkRange(0, blue, 255), alpha);
    }

    /**
     * <p>
     * Create Color with alpha channel.
     * </p>
     * 
     * @param red A red component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param green A green component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param blue A blue component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @return A new color.
     */
    private static Color rgb2hsl(float red, float green, float blue, double alpha) {
        red = red / 255;
        green = green / 255;
        blue = blue / 255;

        float max = Math.max(Math.max(red, green), blue);
        float min = Math.min(Math.min(red, green), blue);
        float diff = max - min;
        float sum = max + min;

        float hue = 0;
        float satuation = 0;
        float lightness = (max + min) / 2;

        if (diff != 0) {
            if (max == red) {
                hue = 60 * (green - blue) / diff;
            } else if (max == green) {
                hue = 60 * (blue - red) / diff + 120;
            } else if (max == blue) {
                hue = 60 * (red - green) / diff + 240;
            }
        }

        if (hue < 0) {
            hue += 360;
        }

        if (diff != 0) {
            satuation = lightness < 0.5 ? diff / sum : diff / (2 - sum);
        }
        return new Color(Math.round(hue), Math.round(satuation * 100), Math.round(lightness * 100), alpha);
    }

    /**
     * Parse any color code.
     * 
     * @param code A color colde (any type).
     * @return A parsed color.
     */
    public static Color of(String color) {
        if (color == null) color = "";
        color = color.toLowerCase().trim();

        Matcher matcher = ColorSpace.matcher(color);

        if (matcher.matches()) {
            String type = matcher.group(1);
            String first = matcher.group(2);
            String second = matcher.group(3);
            String third = matcher.group(4);
            String fouth = matcher.group(5);
            double alpha = fouth != null ? decimalOrPercentage(fouth) : 1;

            switch (type) {
            case "rgb":
                return rgb(integerOrPercentageAs255(first), integerOrPercentageAs255(second), integerOrPercentageAs255(third), alpha);

            case "hsl":
                return hsl(Integer.parseInt(first), percentage(second), percentage(third), alpha);

            default:
                throw new IllegalArgumentException("Color code [" + color + "] is invalid.");
            }
        }

        if (color.startsWith("#")) {
            color = color.substring(1);
            String red, green, blue, alpha = "FF";

            switch (color.length()) {
            case 4:
                alpha = color.substring(3, 4) + color.substring(3, 4);

            case 3:
                red = color.substring(0, 1) + color.substring(0, 1);
                green = color.substring(1, 2) + color.substring(1, 2);
                blue = color.substring(2, 3) + color.substring(2, 3);
                break;

            case 8:
                alpha = color.substring(6, 8);

            case 6:
                red = color.substring(0, 2);
                green = color.substring(2, 4);
                blue = color.substring(4, 6);
                break;

            default:
                throw new IllegalArgumentException("Color code [" + color + "] is invalid.");
            }
            return rgb(Integer.parseInt(red, 16), Integer.parseInt(green, 16), Integer
                    .parseInt(blue, 16), Integer.parseInt(alpha, 16) / 255d);
        }
        throw new IllegalArgumentException("Color code [" + color + "] is invalid.");
    }

    /**
     * Parse as percentage number.
     * 
     * @param number
     * @return
     */
    private static int percentage(String number) {
        if (number.equals("0")) {
            return 0;
        } else if (number.endsWith("%")) {
            return Integer.parseInt(number.substring(0, number.length() - 1));
        } else {
            throw new IllegalArgumentException("Color code [" + number + "] is invalid.");
        }
    }

    /**
     * Parse as integer or percentage number.
     * 
     * @param number
     * @return
     */
    private static int integerOrPercentageAs255(String number) {
        if (number.endsWith("%")) {
            return (int) (Integer.parseInt(number.substring(0, number.length() - 1)) * 2.55);
        } else {
            return Integer.parseInt(number);
        }
    }

    /**
     * Parse as decimal or percentage number.
     * 
     * @param number
     * @return
     */
    private static double decimalOrPercentage(String number) {
        if (number.endsWith("%")) {
            return Integer.parseInt(number.substring(0, number.length() - 1)) / 100d;
        } else {
            return Double.parseDouble(number);
        }
    }

    /**
     * Format decimal.
     * 
     * @param decimal
     * @return
     */
    private static String formatDecimal(double decimal) {
        int number = (int) decimal;

        if (decimal == number) {
            return Integer.toString(number);
        } else {
            String text = Double.toString(decimal);

            if (text.startsWith("0.")) {
                return text.substring(1);
            } else {
                return text;
            }
        }
    }
}
