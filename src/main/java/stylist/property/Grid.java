/*
 * Copyright (C) 2023 The STYLIST Development Team
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
import stylist.value.Numeric;
import stylist.value.Unit;

public class Grid extends PropertyDefinition<Grid> {

    /**
     * The grid-template-columns CSS property defines the line names and track sizing functions of
     * the grid columns.
     */
    public final TemplateColumns templateColumns = new TemplateColumns();

    /**
     * 
     */
    public final class TemplateColumns extends PropertyDefinition<Grid> {

        /**
         * 
         */
        private TemplateColumns() {
            super("grid-template-columns", Grid.this);
        }

        /**
         * Is a non-negative length.
         * 
         * @param widths
         * @return
         */
        public Grid width(Numeric... widths) {
            return value("grid-template-columns", List.of(widths), " ");
        }

        public Grid repeat(double minSize, Unit minUnit, double maxSize, Unit maxUnit) {
            return repeat(Numeric.of(minSize, minUnit), Numeric.of(maxSize, maxUnit));
        }

        public Grid repeat(Numeric min, Numeric max) {
            return value("grid-template-columns", "repeat(auto-fit,minmax(" + min + "," + max + "))");
        }
    }

    /**
     * The column-gap CSS property sets the size of the gap (gutter) between an element's columns.
     * Initially a part of Multi-column Layout, the definition of column-gap has been broadened to
     * include multiple layout methods. Now specified in Box Alignment, it may be used in
     * Multi-column, Flexible Box, and Grid layouts.
     * 
     * @param size
     * @param unit
     * @return
     */
    public Grid columnGap(double size, Unit unit) {
        return columnGap(Numeric.of(size, unit));
    }

    /**
     * The column-gap CSS property sets the size of the gap (gutter) between an element's columns.
     * Initially a part of Multi-column Layout, the definition of column-gap has been broadened to
     * include multiple layout methods. Now specified in Box Alignment, it may be used in
     * Multi-column, Flexible Box, and Grid layouts.
     * 
     * @param size
     * @return
     */
    public Grid columnGap(Numeric size) {
        value("column-gap", size);

        return this;
    }

    /**
     * The row-gap CSS property sets the size of the gap (gutter) between an element's rows. Early
     * versions of the specification called this property grid-row-gap, and to maintain
     * compatibility with legacy websites, browsers will still accept grid-row-gap as an alias for
     * row-gap.
     * 
     * @param size
     * @param unit
     * @return
     */
    public Grid rowGap(double size, Unit unit) {
        return rowGap(Numeric.of(size, unit));
    }

    /**
     * The row-gap CSS property sets the size of the gap (gutter) between an element's rows. Early
     * versions of the specification called this property grid-row-gap, and to maintain
     * compatibility with legacy websites, browsers will still accept grid-row-gap as an alias for
     * row-gap.
     * 
     * @param size
     * @return
     */
    public Grid rowGap(Numeric size) {
        value("row-gap", size);

        return this;
    }
}