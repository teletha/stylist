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

import kiss.I;
import kiss.Variable;
import stylist.util.Formatter;
import stylist.util.Properties;

/**
 * <p>
 * This class is CSSStyleRule which represents a single CSS style rule.
 * </p>
 * 
 * @version 2018/08/30 18:18:24
 */
public class StyleRule implements Comparable<StyleRule> {

    /** The selector. */
    public final String selector;

    /** The property list. */
    public final Properties properties;

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
        this.properties = new Properties();
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
                        Variable<String> current = this.properties.get(resolvedName);

                        if (current.isAbsent()) {
                            this.properties.add(resolvedName, value);
                        } else {
                            this.properties.set(resolvedName, current.get() + separator + value);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(StyleRule o) {
        return selector.compareTo(o.selector);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Formatter.pretty().format(this);
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

    /**
     * Create empty {@link StyleRule} with the specified selector.
     * 
     * @param selector
     * @return
     */
    public static StyleRule create(String selector) {
        return new StyleRule(selector);
    }
}
