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

/**
 * @version 2018/08/30 18:07:09
 */
public interface ValueStyle<V> extends Location {

    /**
     * <p>
     * Declare styles for the specified value.
     * </p>
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
        return ValuedStyle.cache.computeIfAbsent(this, style -> new HashMap()).computeIfAbsent(value, key -> new ValuedStyle(this, key));
    }
}
