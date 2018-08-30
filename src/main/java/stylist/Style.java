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

/**
 * @version 2018/08/30 18:05:55
 */
public interface Style extends Declarable, Location {

    /** The empty style. */
    Style Empty = () -> {
    };

    /**
     * <p>
     * Define the style declaration.
     * </p>
     */
    void style();

    /**
     * {@inheritDoc}
     */
    @Override
    default void declare() {
        // If this exception will be thrown, it is bug of this program. So we must rethrow the
        // wrapped error in here.
        throw new Error();
    }

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
}
