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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MultipleStyle implements Style {

    /** The aggregation. */
    final Style[] styles;

    /** The name list. */
    final String[] names;

    /**
     * Hide constructor.
     * 
     * @param styles A style group.
     */
    private MultipleStyle(Style[] styles) {
        this.styles = styles;

        Set<String> set = new HashSet();

        for (Style style : styles) {
            for (String name : style.className()) {
                set.add(name);
            }
        }
        this.names = set.toArray(new String[set.size()]);
    }

    /**
     * <p>
     * Helper method to create {@link MultipleStyle}.
     * </p>
     * 
     * @param styles
     * @return
     */
    static MultipleStyle of(Style... styles) {
        List<Style> list = new ArrayList();
        merge(list, styles);

        return new MultipleStyle(list.toArray(new Style[list.size()]));
    }

    /**
     * <p>
     * Helper method to create {@link MultipleStyle}.
     * </p>
     * 
     * @param list
     * @param styles
     */
    private static void merge(List<Style> list, Style... styles) {
        for (Style style : styles) {
            if (style instanceof MultipleStyle) {
                merge(list, ((MultipleStyle) style).styles);
            } else if (!list.contains(style)) {
                list.add(style);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] className() {
        return names;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Style> group() {
        return List.of(styles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void style() {
        for (Style style : styles) {
            style.style();
        }
    }
}
