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
import java.util.StringJoiner;
import java.util.function.Function;

import stylist.util.Strings;

/**
 * @version 2018/09/04 23:30:50
 */
public class PropertyDefinition<T> {

    /** The current processing property holder. */
    protected static StyleRule properties;

    /** The property name. */
    private final String name;

    /** The context property. */
    private final T context;

    private final EnumSet<Vendor> vendors;

    /**
     * <p>
     * Property definition.
     * </p>
     */
    protected PropertyDefinition() {
        this(null, null);
    }

    /**
     * <p>
     * Property definition.
     * </p>
     */
    protected PropertyDefinition(String name) {
        this(name, null);
    }

    /**
     * <p>
     * Property definition.
     * </p>
     */
    protected PropertyDefinition(String name, T context) {
        this(name, context, Vendor.Standard);
    }

    /**
     * <p>
     * Property definition.
     * </p>
     */
    protected PropertyDefinition(String name, T context, Vendor... vendors) {
        if (name == null) {
            name = Strings.hyphenate(getClass().getSimpleName());
        }

        if (context == null) {
            context = (T) this;
        }

        this.name = name;
        this.context = context;
        this.vendors = EnumSet.of(Vendor.Now, vendors);
    }

    /**
     * <p>
     * The initial CSS keyword applies the initial value of a property to an element. It is allowed
     * on every CSS property and causes the element for which it is specified to use the initial
     * value of the property.
     * </p>
     * 
     * @return
     */
    public T initial() {
        return value("initial");
    }

    /**
     * <p>
     * Set property.
     * </p>
     * 
     * @param value A property name.
     * @param value A property value.
     * @return Chainable API.
     */
    protected final T value(Object value) {
        return value(name, value);
    }

    /**
     * <p>
     * Set property.
     * </p>
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @return Chainable API.
     */
    protected final T value(String name, int... values) {
        List<CSSValue> list = new ArrayList();

        for (int i = 0; i < values.length; i++) {
            list.add(CSSValue.of(values[i]));
        }
        return value(name, list, ",");
    }

    /**
     * <p>
     * Set property.
     * </p>
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @return Chainable API.
     */
    protected final T value(String name, float... values) {
        return value(name, ",", values);
    }

    /**
     * <p>
     * Set property.
     * </p>
     * 
     * @param name A property name.
     * @param separator A value separator.
     * @param values A list of property values.
     * @return Chainable API.
     */
    protected final T value(String name, String separator, float... values) {
        List<CSSValue> list = new ArrayList();

        for (int i = 0; i < values.length; i++) {
            list.add(CSSValue.of(values[i]));
        }
        return value(name, list, separator);
    }

    /**
     * <p>
     * Set property.
     * </p>
     * 
     * @param name A property name.
     * @param value A property value.
     * @return Chainable API.
     */
    protected final T value(String name, Object value) {
        return value(name, List.of(CSSValue.of(value)), " ");
    }

    /**
     * <p>
     * Set property.
     * </p>
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @param separator A value separator.
     * @return Chainable API.
     */
    protected final T value(String name, List<? extends CSSValue> values, String separator) {
        return value(name, values, separator, 0);
    }

    /**
     * <p>
     * Set property.
     * </p>
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @param separator A value separator.
     * @param writeMode A value write mechanism.
     * @return Chainable API.
     */
    protected final T value(String name, List<? extends CSSValue> values, String separator, int writeMode) {
        return value(EnumSet.noneOf(Vendor.class), name, values, separator, writeMode);
    }

    /**
     * <p>
     * Set property.
     * </p>
     * 
     * @param vendors A list of {@link Vendor} for the specified property name.
     * @param name A property name.
     * @param values A list of property values.
     * @param separator A value separator.
     * @param writeMode A value write mechanism.
     * @return Chainable API.
     */
    protected final T value(EnumSet<Vendor> vendors, String name, List<? extends CSSValue> values, String separator, int writeMode) {
        vendors.addAll(this.vendors);

        properties.property(name, values, separator, writeMode, vendors);

        return context;
    }

    /**
     * <p>
     * Check the current value.
     * </p>
     * 
     * @param value A property value you want.
     * @return A result.
     */
    protected final boolean is(String value) {
        return properties.is(name, value);
    }

    /**
     * <p>
     * Join all values.
     * </p>
     * 
     * @param images
     * @param index
     * @return
     */
    protected static final <T> String join(T[] items, Function<T, Object> conveter) {
        StringJoiner joiner = new StringJoiner(",");

        for (T item : items) {
            joiner.add(conveter.apply(item).toString());
        }
        return joiner.toString();
    }

    /**
     * <p>
     * Join all values.
     * </p>
     * 
     * @param images
     * @param index
     * @return
     */
    protected static final <T> String join(Iterable<T> items, Function<T, Object> conveter) {
        StringJoiner joiner = new StringJoiner(",");

        for (T item : items) {
            joiner.add(conveter.apply(item).toString());
        }
        return joiner.toString();
    }

    /**
     * <p>
     * Create sub rule.
     * </p>
     * 
     * @param template A selector template.
     * @param sub A sub style descriptor.
     */
    protected static final StyleRule createSubRule(String template, Style sub) {
        return StyleRule.create(template, sub, false);
    }
}
