/*
 * Copyright (C) 2020 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public interface Style extends Consumer, Serializable {

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

    /**
     * {@inheritDoc}
     */
    @Override
    default void accept(Object context) {
        // do nothing
    }

    /**
     * Describe detailed information for this {@link Location}.
     * 
     * @return
     */
    default String detail() {
        return ValuedStyle.locate(this);
    }

    /**
     * Gets the CSS selector expression associated with this {@link Style}. The default
     * implementation returns a class selector with the obfuscated name.
     * 
     * @return CSS selector expression (For example .name, #id or element).
     */
    default String selector() {
        return Stylist.id(this);
    }

    /**
     * Gets class names that uniquely identifies this {@link Style} (returns class name [A] instead
     * of class selector [.A]). If the style is assigned a selector other than the class name, a
     * zero-length array will be returned.
     * 
     * @return A list of class names.
     */
    default String[] className() {
        String name = selector();

        return name.charAt(0) == '.' ? new String[] {name.substring(1)} : new String[0];
    }

    /**
     * Create named {@link Style}.
     * 
     * @param name A style name.
     * @param style A style definition.
     * @return
     */
    @SuppressWarnings("serial")
    static Style named(String name, Runnable style) {
        return new Style() {

            @Override
            public String selector() {
                return name;
            }

            @Override
            public void style() {
                style.run();
            }
        };
    }
}