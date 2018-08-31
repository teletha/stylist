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
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import kiss.I;
import stylist.util.DualList;

/**
 * <p>
 * This class is CSSStyleRule which represents a single CSS style rule.
 * </p>
 * 
 * @version 2018/08/30 18:18:24
 */
public class StyleRule {

    /** The selector. */
    public final String selector;

    /** The property list. */
    public final DualList<String, String> properties;

    /** The sub rules. */
    public final ArrayList<StyleRule> children = new ArrayList();

    /**
     * <p>
     * Define style rule.
     * </p>
     * 
     * @param name An actual selector.
     */
    StyleRule(String selector) {
        this.selector = selector;
        this.properties = new DualList();
    }

    /**
     * <p>
     * Check the current value.
     * </p>
     * 
     * @param name A property name.
     * @param value A property value you want.
     * @return
     */
    boolean is(String name, String value) {
        return properties.contains(name, value);
    }

    /**
     * <p>
     * Declare the specified property.
     * </p>
     * 
     * @param name A property name.
     * @param values A list of property values.
     * @param separator A value separator.
     * @param override A flag for property override.
     * @param prefixes A list of vendors for property name.
     */
    void property(String name, List values, String separator, int writeMode, EnumSet<Vendor> prefixes) {
        if (name != null && name.length() != 0 && values != null) {
            EnumMap<Vendor, List<String>> properties = new EnumMap(Vendor.class);

            // calculate dependent vendors
            EnumSet<Vendor> vendors = EnumSet.copyOf(prefixes);

            for (Object value : values) {
                if (value instanceof CSSValue) {
                    vendors.addAll(((CSSValue) value).vendors());
                }
            }

            for (Vendor vendor : vendors) {
                List<String> text = new ArrayList();

                for (Object value : values) {
                    if (value != null) {
                        if (value instanceof CSSValue) {
                            String vendered = ((CSSValue) value).valueFor(vendor);

                            if (vendered != null && vendered.length() != 0) {
                                text.add(vendered);
                            }
                        } else if (value instanceof Number) {
                            Number number = (Number) value;

                            if (number.intValue() == number.doubleValue()) {
                                text.add(String.valueOf(number.intValue()));
                            } else {
                                text.add(number.toString());
                            }
                        } else {
                            String decoded = value.toString();

                            if (decoded != null && decoded.length() != 0) {
                                text.add(decoded);
                            }
                        }
                    }
                }
                properties.put(vendor, text);
            }

            for (Entry<Vendor, List<String>> property : properties.entrySet()) {
                String value = I.join(separator, property.getValue());

                if (value.length() != 0) {
                    Vendor vendor = property.getKey();

                    if (!prefixes.contains(vendor)) {
                        vendor = Vendor.Standard;
                    }

                    String resolvedName = vendor + name;

                    switch (writeMode) {
                    case 0: // addition
                        this.properties.add(resolvedName, value);
                        break;

                    case 1: // override
                        this.properties.set(resolvedName, value);
                        break;

                    case 2: // append
                        Optional<String> current = this.properties.get(resolvedName);

                        if (current.isPresent()) {
                            this.properties.set(resolvedName, current.get() + separator + value);
                        } else {
                            this.properties.add(resolvedName, value);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * Format this {@link StyleRule} human-readably.
     * 
     * @return
     */
    public String format() {
        return format("\r\n", "\t", ": ", " ");
    }

    /**
     * Format this {@link StyleRule} computer-readably.
     * 
     * @return
     */
    public String formatMin() {
        return format("", "", ":", "");
    }

    /**
     * Format code.
     * 
     * @param eol
     * @return
     */
    private String format(String eol, String indent, String propertySeparator, String selectorSeparator) {
        StringBuilder builder = new StringBuilder(selector);
        builder.append(selectorSeparator).append("{").append(eol);
        for (int i = 0, size = properties.size(); i < size; i++) {
            builder.append(indent).append(properties.key(i)).append(propertySeparator).append(properties.value(i)).append(";").append(eol);
        }
        builder.append("}").append(eol);
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return format();
    }

    /**
     * Create {@link StyleRule} from the specified {@link Style}.
     * 
     * @param style
     * @return A create new {@link StyleRule}.
     */
    public static StyleRule create(Style style) {
        return create("$", style, true);
    }

    /**
     * Create {@link StyleRule} from the specified {@link Style}.
     * 
     * @param object A style description.
     * @return A create new {@link StyleRule}.
     */
    public static StyleRule create(String template, Style style, boolean root) {
        // store parent rule
        StyleRule parent = PropertyDefinition.properties;

        // compute selector
        String selector;

        if (parent == null || root) {
            selector = "." + style.name();
        } else {
            // check pseudo element
            String pseudo;
            int index = parent.selector.indexOf("::");

            if (index == -1) {
                selector = parent.selector;
                pseudo = "";
            } else {
                selector = parent.selector.substring(0, index);
                pseudo = parent.selector.substring(index);
            }
            selector = template.replace("$", selector) + pseudo;
        }

        // create child rule
        StyleRule child = new StyleRule(selector);

        // swap context rule and execute it
        PropertyDefinition.properties = child;
        style.style();
        PropertyDefinition.properties = parent;

        if (parent != null) {
            parent.children.add(child);
        }

        // API definition
        return child;
    }
}
