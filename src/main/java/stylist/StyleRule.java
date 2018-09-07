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
 * This class is CSSStyleRule which represents a single CSS style rule.
 * 
 * @version 2018/09/06 16:35:10
 */
public class StyleRule implements Comparable<StyleRule> {

    /** The selector. */
    public final CSSValue selector;

    /** The description. */
    public final String description;

    /** The internal selector. */
    private final SelectorDSL internal;

    /** The property list. */
    public final Properties properties;

    /** The sub rules. */
    public final ArrayList<StyleRule> children = new ArrayList();

    /**
     * Define style rule.
     * 
     * @param name An actual selector.
     * @param description A description of style.
     */
    private StyleRule(SelectorDSL selector, String description) {
        this.selector = selector.selector();
        this.description = description;
        this.internal = selector;
        this.properties = new Properties();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(StyleRule o) {
        return description.compareTo(o.description);
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
        return create(style, SelectorDSL.create(null));
    }

    /**
     * Create {@link StyleRule} from the specified {@link Style}.
     * 
     * @param object A style description.
     * @return A create new {@link StyleRule}.
     */
    static StyleRule create(Style style, SelectorDSL selector) {
        // store parent rule
        StyleRule parent = PropertyDefinition.rule;

        if (parent == null) {
            selector.selector = "." + style.name();
        } else {
            selector.replace(parent.internal);
        }

        // create child rule
        StyleRule child = new StyleRule(selector, style.detail());

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
}
