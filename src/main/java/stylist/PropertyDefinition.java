/*
 * Copyright (C) 2023 The STYLIST Development Team
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
import java.util.StringJoiner;
import java.util.function.Function;

import stylist.value.CSSValue;
import stylist.value.Vendor;

public class PropertyDefinition<T> {

    /** The current processing property holder. */
    protected static StyleRule rule;

    /** The property name. */
    private final String name;

    /** The context property. */
    private final T context;

    /** The required vendors for property name. */
    private final EnumSet<Vendor> requiredVendorsForNames;

    /**
     * Property definition.
     */
    protected PropertyDefinition() {
        this(null, null);
    }

    /**
     * Property definition.
     * 
     * @param name A property name.
     */
    protected PropertyDefinition(String name) {
        this(name, null);
    }

    /**
     * Property definition.
     * 
     * @param name A property name.
     * @param context The chainable context object.
     */
    protected PropertyDefinition(String name, T context) {
        this(name, context, Vendor.Standard);
    }

    /**
     * Property definition.
     * 
     * @param name A property name.
     * @param context The chainable context object.
     * @param vendors The required vendors.
     */
    protected PropertyDefinition(String name, T context, Vendor... vendors) {
        this.name = Objects.requireNonNullElse(name, hyphenate(getClass().getSimpleName()));
        this.context = Objects.requireNonNullElse(context, (T) this);
        this.requiredVendorsForNames = EnumSet.of(Vendor.Standard, vendors);
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
     * The initial CSS keyword applies the initial value of a property to an element. It is allowed
     * on every CSS property and causes the element for which it is specified to use the initial
     * value of the property.
     * 
     * @return
     */
    public T initial() {
        return value("initial");
    }

    /**
     * The unset CSS keyword resets a property to its inherited value if it inherits from its
     * parent, and to its initial value if not. In other words, it behaves like the inherit keyword
     * in the first case, and like the initial keyword in the second case. It can be applied to any
     * CSS property, including the CSS shorthand all.
     * 
     * @return
     */
    public T unset() {
        return value("unset");
    }

    /**
     * Set property.
     * 
     * @param value A property value.
     * @return Chainable API.
     */
    protected final T value(Object value) {
        return value(name, value);
    }

    /**
     * Set property as int values.
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @return Chainable API.
     */
    protected final T value(Object name, int... values) {
        List<CSSValue> list = new ArrayList();

        for (int i = 0; i < values.length; i++) {
            list.add(CSSValue.of(values[i]));
        }
        return value(name, list, ",");
    }

    /**
     * Set property as float values.
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @return Chainable API.
     */
    protected final T value(Object name, float... values) {
        return value(name, ",", values);
    }

    /**
     * Set property as float values with separator.
     * 
     * @param name A property name.
     * @param separator A value separator.
     * @param values A list of property values.
     * @return Chainable API.
     */
    protected final T value(Object name, String separator, float... values) {
        List<CSSValue> list = new ArrayList();

        for (int i = 0; i < values.length; i++) {
            list.add(CSSValue.of(values[i]));
        }
        return value(name, list, separator);
    }

    /**
     * Set property.
     * 
     * @param name A property name.
     * @param value A property value.
     * @return Chainable API.
     */
    protected final T value(Object name, Object value) {
        return value(name, List.of(CSSValue.of(value)), " ");
    }

    /**
     * Set the property.
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @param separator A value separator.
     * @return Chainable API.
     */
    protected final T value(Object name, List<? extends CSSValue> values, String separator) {
        // create property name and value
        CSSValue propertyName = CSSValue.of(name, requiredVendorsForNames);
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
     * Read the current value as {@link String}. If it is not specified yet, your default value will
     * be returned.
     * 
     * @param defaultValue
     * @return
     */
    protected final String readValueAsString(String defaultValue) {
        return rule.properties.get(name).map(CSSValue::toString).or(defaultValue);
    }

    /**
     * Read the snapshot of all specified property names.
     * 
     * @return
     */
    protected final List<CSSValue> readPropertyNames() {
        return new ArrayList(rule.properties.names());
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
     * Join all values by comma.
     * 
     * @return
     */
    protected static final <T> String join(T[] items, Function<T, Object> conveter) {
        return join(List.of(items), conveter);
    }

    /**
     * Join all values by comma.
     * 
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
     * Convert to hyphened lower case.
     * 
     * @param value
     * @return
     */
    private static String hyphenate(String value) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);

            if (c == '_') {
                builder.append('-');
            } else if (Character.isUpperCase(c)) {
                if (i != 0) {
                    builder.append('-');
                }
                builder.append(Character.toLowerCase(c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * INTERNAL USAGE : Bypass to animation manager.
     * 
     * @param anime
     */
    protected static final void registerAnimation(AnimationFrames anime) {
        Stylist.animations.add(anime);
    }
}