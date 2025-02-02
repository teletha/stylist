/*
 * Copyright (C) 2025 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.property.helper;

/**
 * The CSS align-content property sets the distribution of space between and around content items
 * along a flexbox's cross axis, or a grid or block-level element's block axis.
 */
public enum Content {

    /**
     * The items are packed in their default position as if no align-content value was set.
     */
    Normal,

    /**
     * The items are packed flush to each other against the start edge of the alignment container in
     * the cross axis.
     */
    Start,

    /**
     * The items are packed flush to each other against the end edge of the alignment container in
     * the cross axis.
     */
    End,

    /**
     * The items are packed flush to each other in the center of the alignment container along the
     * cross axis.
     */
    Center,

    /**
     * Specifies participation in first- or last-baseline alignment: aligns the alignment baseline
     * of the box's first or last baseline set with the corresponding baseline in the shared first
     * or last baseline set of all the boxes in its baseline-sharing group.
     */
    Baseline,

    /**
     * Specifies participation in first- or last-baseline alignment: aligns the alignment baseline
     * of the box's first or last baseline set with the corresponding baseline in the shared first
     * or last baseline set of all the boxes in its baseline-sharing group.
     */
    FirstBaseline("first baseline"),

    /**
     * Specifies participation in first- or last-baseline alignment: aligns the alignment baseline
     * of the box's first or last baseline set with the corresponding baseline in the shared first
     * or last baseline set of all the boxes in its baseline-sharing group.
     */
    LastBaseline("last baseline"),

    /**
     * The items are evenly distributed within the alignment container along the cross axis. The
     * spacing between each pair of adjacent items is the same. The first item is flush with the
     * start edge of the alignment container in the cross axis, and the last item is flush with the
     * end edge of the alignment container in the cross axis.
     */
    SpaceBetween("space-between"),

    /**
     * The items are evenly distributed within the alignment container along the cross axis. The
     * spacing between each pair of adjacent items is the same. The empty space before the first and
     * after the last item equals half of the space between each pair of adjacent items.
     */
    SpaceAround("space-around"),

    /**
     * The items are evenly distributed within the alignment container along the cross axis. The
     * spacing between each pair of adjacent items, the start edge and the first item, and the end
     * edge and the last item, are all exactly the same.
     */
    SpaceEvenly("space-evenly"),

    /**
     * If the combined size of the items along the cross axis is less than the size of the alignment
     * container, any auto-sized items have their size increased equally (not proportionally), while
     * still respecting the constraints imposed by max-height/max-width (or equivalent
     * functionality), so that the combined size exactly fills the alignment container along the
     * cross axis.
     */
    Stretch,

    /**
     * Used alongside an alignment keyword. If the chosen keyword means that the item overflows the
     * alignment container causing data loss, the item is instead aligned as if the alignment mode
     * were start.
     */
    Safe,

    /**
     * Used alongside an alignment keyword. Regardless of the relative sizes of the item and
     * alignment container and whether overflow which causes data loss might happen, the given
     * alignment value is honored.
     */
    Unsafe,

    /**
     * The items are packed flush to each other against the edge of the alignment container
     * depending on the flex container's cross-start side. This only applies to flex layout items.
     * For items that are not children of a flex container, this value is treated like start.
     */
    FlexStart("flex-start"),

    /**
     * The items are packed flush to each other against the edge of the alignment container
     * depending on the flex container's cross-end side. This only applies to flex layout items. For
     * items that are not children of a flex container, this value is treated like end.
     */
    FlexEnd("flex-end");

    public final String value;

    private Content() {
        this.value = name().toLowerCase();
    }

    private Content(String value) {
        this.value = value;
    }
}