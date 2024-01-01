/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import stylist.PropertyDefinition;

public class Scroll extends PropertyDefinition<Scroll> {

    /**
     * <p>
     * The scroll-margin property is a shorthand property which sets all of the scroll-margin
     * longhands, assigning values much like the margin property does for the margin-* longhands
     * </p>
     * <p>
     * In these examples, you can see the effect of scroll-margin by scrolling the output box to a
     * point partway between two of the "pages" of the example's content. The value specified for
     * scroll-margin determines how much of the page that's primarily outside the snapport should
     * remain visible.
     * </p>
     * <p>
     * Thus, the scroll-margin values represent outsets defining the scroll snap area that is used
     * for snapping this box to the snapport. The scroll snap area is determined by taking the
     * transformed border box, finding its rectangular bounding box (axis-aligned in the scroll
     * container’s coordinate space), then adding the specified outsets.
     * </p>
     */
    public final BoxLength margin = new BoxLength("scroll-margin");

    /**
     * <p>
     * The scroll-padding property is a shorthand property that sets all of the scroll-padding-*
     * longhands. It assigns values much like the padding property does for the padding-* longhands.
     * </p>
     * <p>
     * The scroll-padding-* properties define offsets for the optimal viewing region of the
     * scrollport: the region used as the target region for placing things in view of the user. This
     * allows the author to exclude regions of the scrollport that are obscured by other content
     * (such as fixed-positioned toolbars or sidebars), or simply to put more breathing room between
     * a targeted element and the edges of the scrollport.
     * </p>
     * 
     */
    public final BoxLength padding = new BoxLength("scroll-padding");

    /**
     * <p>
     * The scrolling box scrolls instantly.
     * </p>
     * 
     * @return
     */
    public Scroll auto() {
        return value("scroll-behavior", "auto");
    }

    /**
     * <p>
     * The scrolling box scrolls in a smooth fashion using a user-agent-defined timing function over
     * a user-agent-defined period of time. User agents should follow platform conventions, if any.
     * </p>
     * 
     * @return
     */
    public Scroll smooth() {
        return value("scroll-behavior", "smooth");
    }
}