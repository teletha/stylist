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

import java.util.List;

import stylist.PropertyDefinition;
import stylist.property.helper.ColorHelper;
import stylist.value.CSSValue;

public class Scroll extends PropertyDefinition<Scroll> implements ColorHelper<Scroll> {

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
     * The scroll-snap-type CSS property sets how strictly snap points are enforced on the scroll
     * container if there are any.
     */
    public final SnapType snapType = new SnapType();

    /**
     * The scroll-snap-align CSS property specifies the box's snap position as an alignment of its
     * snap area within the snap port.
     */
    public final SnapAlign snapAlign = new SnapAlign();

    /**
     * The scroll-snap-stop CSS property defines whether the scroll container is allowed to "pass
     * over" possible snap positions.
     */
    public final SnapStop snapStop = new SnapStop();

    /**
     * The overscroll-behavior CSS property sets what a browser does when reaching the boundary of a
     * scrolling area.
     */
    public final Overscroll overscroll = new Overscroll();

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

    /**
     * <p>
     * A thin scrollbar width variant on platforms that provide that option, or a thinner
     * scrollbar than the default platform scrollbar width.
     * </p>
     * 
     * @return
     */
    public Scroll thin() {
        return value("scrollbar-width", "thin");
    }

    /**
     * <p>
     * No scrollbar shown, however the element will still be scrollable.
     * </p>
     * 
     * @return
     */
    public Scroll none() {
        return value("scrollbar-width", "none");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scroll color(CSSValue color) {
        return color(color, color);
    }

    public Scroll color(CSSValue color, CSSValue back) {
        return value("scrollbar-color", List.of(color, back), " ");
    }

    public class SnapType extends PropertyDefinition<Scroll> {

        private SnapType() {
            super("scroll-snap-type", Scroll.this);
        }

        /**
         * The scroll container ignores snap points.
         * 
         * @return Chainable API.
         */
        public Scroll none() {
            return value("none");
        }

        /**
         * The scroll container snaps to snap positions in its horizontal axis, and snapping is
         * mandatory.
         * 
         * @return Chainable API.
         */
        public Scroll xMandatory() {
            return value("x mandatory");
        }

        /**
         * The scroll container snaps to snap positions in its vertical axis, and snapping is
         * mandatory.
         * 
         * @return Chainable API.
         */
        public Scroll yMandatory() {
            return value("y mandatory");
        }

        /**
         * The scroll container snaps to snap positions in both directions, and snapping is
         * mandatory.
         * 
         * @return Chainable API.
         */
        public Scroll bothMandatory() {
            return value("both mandatory");
        }

        /**
         * The scroll container snaps to snap positions in its block axis, and snapping is
         * mandatory.
         * 
         * @return Chainable API.
         */
        public Scroll blockMandatory() {
            return value("block mandatory");
        }

        /**
         * The scroll container snaps to snap positions in its inline axis, and snapping is
         * mandatory.
         * 
         * @return Chainable API.
         */
        public Scroll inlineMandatory() {
            return value("inline mandatory");
        }

        /**
         * The scroll container snaps to snap positions in its horizontal axis, and snapping is on a
         * best-effort basis.
         * 
         * @return Chainable API.
         */
        public Scroll xProximity() {
            return value("x proximity");
        }

        /**
         * The scroll container snaps to snap positions in its vertical axis, and snapping is on a
         * best-effort basis.
         * 
         * @return Chainable API.
         */
        public Scroll yProximity() {
            return value("y proximity");
        }

        /**
         * The scroll container snaps to snap positions in both directions, and snapping is on a
         * best-effort basis.
         * 
         * @return Chainable API.
         */
        public Scroll bothProximity() {
            return value("both proximity");
        }

        /**
         * The scroll container snaps to snap positions in its block axis, and snapping is on a
         * best-effort basis.
         * 
         * @return Chainable API.
         */
        public Scroll blockProximity() {
            return value("block proximity");
        }

        /**
         * The scroll container snaps to snap positions in its inline axis, and snapping is on a
         * best-effort basis.
         * 
         * @return Chainable API.
         */
        public Scroll inlineProximity() {
            return value("inline proximity");
        }
    }

    public class SnapAlign extends PropertyDefinition<Scroll> {

        private SnapAlign() {
            super("scroll-snap-align", Scroll.this);
        }

        /**
         * No snap alignment is enforced.
         * 
         * @return Chainable API.
         */
        public Scroll none() {
            return value("none");
        }

        /**
         * The snap area is aligned to the start of the snap port.
         * 
         * @return Chainable API.
         */
        public Scroll start() {
            return value("start");
        }

        /**
         * The snap area is aligned to the end of the snap port.
         * 
         * @return Chainable API.
         */
        public Scroll end() {
            return value("end");
        }

        /**
         * The snap area is aligned to the center of the snap port.
         * 
         * @return Chainable API.
         */
        public Scroll center() {
            return value("center");
        }
    }

    public class SnapStop extends PropertyDefinition<Scroll> {

        private SnapStop() {
            super("scroll-snap-stop", Scroll.this);
        }

        /**
         * The scroll container may pass over possible snap positions.
         * 
         * @return Chainable API.
         */
        public Scroll normal() {
            return value("normal");
        }

        /**
         * The scroll container must not pass over possible snap positions.
         * 
         * @return Chainable API.
         */
        public Scroll always() {
            return value("always");
        }
    }

    public class Overscroll extends PropertyDefinition<Scroll> {

        private Overscroll() {
            super("overscroll-behavior", Scroll.this);
        }

        /**
         * The default scroll overflow behavior occurs.
         * 
         * @return Chainable API.
         */
        public Scroll auto() {
            return value("auto");
        }

        /**
         * Default scroll overflow behavior is observed inside the element, but the underlying
         * elements are not scrolled.
         * 
         * @return Chainable API.
         */
        public Scroll contain() {
            return value("contain");
        }

        /**
         * No scroll chaining occurs to neighboring scrolling areas.
         * 
         * @return Chainable API.
         */
        public Scroll none() {
            return value("none");
        }

        /**
         * Set the horizontal overscroll behavior.
         * 
         * @param behavior An overscroll behavior.
         * @return Chainable API.
         */
        public Scroll x(Behavior behavior) {
            return value("overscroll-behavior-x", behavior.value);
        }

        /**
         * Set the vertical overscroll behavior.
         * 
         * @param behavior An overscroll behavior.
         * @return Chainable API.
         */
        public Scroll y(Behavior behavior) {
            return value("overscroll-behavior-y", behavior.value);
        }
    }

    public enum Behavior {
        auto,
        contain,
        none;

        public final String value;

        private Behavior() {
            this.value = name().toLowerCase();
        }
    }
}