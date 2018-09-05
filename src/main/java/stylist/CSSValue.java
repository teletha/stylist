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

import static stylist.Vendor.*;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Objects;

import stylist.util.Formatter;

/**
 * @version 2018/09/02 11:07:38
 */
public abstract class CSSValue {

    /** The empty value. */
    public static final CSSValue EMPTY = new Empty();

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

    public EnumSet<Vendor> aaa() {
        return vendors();
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
    public CSSValue join(String separator, CSSValue value) {
        return new Joined(this, value, separator);
    }

    /**
     * Format this value.
     * 
     * @param formatter
     * @return
     */
    public String format(Formatter formatter) {
        return toString();
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
    public static CSSValue of(Object value, Vendor... vendors) {
        if (value instanceof CSSValue) {
            return (CSSValue) value;
        }
        if (value instanceof Number) {
            return new Digit((Number) value);
        }
        return new Value(value, vendors);
    }

    /**
     * @version 2018/09/02 13:17:12
     */
    private static class Empty extends CSSValue {

        /**
         * {@inheritDoc}
         */
        @Override
        public CSSValue join(String separator, CSSValue value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }
    }

    /**
     * @version 2018/09/02 11:05:46
     */
    private static class Value extends CSSValue {

        /** The vendored values. */
        private final EnumMap<Vendor, String> values;

        /**
         * @param value
         * @param vendors
         */
        private Value(Object value, Vendor... vendors) {
            String v = String.valueOf(value);

            this.values = new EnumMap(Vendor.class);
            this.values.put(Standard, v);
            for (Vendor vendor : vendors) {
                this.values.put(vendor, vendor + v);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected EnumSet<Vendor> vendors() {
            return EnumSet.copyOf(values.keySet());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String valueFor(Vendor vendor) {
            String value = values.get(vendor);

            if (value != null) {
                return value;
            }
            return values.get(Standard);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return values.hashCode();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Value) {
                return values.equals(((Value) obj).values);
            } else {
                return false;
            }
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
            if (obj instanceof Digit) {
                return value.equals(((Digit) obj).value);
            } else {
                return false;
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

        /**
         * {@inheritDoc}
         */
        @Override
        public String format(Formatter formatter) {
            return value.format(formatter);
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
            return value.equals(obj);
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
        protected EnumSet<Vendor> vendors() {
            EnumSet<Vendor> set = EnumSet.copyOf(before.vendors());
            set.addAll(after.vendors());

            return set;
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
        public String format(Formatter formatter) {
            return before.format(formatter) + separator + after.format(formatter);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return Objects.hash(before, after);
        }
    }
}
