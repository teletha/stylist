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

/**
 * The place-self CSS shorthand property allows you to align an individual item in both the block
 * and inline directions at once (i.e. the align-self and justify-self properties). This property
 * applies to block-level boxes, absolutely-positioned boxes, and grid items. If the second value is
 * not present, the first value is also used for it.
 */
public class PlaceSelf extends PropertyDefinition<PlaceSelf> {

    /**
     * The align-self CSS property overrides a grid or flex item's align-items value. In grid, it
     * aligns the item inside the grid area. In flexbox, it aligns the item on the cross axis.
     */
    public final AlignItems<PlaceSelf> align = new AlignItems<>("align-self", this);

    /**
     * The CSS justify-self property sets the way a box is justified inside its alignment container
     * along the appropriate axis.
     */
    public final AlignItems<PlaceSelf> justify = new AlignItems("justify-self", this);

    public PlaceSelf() {
        super("place-self");
    }
}
