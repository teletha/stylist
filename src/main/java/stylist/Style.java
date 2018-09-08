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

import java.util.Collection;
import java.util.List;

/**
 * @version 2018/09/08 18:05:56
 */
public interface Style extends Location {

    /** The empty {@link Style} for reuse. */
    Style Empty = () -> {
    };

    /**
     * <p>
     * Define the style declaration.
     * </p>
     */
    void style();

    /**
     * <p>
     * Combine this {@link Style} and the specified {@link Style}.
     * </p>
     * 
     * @param style A style to combine.
     * @return A combined {@link Style}.
     */
    default Style with(Style style) {
        return MultipleStyle.of(this, style);
    }

    /**
     * Returns all members of the group to which this {@link Style} belongs. Usually this
     * {@link Style} only.
     * 
     * @return All members of the group to which this {@link Style} belongs.
     */
    default Collection<Style> group() {
        return List.of(this);
    }
}
