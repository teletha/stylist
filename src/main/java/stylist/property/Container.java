/*
 * Copyright (C) 2025 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.property;

import stylist.PropertyDefinition;

public class Container extends PropertyDefinition<Container> {

    /**
     * Establishes a query container for container size queries in both the inline and block
     * dimensions. Applies layout containment, style containment, and size containment to the
     * container.
     * <p>
     * Size containment is applied to the element in both the inline and block directions. The size
     * of the element can be computed in isolation, ignoring the child elements.
     * 
     * @return
     */
    public Container size() {
        return value("container-type", "size");
    }

    /**
     * Establishes a query container for dimensional queries on the inline axis of the container.
     * Applies layout, style, and inline-size containment to the element.
     * <p>
     * Inline size containment is applied to the element. The inline size of the element can be
     * computed in isolation, ignoring the child elements.
     * 
     * @return
     */
    public Container inlineSize() {
        return value("container-type", "inline-size");
    }

    /**
     * Default value. The element is not a query container for any container size queries, but
     * remains a query container for container style queries.
     * 
     * @return
     */
    public Container normal() {
        return value("container-type", "normal");
    }

    /**
     * The container-name CSS property specifies a list of query container names used by
     * the @container at-rule in a container query. A container query will apply styles to elements
     * based on the size of the nearest ancestor with a containment context. When a containment
     * context is given a name, it can be specifically targeted using the @container at-rule instead
     * of the nearest ancestor with containment.
     * 
     * @return
     */
    public Container name(String name) {
        return value("container-name", name);
    }
}
