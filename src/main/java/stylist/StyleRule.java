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

import stylist.util.Formatter;
import stylist.util.Properties;

/**
 * <p>
 * This class is CSSStyleRule which represents a single CSS style rule.
 * </p>
 * 
 * @version 2018/09/05 12:03:58
 */
public class StyleRule implements Comparable<StyleRule> {

    /** The selector. */
    public final CSSValue selector;

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
        this.selector = CSSValue.of(selector);
        this.properties = new Properties();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(StyleRule o) {
        return selector.toString().compareTo(o.selector.toString());
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
        return create("$", style);
    }

    /**
     * Create {@link StyleRule} from the specified {@link Style}.
     * 
     * @param object A style description.
     * @return A create new {@link StyleRule}.
     */
    public static StyleRule create(String template, Style style) {
        // store parent rule
        StyleRule parent = PropertyDefinition.rule;

        // compute selector
        String selector;

        if (parent == null) {
            selector = "." + style.name();
        } else {
            // check pseudo element
            String pseudo;
            String parentSelector = parent.selector.toString();
            int index = parentSelector.indexOf("::");

            if (index == -1) {
                selector = parentSelector;
                pseudo = "";
            } else {
                selector = parentSelector.substring(0, index);
                pseudo = parentSelector.substring(index);
            }
            selector = template.replace("$", selector) + pseudo;
        }

        // create child rule
        StyleRule child = new StyleRule(selector);

        // swap context rule and execute it
        PropertyDefinition.rule = child;
        style.style();
        PropertyDefinition.rule = parent;

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
