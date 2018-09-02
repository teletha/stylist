/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import java.util.EnumSet;
import java.util.Objects;

/**
 * @version 2018/09/02 11:07:38
 */
public abstract class CSSValue {

    /** For reuse. */
    protected static final EnumSet<Vendor> NoVendors = EnumSet.noneOf(Vendor.class);

    /**
     * <p>
     * Detect dependent vendors.
     * </p>
     * 
     * @return
     */
    protected EnumSet<Vendor> vendors() {
        return NoVendors;
    }

    /**
     * <p>
     * Write vendor specific value. Returning <code>null</code> or empty string, {@link StyleRule}
     * will omit the target property.
     * </p>
     * 
     * @param vendor A target vendor.
     */
    protected abstract String valueFor(Vendor vendor);

    /**
     * Create vendor fixed value.
     * 
     * @param vendor
     * @return
     */
    final CSSValue fix(Vendor vendor) {
        return new VendorFixed(vendor, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return valueFor(Vendor.Standard);
    }

    /**
     * Create simple text {@link CSSValue}.
     * 
     * @param value
     * @return
     */
    public static CSSValue of(String value) {
        return new Value(value);
    }

    /**
     * Create simple number {@link CSSValue}.
     * 
     * @param value
     * @return
     */
    public static CSSValue of(Number value) {
        return new Digit(value);
    }

    /**
     * @version 2018/09/02 11:05:46
     */
    private static class Value extends CSSValue {

        /** The actual value. */
        private final String value;

        /**
         * @param value
         */
        private Value(String value) {
            this.value = Objects.requireNonNull(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            return value;
        }
    }

    /**
     * @version 2018/09/02 11:05:46
     */
    private static class Digit extends CSSValue {

        /** The actual value. */
        private final Number value;

        /**
         * @param value
         */
        private Digit(Number value) {
            this.value = Objects.requireNonNull(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            if (value.intValue() == value.doubleValue()) {
                return String.valueOf(value.intValue());
            } else {
                return value.toString();
            }
        }
    }

    /**
     * @version 2018/09/02 11:30:14
     */
    private static class VendorFixed extends CSSValue {

        /** The specified vendor. */
        private final Vendor vendor;

        /** The actual value. */
        private final CSSValue value;

        /**
         * @param vendor
         * @param value
         */
        private VendorFixed(Vendor vendor, CSSValue value) {
            this.vendor = vendor;
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            return value.valueFor(this.vendor);
        }
    }
}
