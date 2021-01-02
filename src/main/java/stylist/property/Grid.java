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
}