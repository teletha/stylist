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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * {@link Style} with the context value.
 * </p>
 * 
 * @version 2018/08/30 22:24:36
 */
class ValuedStyle<V> implements Style {

    /** The cache repository. */
    static final Map<ValueStyle, Map<Object, ValuedStyle>> cache = new HashMap();

    /** The original {@link ValueStyle}. */
    private final ValueStyle<V> base;

    /** The context value. */
    private final V value;

    /**
     * <p>
     * Create {@link Style} with the context value.
     * </p>
     * 
     * @param base A original style.
     * @param value A context value.
     */
    ValuedStyle(ValueStyle<V> base, V value) {
        this.value = value;
        this.base = base;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] names() {
        return new String[] {base.name(), name()};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void style() {
        base.style(value);
    }
}
