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

import static stylist.value.Vendor.*;

import stylist.PropertyDefinition;

public class Flex extends PropertyDefinition<Flex> {

    /**
     * The CSS flex-direction property specifies how flex items are placed in the flex container
     * defining the main axis and the direction (normal or reversed).
     */
    public final Direction direction = new Direction();

    /**
     * The CSS flex-wrap property specifies whether the children are forced into a single line or if
     * the items can be flowed on multiple lines.
     */
    public final Wrap wrap = new Wrap();

    /**
     * The CSS align-content property aligns a flex container's lines within the flex container when
     * there is extra space on the cross-axis. This property has no effect on single line flexible
     * boxes.
     */
    public final AlignContent alignContent = new AlignContent();

    /**
     * The CSS align-items property aligns flex items of the current flex line the same way as
     * justify-content but in the perpendicular direction.
     */
    public final AlignItems<Flex> alignItems = new AlignItems<>("align-items", this);

    /**
     * The CSS justify-items property defines the default justify-self for all items of the box,
     * giving them all a default way of justifying each box along the appropriate axis.
     */
    public final AlignItems<Flex> justifyItems = new AlignItems<>("justify-items", this);

    /**
     * The CSS justify-content property defines how a browser distributes available space between
     * and around elements when aligning flex items in the main-axis of the current line. The
     * alignment is done after the lengths and auto margins are applied, meaning that, if there is
     * at least one flexible element, with flex-grow different than 0, it will have no effect as
     * there won't be any available space.
     */
    public final JustifyContent justifyContent = new JustifyContent();

    /**
     * @version 2014/10/21 16:21:12
     */
    public class Direction extends PropertyDefinition<Flex> {

        /**
         * 
         */
        private Direction() {
            super("flex-direction", Flex.this, Webkit);
        }

        /**
         * <p>
         * Initial value. Child elements are displayed in the same order that they are declared in
         * the source document, from left to right.
         * </p>
         * 
         * @return
         */
        public Flex row() {
            return value("row");
        }

        /**
         * <p>
         * Child elements are displayed in the reverse order that they are declared in the source
         * document, from right to left.
         * </p>
         * 
         * @return
         */
        public Flex rowReverse() {
            return value("row-reverse");
        }

        /**
         * <p>
         * Child elements are displayed in the same order that they are declared in the source
         * document, from top to bottom.
         * </p>
         * 
         * @return
         */
        public Flex column() {
            return value("column");
        }

        /**
         * <p>
         * Child elements are displayed in the reverse order that they are declared in the source
         * document, from bottom to top.
         * </p>
         * 
         * @return
         */
        public Flex columnReverse() {
            return value("column-reverse");
        }
    }

    /**
     * @version 2013/07/22 11:39:30
     */
    public class Wrap extends PropertyDefinition<Flex> {

        /**
         * 
         */
        private Wrap() {
            super("flex-wrap", Flex.this, Webkit);
        }

        /**
         * <p>
         * The flex items break into multiple lines. The cross-start is either equivalent to start
         * or before depending flex-direction value and the cross-end is the opposite of the
         * specified cross-start.
         * </p>
         * 
         * @return
         */
        public Flex enable() {
            return value("wrap");
        }

        /**
         * <p>
         * The flex items are laid out in a single line which may cause the flex container to
         * overflow. The cross-start is either equivalent to start or before depending
         * flex-direction value.
         * </p>
         * 
         * @return
         */
        public Flex disable() {
            return value("nowrap");
        }

        /**
         * <p>
         * Behaves the same as wrap but cross-start and cross-end are permuted
         * </p>
         * 
         * @return
         */
        public Flex reverse() {
            return value("wrap-reverse");
        }
    }

    /**
     * @version 2013/07/22 11:27:09
     */
    public class JustifyContent extends PropertyDefinition<Flex> {

        /**
         * 
         */
        private JustifyContent() {
            super("justify-content", Flex.this, Webkit);
        }

        /**
         * <p>
         * The flex items are packed starting from the main-start. Margins of the first flex item is
         * flushed with the main-start edge of the line and each following flex item is flushed with
         * the preceding.
         * </p>
         * 
         * @return
         */
        public Flex start() {
            return value("flex-start");
        }

        /**
         * <p>
         * The flex items are packed starting from the main-end. The margin edge of the last flex
         * item is flushed with the main-end edge of the line and each preceding flex item is
         * flushed with the following.
         * </p>
         * 
         * @return
         */
        public Flex end() {
            return value("flex-end");
        }

        /**
         * <p>
         * The flex items are packed toward the center of the line. The flex items are flushed with
         * each other and aligned in the center of the line. Space between the main-start edge of
         * the line and first item and between main-end and the last item of the line is the same.
         * </p>
         * 
         * @return
         */
        public Flex center() {
            return value("center");
        }

        /**
         * <p>
         * Flex items are evenly distributed along the line. The spacing is done such as the space
         * between two adjacent items is the same. Main-start edge and main-end edge are flushed
         * with respectively first and last flex item edges.
         * </p>
         * 
         * @return
         */
        public Flex spaceBetween() {
            return value("space-between");
        }

        /**
         * <p>
         * Flex items are evenly distributed so that the space between two adjacent items is the
         * same. The empty space before the first and after the last items equals half of the space
         * between two adjacent items.
         * </p>
         * 
         * @return
         */
        public Flex spaceAround() {
            return value("space-around");
        }
    }

    /**
     * @version 2013/07/22 11:33:09
     */
    public class AlignContent extends PropertyDefinition<Flex> {

        /**
         * 
         */
        private AlignContent() {
            super("align-content", Flex.this, Webkit);
        }

        /**
         * <p>
         * Lines are packed starting from the cross-start. Cross-start edge of the first line and
         * cross-start edge of the flex container are flushed together. Each following line is flush
         * with the preceding.
         * </p>
         * 
         * @return
         */
        public Flex start() {
            return value("flex-start");
        }

        /**
         * <p>
         * Lines are packed starting from the cross-end. Cross-end of the last line and cross-end of
         * the flex container are flushed together. Each preceding line is flushed with the
         * following line.
         * </p>
         * 
         * @return
         */
        public Flex end() {
            return value("flex-end");
        }

        /**
         * <p>
         * Lines are packed toward the center of the flex container. The lines are flushed with each
         * other and aligned in the center of the flex container. Space between the cross-start edge
         * of the flex container and first line and between cross-end of the flex container and the
         * last line is the same.
         * </p>
         * 
         * @return
         */
        public Flex center() {
            return value("center");
        }

        /**
         * <p>
         * Lines are evenly distributed in the flex container. The spacing is done such as the space
         * between two adjacent items is the same. Cross-start edge and cross-end edge of the flex
         * container are flushed with respectively first and last line edges.
         * </p>
         * 
         * @return
         */
        public Flex spaceBetween() {
            return value("space-between");
        }

        /**
         * <p>
         * Lines are evenly distributed so that the space between two adjacent lines is the same.
         * The empty space before the first and after the last lines equals half of the space
         * between two adjacent lines.
         * </p>
         * 
         * @return
         */
        public Flex spaceAround() {
            return value("space-around");
        }

        /**
         * <p>
         * Lines stretch to use the remaining space. The free-space is split equally between all the
         * lines.
         * </p>
         * 
         * @return
         */
        public Flex stretch() {
            return value("stretch");
        }
    }
}