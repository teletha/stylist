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

import static stylist.value.Unit.*;
import static stylist.value.Vendor.*;

import java.util.EnumSet;
import java.util.StringJoiner;

public class Numeric extends CSSValue {

    /** The zero value. */
    public static final Numeric Zero = new Numeric(0, px);

    /** The numerical value. */
    public static final Numeric Top = new Numeric(0, percent);

    /** The numerical value. */
    public static final Numeric Left = Top;

    /** The numerical value. */
    public static final Numeric Center = new Numeric(50, percent);

    /** The numerical value. */
    public static final Numeric Bottom = new Numeric(100, percent);

    /**
     * Represents the largest max-content contribution of the grid items occupying the grid track.
     */
    public static final Numeric MaxContent = new Numeric("", "max-content");

    /**
     * Represents the largest min-content contribution of the grid items occupying the grid track.
     */
    public static final Numeric MinContent = new Numeric("", "min-content");

    /**
     * As min, it represents the largest minimum size (as specified by min-width/min-height) of the
     * grid items occupying the grid track. As max, it is identical to max-content. However, unlike
     * max-content, it allows expansion of the track by the align-content and justify-content
     * property values like normal and stretch.
     */
    public static final Numeric Auto = new Numeric("", "auto");

    /** The numerical value. */
    public static final Numeric Right = Bottom;

    /** The size. */
    public final double size;

    /** The unit. */
    public final Unit unit;

    /** The expression. */
    protected final String expression;

    /** The flag. */
    private final String function;

    /**
     * <p>
     * {@link Numeric} value for zero.
     * </p>
     */
    public Numeric() {
        this(0, px);
    }

    /**
     * <p>
     * {@link Numeric} value as number.
     * </p>
     * 
     * @param size A numeric size.
     */
    public Numeric(double size) {
        this(size, null);
    }

    /**
     * <p>
     * {@link Numeric} value.
     * </p>
     * 
     * @param value A numeric size.
     * @param unit A unit.
     */
    public Numeric(double value, Unit unit) {
        if (unit == deg) {
            value = range(0, value, 360);
        }

        this.size = value;
        this.unit = unit;
        this.expression = value();
        this.function = null;
    }

    /**
     * Helper method to check range.
     * 
     * @param min
     * @param value
     * @param max
     * @return
     */
    private static double range(double min, double value, double max) {
        return value < min ? value + max : max < value ? value - max : value;
    }

    /**
     * <p>
     * {@link Numeric} value for string expression.
     * </p>
     * 
     * @param expression A string expression.
     */
    protected Numeric(String function, String expression) {
        this.size = 0;
        this.unit = null;
        this.expression = expression;
        this.function = function;
    }

    /**
     * <p>
     * Copy {@link Numeric} value.
     * </p>
     * 
     * @param numeric A valut to copy..
     */
    protected Numeric(Numeric numeric) {
        this.size = numeric.size;
        this.unit = numeric.unit;
        this.expression = numeric.expression;
        this.function = numeric.function;
    }

    /**
     * <p>
     * Addition.
     * </p>
     * 
     * @param value
     * @return
     */
    public Numeric plus(double value) {
        return new Numeric(size + value, unit);
    }

    /**
     * Addition.
     */
    public Numeric plus(double value, Unit unit) {
        return plus(new Numeric(value, unit));
    }

    /**
     * <p>
     * Addition.
     * </p>
     * 
     * @param value
     * @return
     */
    public Numeric plus(Numeric value) {
        if (unit == value.unit) {
            return new Numeric(size + value.size, unit);
        } else {
            return new Numeric("calc", expression + " + " + value.expression);
        }
    }

    /**
     * <p>
     * Subtraction.
     * </p>
     * 
     * @param value
     * @return
     */
    public Numeric subtract(double value) {
        return new Numeric(size - value, unit);
    }

    /**
     * Subtraction.
     */
    public Numeric subtract(double value, Unit unit) {
        return subtract(new Numeric(value, unit));
    }

    /**
     * <p>
     * Subtraction.
     * </p>
     * 
     * @param value
     * @return
     */
    public Numeric subtract(Numeric value) {
        if (unit == value.unit) {
            return new Numeric(size - value.size, unit);
        } else {
            return new Numeric("calc", expression + " - " + value.expression);
        }
    }

    /**
     * <p>
     * Multiply.
     * </p>
     * 
     * @param value
     * @return
     */
    public Numeric multiply(double value) {
        return new Numeric(size * value, unit);
    }

    /**
     * Multiply.
     */
    public Numeric multiply(double value, Unit unit) {
        return multiply(new Numeric(value, unit));
    }

    /**
     * <p>
     * Multiply.
     * </p>
     * 
     * @param value
     * @return
     */
    public Numeric multiply(Numeric value) {
        if (unit == value.unit) {
            return new Numeric(size * value.size, unit);
        } else {
            return new Numeric("calc", expression + " * " + value.expression);
        }
    }

    /**
     * <p>
     * Division.
     * </p>
     * 
     * @param value
     * @return
     */
    public Numeric divide(double value) {
        return new Numeric(size / value, unit);
    }

    /**
     * Division.
     */
    public Numeric divide(double value, Unit unit) {
        return divide(new Numeric(value, unit));
    }

    /**
     * <p>
     * Division.
     * </p>
     * 
     * @param value
     * @return
     */
    public Numeric divide(Numeric value) {
        if (unit == value.unit) {
            return new Numeric(size / value.size, unit);
        } else {
            return new Numeric("calc", expression + " / " + value.expression);
        }
    }

    /**
     * <p>
     * </p>
     * 
     * @return
     */
    public Numeric negate() {
        return multiply(new Numeric(-1, unit));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Numeric) {
            Numeric other = (Numeric) obj;

            return size == other.size && unit == other.unit;
        }

        if (obj instanceof String) {
            return toString().equals(obj);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumSet<Vendor> vendors() {
        if (function != null) {
            return EnumSet.of(Standard, Webkit);
        } else {
            return NoVendors;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String valueFor(Vendor vendor) {
        if (function == null) {
            vendor = Standard;
        }
        return vendor + value();
    }

    /**
     * <p>
     * Helper method to stringize this value.
     * </p>
     * 
     * @return A string expression.
     */
    private String value() {
        if (function != null) {
            if (function.equals("")) {
                return expression;
            } else {
                return function + "(" + expression + ")";
            }
        }

        int integer = (int) size;

        if (size == 0) {
            return "0";
        } else if (integer == size) {
            return String.valueOf(integer) + (unit == null ? "" : unit);
        } else {
            return String.valueOf(size) + (unit == null ? "" : unit);
        }
    }

    /**
     * Generic builder.
     * 
     * @param size
     * @return
     */
    public static Numeric of(double size) {
        return new Numeric(size);
    }

    /**
     * Generic builder.
     * 
     * @param size
     * @param unit
     * @return
     */
    public static Numeric of(double size, Unit unit) {
        return new Numeric(size, unit);
    }

    /**
     * The max() CSS function lets you set the largest (most positive) value from a list of
     * comma-separated expressions as the value of a CSS property value. The max() function can be
     * used anywhere a <length>, <frequency>, <angle>, <time>, <percentage>, <number>, or <integer>
     * is allowed.
     * 
     * @param values
     * @return
     */
    public static Numeric max(Numeric... values) {
        return function("max", values);
    }

    /**
     * The min() CSS function lets you set the smallest (most negative) value from a list of
     * comma-separated expressions as the value of a CSS property value. The min() function can be
     * used anywhere a <length>, <frequency>, <angle>, <time>, <percentage>, <number>, or <integer>
     * is allowed.
     * 
     * @param values
     * @return
     */
    public static Numeric min(Numeric... values) {
        return function("min", values);
    }

    /**
     * The minmax() CSS function defines a size range greater than or equal to min and less than or
     * equal to max. It is used with CSS grids.
     * 
     * @return
     */
    public static Numeric minmax(double min, Unit minUnit, double max, Unit maxUnit) {
        return minmax(of(min, minUnit), of(max, maxUnit));
    }

    /**
     * The minmax() CSS function defines a size range greater than or equal to min and less than or
     * equal to max. It is used with CSS grids.
     * 
     * @return
     */
    public static Numeric minmax(Numeric min, Numeric max) {
        return function("minmax", new Numeric[] {min, max});
    }

    private static Numeric function(String name, Numeric[] values) {
        StringJoiner joiner = new StringJoiner(", ");
        for (Numeric value : values) {
            joiner.add(value.expression);
        }
        return new Numeric(name, joiner.toString());
    }
}