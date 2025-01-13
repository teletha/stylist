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
 * The CSS align-items property sets the align-self value on all direct children as a group. In
 * flexbox, it controls the alignment of items on the cross axis. In grid layout, it controls the
 * alignment of items on the block axis within their grid areas.
 */
public class AlignItems<C> extends PropertyDefinition<C> {

    public AlignItems(String name, C context) {
        super(name, context);
    }

    /// The effect of this keyword is dependent of the layout mode we are in:
    ///
    /// - In absolutely-positioned layouts, the keyword behaves like start on replaced absolutely-positioned boxes, and as stretch on all other absolutely-positioned boxes.
    /// - In static position of absolutely-positioned layouts, the keyword behaves as stretch.
    /// - For flex items, the keyword behaves as stretch.
    /// - For grid items, this keyword leads to a behavior similar to the one of stretch, except for boxes with an aspect ratio or an intrinsic sizes where it behaves like start.
    /// - The property doesn't apply to block-level boxes, and to table cells.
    ///
    public C normal() {
        return value("normal");
    }

    /**
     * The items are packed flush to each other toward the start edge of the alignment container in
     * the appropriate axis.
     * 
     * @return
     */
    public C start() {
        return value("start");
    }

    /**
     * The items are packed flush to each other toward the end edge of the alignment container in
     * the appropriate axis.
     * 
     * @return
     */
    public C end() {
        return value("end");
    }

    /**
     * The flex items' margin boxes are centered within the line on the cross-axis. If the
     * cross-size of an item is larger than the flex container, it will overflow equally in both
     * directions.
     * 
     * @return
     */
    public C center() {
        return value("center");
    }

    /**
     * All flex items are aligned such that their flex container baselines align. The item with the
     * largest distance between its cross-start margin edge and its baseline is flushed with the
     * cross-start edge of the line.
     * 
     * @return
     */
    public C baseline() {
        return value("baseline");
    }

    /**
     * All flex items are aligned such that their flex container baselines align. The item with the
     * largest distance between its cross-start margin edge and its baseline is flushed with the
     * cross-start edge of the line.
     * 
     * @return
     */
    public C firstBaseline() {
        return value("first baseline");
    }

    /**
     * All flex items are aligned such that their flex container baselines align. The item with the
     * largest distance between its cross-start margin edge and its baseline is flushed with the
     * cross-start edge of the line.
     * 
     * @return
     */
    public C lastBaseline() {
        return value("last baseline");
    }

    /**
     * The items are packed flush to the edge of the alignment container's start side of the item,
     * in the appropriate axis.
     * 
     * @return
     */
    public C selfStart() {
        return value("self-start");
    }

    /**
     * The items are packed flush to the edge of the alignment container's end side of the item, in
     * the appropriate axis.
     * 
     * @return
     */
    public C selfEnd() {
        return value("self-end");
    }

    /**
     * If the items are smaller than the alignment container, auto-sized items will be equally
     * enlarged to fill the container, respecting the items' width and height limits.
     * 
     * @return
     */
    public C stretch() {
        return value("stretch");
    }

    /**
     * In the case of anchor-positioned elements, aligns the items to the center of the associated
     * anchor element in the block direction. See Centering on the anchor using anchor-center.
     * 
     * @return
     */
    public C anchorCenter() {
        return value("anchor-center");
    }

    /**
     * Used alongside an alignment keyword. If the chosen keyword means that the item overflows the
     * alignment container causing data loss, the item is instead aligned as if the alignment mode
     * were start.
     * 
     * @return
     */
    public C safe() {
        return value("safe");
    }

    /**
     * Used alongside an alignment keyword. Regardless of the relative sizes of the item and
     * alignment container and whether overflow which causes data loss might happen, the given
     * alignment value is honored.
     * 
     * @return
     */
    public C unsafe() {
        return value("unsafe");
    }

    /**
     * Used in flex layout only, aligns the flex items flush against the flex container's main-start
     * or cross-start side. When used outside of a flex formatting context, this value behaves as
     * start.
     * 
     * @return
     */
    public C flexStart() {
        return value("flex-start");
    }

    /**
     * Used in flex layout only, aligns the flex items flush against the flex container's main-end
     * or cross-end side. When used outside of a flex formatting context, this value behaves as end.
     * 
     * @return
     */
    public C flexEnd() {
        return value("flex-end");
    }
}