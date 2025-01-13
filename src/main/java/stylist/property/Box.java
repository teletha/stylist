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

public class Box<C> extends PropertyDefinition<C> {

    /**
     * Hide.
     */
    Box(String name, C context) {
        super(name, context);
    }

    /**
     * The browser will calculate and select a height for the specified element.
     * 
     * @return Chainable API.
     */
    public C auto() {
        return value("auto");
    }

    /**
     * The intrinsic preferred height.
     * 
     * @return Chainable API.
     */
    public C maxContent() {
        return value("max-content");
    }

    /**
     * The intrinsic minimum height.
     * 
     * @return Chainable API.
     */
    public C minContent() {
        return value("min-content");
    }

    /**
     * The larger of: the intrinsic minimum height the smaller of the intrinsic preferred height
     * and the available height
     * 
     * @return Chainable API.
     */
    public C fitContent() {
        return value("fit-content");
    }

    /**
     * Use the fill-available inline size or fill-available block size, as appropriate to the
     * writing mode.
     * 
     * @return Chainable API.
     */
    public C fill() {
        return value("fill");
    }

    /**
     * Sets the width of the element's margin box to the width of its containing block. It
     * attempts to make the margin box fill the available space in the containing block, so in a
     * way behaving similar to 100% but applying the resulting size to the margin box rather
     * than the box determined by box-sizing.
     * 
     * @return
     */
    public C stretch() {
        return value("stretch");
    }
}