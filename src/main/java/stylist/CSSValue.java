/*
 * Copyright (C) 2019 stylist Development Team
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
 * @version 2018/09/05 12:55:50
 */
public abstract class CSSValue {

    /** The empty value. */
    public static final CSSValue EMPTY = new Value("");

    /** For reuse. */
    protected static final EnumSet<Vendor> NoVendors = EnumSet.of(Vendor.Standard);

    /**
     * Get all required vendors.
     * 
     * @return
     */
    public EnumSet<Vendor> vendors() {
        return NoVendors;
    }

    /**
     * <p>
     * Compute the vendor specific value.
     * </p>
     * 
     * @param vendor A target {@link Vendor}.
     */
    protected abstract String valueFor(Vendor vendor);

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
     * Join this value and the specified {@link CSSValue} by whitespace.
     * 
     * @param other A target to join.
     * @return A joined {@link CSSValue}.
     */
    public final CSSValue join(CSSValue other) {
        return join(" ", other);
    }

    /**
     * Join this value and the specified {@link CSSValue} by the specified separator.
     * 
     * @param separator A separator value.
     * @param other A target to join.
     * @return A joined {@link CSSValue}.
     */
    public final CSSValue join(String separator, CSSValue other) {
        if (this == EMPTY) {
            return other;
        }
        return new Joined(this, other, separator);
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
     * Returns {@link Vendor#Standard} value.
     * 
     * @return A {@link Vendor#Standard} value.
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
        return of(value, EnumSet.of(Standard, vendors));
    }

    /**
     * Create simple text {@link CSSValue}.
     * 
     * @param value
     * @return
     */
    public static CSSValue of(Object value, EnumSet<Vendor> vendors) {
        if (value instanceof CSSValue) {
            return (CSSValue) value;
        }
        if (value instanceof Number) {
            return new Digit((Number) value);
        }
        return new Value(value, vendors);
    }

    /**
     * @version 2018/09/02 11:05:46
     */
    static class Value extends CSSValue {

        /** The vendored values. */
        private final EnumMap<Vendor, String> values;

        /**
         * @param value
         * @param vendors
         */
        Value(Object value, Vendor... vendors) {
            this(value, EnumSet.of(Standard, vendors));
        }

        /**
         * @param value
         * @param vendors
         */
        Value(Object value, EnumSet<Vendor> vendors) {
            String v = String.valueOf(value);

            this.values = new EnumMap(Vendor.class);
            for (Vendor vendor : vendors) {
                this.values.put(vendor, vendor + v);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EnumSet<Vendor> vendors() {
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
        public EnumSet<Vendor> vendors() {
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
