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
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import kiss.I;
import kiss.Managed;
import kiss.Singleton;
import stylist.PostProcessor;
import stylist.PropertyDefinition;
import stylist.Style;
import stylist.util.Properties;
import stylist.value.Numeric;
import stylist.value.Unit;

public class Grid extends PropertyDefinition<Grid> {

    /**
     * The CSS align-items property sets the align-self value on all direct children as a group. In
     * flexbox, it controls the alignment of items on the cross axis. In grid layout, it controls
     * the alignment of items on the block axis within their grid areas.
     */
    public final AlignItems<Grid> alignItems = new AlignItems<>(this);

    /**
     * The grid-template-columns CSS property defines the line names and track sizing functions of
     * the grid columns.
     */
    public final TemplateColumns templateColumns = new TemplateColumns();

    /**
     * The grid-template-columns CSS property defines the line names and track sizing functions of
     * the grid columns.
     */
    public final class TemplateColumns extends PropertyDefinition<Grid> implements SizeDefinable<Grid> {

        private TemplateColumns() {
            super("grid-template-columns", Grid.this);
        }

        /**
         * Is a non-negative length.
         * 
         * @param sizes
         * @return
         */
        @Override
        public Grid size(Numeric... sizes) {
            return value(name, List.of(sizes), " ");
        }

        public Grid repeat(double minSize, Unit minUnit, double maxSize, Unit maxUnit) {
            return repeat(Numeric.of(minSize, minUnit), Numeric.of(maxSize, maxUnit));
        }

        public Grid repeat(Numeric min, Numeric max) {
            return value(name, "repeat(auto-fit,minmax(" + min + "," + max + "))");
        }
    }

    /**
     * The grid-template-rows CSS property defines the line names and track sizing functions of the
     * grid rows.
     */
    public final TemplateRows templateRows = new TemplateRows();

    /**
     * The grid-template-rows CSS property defines the line names and track sizing functions of the
     * grid rows.
     */
    public final class TemplateRows extends PropertyDefinition<Grid> implements SizeDefinable<Grid> {

        private TemplateRows() {
            super("grid-template-rows", Grid.this);
        }

        /**
         * Is a non-negative length.
         * 
         * @param sizes
         * @return
         */
        @Override
        public Grid size(Numeric... sizes) {
            return value(name, List.of(sizes), " ");
        }

        public Grid repeat(double minSize, Unit minUnit, double maxSize, Unit maxUnit) {
            return repeat(Numeric.of(minSize, minUnit), Numeric.of(maxSize, maxUnit));
        }

        public Grid repeat(Numeric min, Numeric max) {
            return value(name, "repeat(auto-fit,minmax(" + min + "," + max + "))");
        }
    }

    /**
     * This property is specified as a value for <'row-gap'>, followed optionally by a value for
     * <'column-gap'>. If <'column-gap'> is omitted, it is set to the same value as <'row-gap'>.
     * Both <'row-gap'> and <'column-gap'> can each be specified as a <length> or a <percentage>.
     * 
     * @return
     */
    public Grid gap(double gap, Unit unit) {
        return rowGap(gap, unit).columnGap(gap, unit);
    }

    /**
     * This property is specified as a value for <'row-gap'>, followed optionally by a value for
     * <'column-gap'>. If <'column-gap'> is omitted, it is set to the same value as <'row-gap'>.
     * Both <'row-gap'> and <'column-gap'> can each be specified as a <length> or a <percentage>.
     * 
     * @return
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
     * @return
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
     * @return
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

    public Grid templateAreas(Style... areas) {
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