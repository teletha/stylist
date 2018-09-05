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

import stylist.CSSValue.Value;
import stylist.util.Strings;

/**
 * @version 2018/09/05 14:01:22
 */
public class PropertyDefinition<T> {

    /** The current processing property holder. */
    protected static StyleRule rule;

    /** The property name. */
    private final String name;

    /** The context property. */
    private final T context;

    private final EnumSet<Vendor> requiredVendorsForNames;

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
        this.requiredVendorsForNames = EnumSet.of(Vendor.Now, vendors);
    }

    /**
     * <p>
     * The inherit CSS keyword causes the element for which it is specified to take the computed
     * value of the property from its parent element. It can be applied to any CSS property,
     * including the CSS shorthand all.
     * </p>
     * <p>
     * For inherited properties, this reinforces the default behavior, and is only needed to
     * override another rule. For non-inherited properties, this specifies a behavior that typically
     * makes relatively little sense and you may consider using initial instead, or unset on the all
     * property.
     * </p>
     * 
     * @return
     */
    public T inherit() {
        return value("inherit");
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
     * The unset CSS keyword resets a property to its inherited value if it inherits from its
     * parent, and to its initial value if not. In other words, it behaves like the inherit keyword
     * in the first case, and like the initial keyword in the second case. It can be applied to any
     * CSS property, including the CSS shorthand all.
     * </p>
     * 
     * @return
     */
    public T unset() {
        return value("unset");
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
     * Declare the property.
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @param separator A value separator.
     * @return Chainable API.
     */
    protected final T value(String name, List<? extends CSSValue> values, String separator) {
        // create property name and value
        CSSValue propertyName = new Value(name, requiredVendorsForNames);
        CSSValue propertyValue = CSSValue.EMPTY;

        for (CSSValue value : values) {
            propertyValue = propertyValue.join(separator, value);
        }

        // declare property
        rule.properties.set(propertyName, propertyValue);

        // API definition
        return context;
    }

    /**
     * Check the current value.
     * 
     * @param value A property value you want.
     * @return A result.
     */
    protected final boolean is(String value) {
        return rule.properties.contains(name, value);
    }

    /**
     * Join all values.
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
     * Join all values.
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
