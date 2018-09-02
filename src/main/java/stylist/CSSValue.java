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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import kiss.I;

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
     * Test whether the specified value matches this {@link CSSValue}.
     * 
     * @param value
     * @return
     */
    public boolean match(String value) {
        return toString().equalsIgnoreCase(value);
    }

    /**
     * Create joined {@link CSSValue}.
     * 
     * @param separator
     * @param value
     * @return
     */
    public final CSSValue join(String separator, CSSValue value) {
        return new Joined(this, value, separator);
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
    public static CSSValue of(Object value) {
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

    public static CSSValue of(String separator, List<CSSValue> values) {
        return new Concated(separator);
    }

    /**
     * @version 2018/09/02 11:05:46
     */
    private static class Value extends CSSValue {

        /** The actual value. */
        private final Object value;

        /**
         * @param value
         */
        private Value(Object value) {
            this.value = Objects.requireNonNull(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            return value.toString();
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

    /**
     * @version 2018/09/02 12:23:33
     */
    private static class Joined extends CSSValue {

        /** The before value. */
        private final CSSValue before;

        /** The after value. */
        private final CSSValue after;

        /** The separator. */
        private final String separator;

        /**
         * @param before
         * @param after
         * @param separator
         */
        private Joined(CSSValue before, CSSValue after, String separator) {
            this.before = Objects.requireNonNull(before);
            this.after = Objects.requireNonNull(after);
            this.separator = separator;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            return before.valueFor(vendor) + separator + after.valueFor(vendor);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean match(String value) {
            return before.match(value) || after.match(value);
        }
    }

    /**
     * @version 2018/09/02 11:42:39
     */
    static class Concated extends CSSValue {

        /** The value separator. */
        private final String separator;

        /** The actual values. */
        final List<CSSValue> values;

        /**
         * @param separator
         */
        Concated(String separator) {
            this.separator = separator;
            this.values = new ArrayList();
        }

        /**
         * @param separator
         */
        private Concated(String separator, List<CSSValue> values) {
            this.separator = separator;
            this.values = values;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            return I.join(separator, values);
        }

        /**
         * Add value.
         * 
         * @param value
         * @return
         */
        protected Concated add(CSSValue value) {
            if (value != null) {
                values.add(value);
            }
            return this;
        }
    }
}
