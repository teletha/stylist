/*
 * Copyright (C) 2021 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import stylist.PropertyDefinition;
import stylist.property.helper.ColorHelper;
import stylist.property.helper.WidthHelper;
import stylist.value.CSSValue;
import stylist.value.Numeric;

/**
 * @version 2014/10/29 12:49:59
 */
public class Overflows extends Overflow<Overflows> {

    /**
     * <p>
     * The overflow-x CSS property specifies whether to clip content, render a scroll bar or display
     * overflow content of a block-level element, when it overflows at the left and right edges.
     * </p>
     * <p>
     * Using the overflow property with a value different than visible, its default, will create a
     * new block formatting context. This is technically necessary as if a float would intersect
     * with the scrolling element it would force to rewrap the content of the scrollable element
     * around intruding floats. The rewrap would happen after each scroll step and would be lead to
     * a far too slow scrolling experience. Note that, by programmatically setting scrollTop to the
     * relevant HTML element, even when overflow has the hidden value an element may need to scroll.
     * </p>
     */
    public final Overflow<Overflows> x = new Overflow("overflow-x", this);

    /**
     * <p>
     * The overflow-x CSS property specifies whether to clip content, render a scroll bar or display
     * overflow content of a block-level element, when it overflows at the left and right edges.
     * </p>
     * <p>
     * Using the overflow property with a value different than visible, its default, will create a
     * new block formatting context. This is technically necessary as if a float would intersect
     * with the scrolling element it would force to rewrap the content of the scrollable element
     * around intruding floats. The rewrap would happen after each scroll step and would be lead to
     * a far too slow scrolling experience. Note that, by programmatically setting scrollTop to the
     * relevant HTML element, even when overflow has the hidden value an element may need to scroll.
     * </p>
     */
    public final Overflow<Overflows> y = new Overflow("overflow-y", this);

    /**
     * The scrollbar-color CSS property sets the color of the scrollbar track and thumb. The track
     * refers to the background of the scrollbar, which is generally fixed regardless of the
     * scrolling position. The thumb refers to the moving part of the scrollbar, which usually
     * floats on top of the track. The scrollbar-width property allows the author to set the maximum
     * thickness of an element’s scrollbars when they are shown.
     */
    public final Scrollbar scrollbar = new Scrollbar();

    /**
     * Create property.
     */
    public Overflows() {
        super("overflow");
    }

    /**
     * The scrollbar-color CSS property sets the color of the scrollbar track and thumb. The track
     * refers to the background of the scrollbar, which is generally fixed regardless of the
     * scrolling position. The thumb refers to the moving part of the scrollbar, which usually
     * floats on top of the track. The scrollbar-width property allows the author to set the maximum
     * thickness of an element’s scrollbars when they are shown.
     */
    public class Scrollbar<T extends Scrollbar> extends PropertyDefinition<Overflows>
            implements ColorHelper<Overflows>, WidthHelper<Overflows> {

        /**
         * Hide
         */
        private Scrollbar() {
            super("scrollbar", Overflows.this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Overflows color(CSSValue color) {
            return value("scrollbar-color", color);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Overflows width(Numeric width) {
            return value("scrollbar-width", width);
        }

        /**
         * <p>
         * A thin scrollbar width variant on platforms that provide that option, or a thinner
         * scrollbar than the default platform scrollbar width.
         * </p>
         * 
         * @return
         */
        public Overflows thin() {
            return value("scrollbar-width", "thin");
        }

        /**
         * <p>
         * No scrollbar shown, however the element will still be scrollable.
         * </p>
         * 
         * @return
         */
        public Overflows none() {
            return value("scrollbar-width", "none");
        }

        /**
         * <p>
         * The default scrollbar width for the platform.
         * </p>
         * 
         * @return
         */
        public Overflows auto() {
            return value("scrollbar-width", "auto");
        }
    }
}