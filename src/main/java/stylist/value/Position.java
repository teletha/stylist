/*
 * Copyright (C) 2020 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import java.util.EnumSet;

import stylist.CSSValue;
import stylist.Vendor;

/**
 * @version 2018/08/30 18:22:10
 */
public class Position extends CSSValue {

    /** The x-axis. */
    public final Numeric x;

    /** The y-axis. */
    public final Numeric y;

    /**
     * <p>
     * Create position.
     * </p>
     * 
     * @param position A x and y axis.
     */
    public Position(Numeric position) {
        this(position, position);
    }

    /**
     * <p>
     * Create position.
     * </p>
     * 
     * @param x A x-axis.
     * @param y A y-axis.
     */
    public Position(Numeric x, Numeric y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumSet<Vendor> vendors() {
        EnumSet<Vendor> set = EnumSet.noneOf(Vendor.class);

        if (x != null) {
            set.addAll(x.vendors());
        }

        if (y != null) {
            set.addAll(y.vendors());
        }
        return set;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String valueFor(Vendor vendor) {
        if (x.equals(y)) {
            return x.valueFor(vendor);
        } else {
            return x.valueFor(vendor) + " " + y.valueFor(vendor);
        }
    }
}