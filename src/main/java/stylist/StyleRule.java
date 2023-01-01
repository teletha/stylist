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

import kiss.Variable;
import stylist.util.Properties;
import stylist.value.CSSValue;

/**
 * This class is CSSStyleRule which represents a single CSS style rule.
 */
class StyleRule implements Comparable<StyleRule> {

    /** The selector. */
    final CSSValue selector;

    /** The description. */
    final String description;

    /** The internal selector. */
    final SelectorDSL internal;

    /** The property list. */
    final Properties properties;

    /** The sub rules. */
    final ArrayList<StyleRule> children = new ArrayList();

    /** The media rules. */
    final Variable<MediaQuery> query = Variable.empty();

    /**
     * Define style rule.
     * 
     * @param selector An actual selector.
     * @param description A description of style.
     */
    StyleRule(SelectorDSL selector, String description) {
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
        return Stylist.pretty().format(this);
    }
}