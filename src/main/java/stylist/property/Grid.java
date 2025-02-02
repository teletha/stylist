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

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.UnaryOperator;

import kiss.I;
import kiss.Managed;
import kiss.Singleton;
import stylist.PostProcessor;
import stylist.PropertyDefinition;
import stylist.Style;
import stylist.property.helper.GridNumerics;
import stylist.property.helper.Items;
import stylist.util.Properties;
import stylist.value.Numeric;
import stylist.value.Unit;

public class Grid extends PropertyDefinition<Grid> {

    /**
     * The CSS align-items property sets the align-self value on all direct children as a group. In
     * flexbox, it controls the alignment of items on the cross axis. In grid layout, it controls
     * the alignment of items on the block axis within their grid areas.
     * 
     * @return Chainable API
     */
    public Grid align(Items align) {
        return value("align-items", align.value);
    }

    /**
     * The CSS align-items property sets the align-self value on all direct children as a group. In
     * flexbox, it controls the alignment of items on the cross axis. In grid layout, it controls
     * the alignment of items on the block axis within their grid areas.
     * 
     * @return Chainable API
     */
    public Grid align(stylist.property.helper.Content align) {
        return value("align-content", align.value);
    }

    /**
     * The CSS justify-items property defines the default justify-self for all items of the box,
     * giving them all a default way of justifying each box along the appropriate axis.
     * 
     * @return Chainable API
     */
    public Grid justify(Items justify) {
        return value("justify-items", justify.value);
    }

    /**
     * The CSS justify-items property defines the default justify-self for all items of the box,
     * giving them all a default way of justifying each box along the appropriate axis.
     * 
     * @return Chainable API
     */
    public Grid justify(stylist.property.helper.Content justify) {
        return value("justify-content", justify.value);
    }

    /**
     * The CSS place-items shorthand property aligns items along both the block and inline
     * directions at once. It sets the values of the align-items and justify-items properties. If
     * the second value is not set, the first value is also used for it.
     * 
     * @return Chainable API
     */
    public Grid place(Items alignAndJustify) {
        return value("place-items", alignAndJustify.value);
    }

    /**
     * The grid-template-columns CSS property defines the line names and track sizing functions of
     * the grid columns.
     * 
     * @return Chainable API
     */
    public Grid column(UnaryOperator<GridNumerics> values) {
        return value("grid-template-columns", values.apply(new GridNumerics()));
    }

    /**
     * The grid-template-columns CSS property defines the line names and track sizing functions of
     * the grid columns.
     * 
     * @return Chainable API
     */
    public Grid column(Numeric... values) {
        return column(x -> x.size(values));
    }

    /**
     * The grid-template-rows CSS property defines the line names and track sizing functions of the
     * grid rows.
     * 
     * @return Chainable API
     */
    public Grid row(UnaryOperator<GridNumerics> values) {
        return value("grid-template-rows", values.apply(new GridNumerics()));
    }

    /**
     * The grid-template-rows CSS property defines the line names and track sizing functions of the
     * grid rows.
     * 
     * @return Chainable API
     */
    public Grid row(Numeric... values) {
        return row(x -> x.size(values));
    }

    /**
     * This property is specified as a value for <'row-gap'>, followed optionally by a value for
     * <'column-gap'>. If <'column-gap'> is omitted, it is set to the same value as <'row-gap'>.
     * Both <'row-gap'> and <'column-gap'> can each be specified as a <length> or a <percentage>.
     * 
     * @return Chainable API
     */
    public Grid gap(double gap, Unit unit) {
        return rowGap(gap, unit).columnGap(gap, unit);
    }

    /**
     * This property is specified as a value for <'row-gap'>, followed optionally by a value for
     * <'column-gap'>. If <'column-gap'> is omitted, it is set to the same value as <'row-gap'>.
     * Both <'row-gap'> and <'column-gap'> can each be specified as a <length> or a <percentage>.
     * 
     * @return Chainable API
     */
    public Grid gap(Numeric gap) {
        return rowGap(gap).columnGap(gap);
    }

    /**
     * This property is specified as a value for <'row-gap'>, followed optionally by a value for
     * <'column-gap'>. If <'column-gap'> is omitted, it is set to the same value as <'row-gap'>.
     * Both <'row-gap'> and <'column-gap'> can each be specified as a <length> or a <percentage>.
     * 
     * @param row
     * @param rowUnit
     * @param column
     * @param columnUnit
     * @return Chainable API
     */
    public Grid gap(double row, Unit rowUnit, double column, Unit columnUnit) {
        return rowGap(row, rowUnit).columnGap(column, columnUnit);
    }

    /**
     * This property is specified as a value for <'row-gap'>, followed optionally by a value for
     * <'column-gap'>. If <'column-gap'> is omitted, it is set to the same value as <'row-gap'>.
     * Both <'row-gap'> and <'column-gap'> can each be specified as a <length> or a <percentage>.
     * 
     * @param row
     * @param column
     * @return Chainable API
     */
    public Grid gap(Numeric row, Numeric column) {
        return rowGap(row).columnGap(column);
    }

    /**
     * The column-gap CSS property sets the size of the gap (gutter) between an element's columns.
     * Initially a part of Multi-column Layout, the definition of column-gap has been broadened to
     * include multiple layout methods. Now specified in Box Alignment, it may be used in
     * Multi-column, Flexible Box, and Grid layouts.
     * 
     * @param size
     * @param unit
     * @return Chainable API
     */
    public Grid columnGap(double size, Unit unit) {
        return columnGap(Numeric.num(size, unit));
    }

    /**
     * The column-gap CSS property sets the size of the gap (gutter) between an element's columns.
     * Initially a part of Multi-column Layout, the definition of column-gap has been broadened to
     * include multiple layout methods. Now specified in Box Alignment, it may be used in
     * Multi-column, Flexible Box, and Grid layouts.
     * 
     * @param size
     * @return Chainable API
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
     * @return Chainable API
     */
    public Grid rowGap(double size, Unit unit) {
        return rowGap(Numeric.num(size, unit));
    }

    /**
     * The row-gap CSS property sets the size of the gap (gutter) between an element's rows. Early
     * versions of the specification called this property grid-row-gap, and to maintain
     * compatibility with legacy websites, browsers will still accept grid-row-gap as an alias for
     * row-gap.
     * 
     * @param size
     * @return Chainable API
     */
    public Grid rowGap(Numeric size) {
        value("row-gap", size);

        return this;
    }

    /**
     * The grid-auto-flow CSS property controls how the auto-placement algorithm works, specifying
     * exactly how auto-placed items get flowed into the grid.
     * 
     * Items are placed by filling each row in turn, adding new rows as necessary. If neither row
     * nor column is provided, row is assumed.
     * 
     * @return Chainable API
     */
    public Grid flowRow() {
        value("grid-auto-flow", "row");
        return this;
    }

    /**
     * The grid-auto-flow CSS property controls how the auto-placement algorithm works, specifying
     * exactly how auto-placed items get flowed into the grid.
     * 
     * Items are placed by filling each column in turn, adding new columns as necessary.
     * 
     * @return Chainable API
     */
    public Grid flowColumn() {
        value("grid-auto-flow", "column");
        return this;
    }

    /**
     * The grid-auto-flow CSS property controls how the auto-placement algorithm works, specifying
     * exactly how auto-placed items get flowed into the grid.
     * 
     * "dense" packing algorithm attempts to fill in holes earlier in the grid, if smaller items
     * come up later. This may cause items to appear out-of-order, when doing so would fill in holes
     * left by larger items.
     * 
     * If it is omitted, a "sparse" algorithm is used, where the placement algorithm only ever moves
     * "forward" in the grid when placing items, never backtracking to fill holes. This ensures that
     * all of the auto-placed items appear "in order", even if this leaves holes that could have
     * been filled by later items.
     * 
     * @return Chainable API
     */
    public Grid flowDense() {
        value("grid-auto-flow", "dense");
        return this;
    }

    public Grid area(Style... areas) {
        GridAreaProcessor post = I.make(GridAreaProcessor.class);

        StringJoiner joiner = new StringJoiner(" ", "\"", "\"");
        for (Style area : areas) {
            if (area == null) {
                joiner.add(".");
            } else {
                String name = area.selector().substring(1);
                joiner.add(name);
                post.selectors.add(name);
            }
        }
        return value("grid-template-areas", readValueAsString("grid-template-areas", "") + joiner.toString());
    }

    @Managed(Singleton.class)
    private static class GridAreaProcessor implements PostProcessor {

        private Set<String> selectors = new HashSet();

        /**
         * {@inheritDoc}
         */
        @Override
        public void accept(String selector, Properties properties) {
            String name = selector.substring(1);
            if (selectors.contains(name)) {
                properties.set("grid-area", name);
            }
        }
    }
}