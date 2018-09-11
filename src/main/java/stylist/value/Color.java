/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import static java.lang.Integer.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import stylist.CSSValue;
import stylist.Vendor;
import stylist.util.Formatter;

/**
 * @version 2015/10/01 0:36:38
 */
public class Color extends CSSValue {

    private static final String number = "\\s*([+-]?[\\d\\.]+)[\\s,]*";

    private static final Pattern Code = Pattern.compile("(?:(rgb|hs[lvb])a?)\\s*\\(" + number + number + number + "(?:" + number + ")?\\)");

    /** The frequently used color. */
    public static final Color White = new Color(0, 0, 100);

    /** The frequently used color. */
    public static final Color Whity = new Color(0, 0, 97);

    /** The frequently used color. */
    public static final Color WhiteGray = new Color(0, 0, 93);

    /** The frequently used color. */
    public static final Color Black = new Color(0, 0, 0);

    /** The frequently used color. */
    public static final Color Transparent = new Color(0, 0, 0, 0);

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
     */
    public Color(int hue, int saturation, int lightness) {
        this(hue, saturation, lightness, 1);
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
    public Color(int hue, int saturation, int lightness, double alpha) {
        this.hue = checkRange(0, hue, 360);
        this.saturation = checkRange(0, saturation, 100);
        this.lightness = checkRange(0, lightness, 100);
        this.alpha = checkRange(0, alpha, 1);
    }

    /**
     * Validate number range.
     * 
     * @param from Start point.
     * @param target A target number to check.
     * @param to End point.
     * @return A result.
     */
    private int checkRange(int from, int target, int to) {
        if (from <= target && target <= to) {
            return target;
        } else {
            throw new IllegalArgumentException("Color code[" + target + "] must be between " + from + " and " + to + ".");
        }
    }

    /**
     * Validate number range.
     * 
     * @param from Start point.
     * @param target A target number to check.
     * @param to End point.
     * @return A result.
     */
    private double checkRange(double from, double target, double to) {
        if (from <= target && target <= to) {
            return target;
        } else {
            throw new IllegalArgumentException("Color code[" + target + "] must be between " + from + " and " + to + ".");
        }
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
    public Color opacify(float amount) {
        return new Color(hue, saturation, lightness, alpha + amount);
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

        StringBuilder builder = new StringBuilder("rgb(").append(rounded[0]).append(',').append(rounded[1]).append(',').append(rounded[2]);

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
    public String format(Formatter formatter) {
        return formatter.color().apply(this);
    }

    /**
     * <p>
     * Create Color from hex color code.
     * </p>
     * 
     * @param code A hex color code (accpect "#" prefix). A illegal color code will return
     *            {@value #Transparent}.
     * @return A new color.
     */
    public static Color rgb(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Colot code is null.");
        }

        if (code.startsWith("#")) {
            code = code.substring(1);
        }

        switch (code.length()) {
        case 3:
            String red = "" + code.charAt(0) + code.charAt(0);
            String green = "" + code.charAt(1) + code.charAt(1);
            String blue = "" + code.charAt(2) + code.charAt(2);
            return rgb(parseInt(red, 16), parseInt(green, 16), parseInt(blue, 16));

        case 6:
            return rgb(parseInt(code.substring(0, 2), 16), parseInt(code.substring(2, 4), 16), parseInt(code.substring(4), 16));

        default:
            throw new IllegalArgumentException("[" + code + "] is not color code.");
        }
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
        return rgba(red, green, blue, 1);
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
    public static Color rgba(int red, int green, int blue, double alpha) {
        return color(range(red, 255), range(green, 255), range(blue, 255), alpha);
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
    private static Color color(float red, float green, float blue, double alpha) {
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
     * Helper method to check range.
     * 
     * @param value
     * @return
     */
    private static float range(float value, float max) {
        return value < 0 ? 0 : max < value ? max : value;
    }

    /**
     * Parse any color code.
     * 
     * @param code A color colde (any type).
     * @return A parsed color.
     */
    public static Color of(String code) {
        // normalize code
        if (code == null) code = "";

        Matcher matcher = Code.matcher(code.toLowerCase());

        if (matcher.matches()) {
            boolean hasAlpha = matcher.groupCount() == 6;
            String type = matcher.group(1);
            String first = matcher.group(2);
            String second = matcher.group(3);
            String third = matcher.group(4);
            float alpha = hasAlpha ? Float.parseFloat(matcher.group(5)) : 1;

            switch (type) {
            case "rgb":
                return rgba(integerOrPercentage(first), integerOrPercentage(second), integerOrPercentage(third), alpha);

            case "hsl":
                return new Color(hue(first), percentage(second), percentage(third), alpha);
            default:
                break;
            }
        }
        return null;
    }

    /**
     * Parse as integer or percentage number.
     * 
     * @param number
     * @return
     */
    private static int integerOrPercentage(String number) {
        if (number.endsWith("%")) {
            return (int) (Integer.parseInt(number.substring(0, number.length() - 2)) * 2.55);
        } else {
            return Integer.parseInt(number);
        }
    }

    private static int hue(String number) {
        return 1;
    }

    private static int percentage(String number) {
        return 1;
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
