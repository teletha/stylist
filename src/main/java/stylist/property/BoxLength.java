/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import java.util.EnumSet;

import stylist.PropertyDefinition;
import stylist.value.Numeric;
import stylist.value.Unit;
import stylist.value.Vendor;

public class BoxLength extends PropertyDefinition<BoxLength> {

    /** The property name. */
    private final String name;

    /**
     * @param name The property name.
     */
    public BoxLength(String name) {
        this.name = name;
    }

    /**
     * Apply auto value to inline property.
     * 
     * @return
     */
    public BoxLength auto() {
        return inline(new Auto());
    }

    /**
     * The CSS property of an element sets the box space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength size(double size, Unit unit) {
        return size(new Numeric(size, unit));
    }

    /**
     * The CSS property of an element sets the box space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength size(Numeric value) {
        return horizontal(value).vertical(value);
    }

    /**
     * The *-top and *-bottom CSS property of an element sets the margin space required on
     * the top of an element. A negative value is also allowed.
     */
    public BoxLength vertical(double size, Unit unit) {
        return vertical(new Numeric(size, unit));
    }

    /**
     * The *-top and *-bottom CSS property of an element sets the margin space required on
     * the top of an element. A negative value is also allowed.
     */
    public BoxLength vertical(Numeric value) {
        return top(value).bottom(value);
    }

    /**
     * The *-top CSS property of an element sets the margin space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength top(double size, Unit unit) {
        return top(new Numeric(size, unit));
    }

    /**
     * The *-top CSS property of an element sets the margin space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength top(Numeric value) {
        return value(name + "-top", value);
    }

    /**
     * The *-bottom CSS property of an element sets the margin space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength bottom(double size, Unit unit) {
        return bottom(new Numeric(size, unit));
    }

    /**
     * The *-bottom CSS property of an element sets the margin space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength bottom(Numeric value) {
        return value(name + "-bottom", value);
    }

    /**
     * The *-left and *-right CSS property of an element sets the margin space required on
     * the top of an element. A negative value is also allowed.
     */
    public BoxLength horizontal(double size, Unit unit) {
        return horizontal(new Numeric(size, unit));
    }

    /**
     * The *-left and *-right CSS property of an element sets the margin space required on
     * the top of an element. A negative value is also allowed.
     */
    public BoxLength horizontal(Numeric value) {
        return left(value).right(value);
    }

    /**
     * The *-left CSS property of an element sets the margin space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength left(double size, Unit unit) {
        return left(new Numeric(size, unit));
    }

    /**
     * The *-left CSS property of an element sets the margin space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength left(Numeric value) {
        return value(name + "-left", value);
    }

    /**
     * The *-right CSS property of an element sets the margin space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength right(double size, Unit unit) {
        return right(new Numeric(size, unit));
    }

    /**
     * The *-right CSS property of an element sets the margin space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength right(Numeric value) {
        return value(name + "-right", value);
    }

    /**
     * The *-inline-start and *-inline-end CSS property of an element sets the margin space required
     * on the top of an element. A negative value is also allowed.
     */
    public BoxLength inline(double size, Unit unit) {
        return inline(new Numeric(size, unit));
    }

    /**
     * The *-inline-start and *-inline-end CSS property of an element sets the margin space required
     * on the top of an element. A negative value is also allowed.
     */
    public BoxLength inline(Numeric value) {
        return inlineStart(value).inlineEnd(value);
    }

    /**
     * The *-inline-start CSS property of an element sets the space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength inlineStart(double size, Unit unit) {
        return inlineStart(new Numeric(size, unit));
    }

    /**
     * The *-inline-start CSS property of an element sets the space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength inlineStart(Numeric value) {
        return value(name + "-inline-start", value);
    }

    /**
     * The *-inline-end CSS property of an element sets the space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength inlineEnd(double size, Unit unit) {
        return inlineEnd(new Numeric(size, unit));
    }

    /**
     * The *-inline-end CSS property of an element sets the space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength inlineEnd(Numeric value) {
        return value(name + "-inline-end", value);
    }

    /**
     * The *-block-start and *-block-end CSS property of an element sets the margin space required
     * on the top of an element. A negative value is also allowed.
     */
    public BoxLength block(double size, Unit unit) {
        return block(new Numeric(size, unit));
    }

    /**
     * The *-block-start and *-block-end CSS property of an element sets the margin space required
     * on the top of an element. A negative value is also allowed.
     */
    public BoxLength block(Numeric value) {
        return blockStart(value).blockEnd(value);
    }

    /**
     * The *-block-start CSS property of an element sets the space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength blockStart(double size, Unit unit) {
        return blockStart(new Numeric(size, unit));
    }

    /**
     * The *-block-start CSS property of an element sets the space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength blockStart(Numeric value) {
        return value(name + "-block-start", value);
    }

    /**
     * The *-block-end CSS property of an element sets the space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength blockEnd(double size, Unit unit) {
        return blockEnd(new Numeric(size, unit));
    }

    /**
     * The *-block-end CSS property of an element sets the space required on the top of an
     * element. A negative value is also allowed.
     */
    public BoxLength blockEnd(Numeric value) {
        return value(name + "-block-end", value);
    }

    private static class Auto extends Numeric {

        /**
         * Hide
         */
        private Auto() {
            super("calc", "auto");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EnumSet<Vendor> vendors() {
            return NoVendors;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String valueFor(Vendor vendor) {
            return expression;
        }
    }
}