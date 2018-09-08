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

import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 2018/09/08 18:18:37
 */
public interface ValueStyle<V> {

    /**
     * Declare styles for the specified value.
     */
    void style(V value);

    /**
     * <p>
     * Retrieve the refined {@link Style} of the specified value.
     * </p>
     * 
     * @param value A conditional value.
     * @return A refined {@link Style}.
     */
    default Style of(V value) {
        if (value == null) {
            return Style.Empty;
        }

        return ValuedStyle.cache //
                .computeIfAbsent(this, styles -> new ConcurrentHashMap())
                .computeIfAbsent(value, key -> new ValuedStyle(this, key));
    }
}
