/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import java.util.Objects;
import java.util.StringJoiner;

import stylist.value.Numeric;
import stylist.value.Unit;

public class Query {

    private String type;

    private String orientation;

    private String hover;

    private Numeric maxWidth;

    private Numeric minWidth;

    private Numeric maxHeight;

    private Numeric minHeight;

    /**
     * Hide constructor.
     */
    private Query(String type) {
        this.type = "@" + type;
    }

    /**
     * Set the width.
     */
    public Query width(int min, int max, Unit unit) {
        this.minWidth = Numeric.of(min, unit);
        this.maxWidth = Numeric.of(max, unit);
        return this;
    }

    /**
     * Set the max-width.
     * 
     * @param width
     * @return
     */
    public Query maxWidth(int width, Unit unit) {
        this.maxWidth = Numeric.of(width, unit);
        return this;
    }

    /**
     * Set the min-width.
     * 
     * @param width
     * @return
     */
    public Query minWidth(int width, Unit unit) {
        this.minWidth = Numeric.of(width, unit);
        return this;
    }

    /**
     * Set the height.
     */
    public Query height(int min, int max, Unit unit) {
        this.minHeight = Numeric.of(min, unit);
        this.maxHeight = Numeric.of(max, unit);
        return this;
    }

    /**
     * Set the max-height.
     * 
     * @param height
     * @return
     */
    public Query maxHeight(int height, Unit unit) {
        this.maxHeight = Numeric.of(height, unit);
        return this;
    }

    /**
     * Set the min-height.
     * 
     * @param height
     * @return
     */
    public Query minHeight(int height, Unit unit) {
        this.minHeight = Numeric.of(height, unit);
        return this;
    }

    /**
     * Set the orientation.
     * 
     * @return
     */
    public Query landscape() {
        this.orientation = "landscape";
        return this;
    }

    /**
     * Set the orientation.
     * 
     * @return
     */
    public Query portrait() {
        this.orientation = "portrait";
        return this;
    }

    /**
     * Set the device type.
     * 
     * @return
     */
    public Query hover() {
        this.hover = "hover";
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(hover, maxHeight, maxWidth, minHeight, minWidth, orientation, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Query other = (Query) obj;
        return Objects.equals(hover, other.hover) && Objects.equals(maxHeight, other.maxHeight) && Objects
                .equals(maxWidth, other.maxWidth) && Objects.equals(minHeight, other.minHeight) && Objects
                        .equals(minWidth, other.minWidth) && Objects
                                .equals(orientation, other.orientation) && Objects.equals(type, other.type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringJoiner join = new StringJoiner(") and (", type + " (", ")").setEmptyValue(type);
        if (orientation != null) {
            join.add("orientation:" + orientation);
        }
        if (hover != null) {
            join.add("hover:" + hover);
        }

        if (minHeight != null && maxHeight != null) {
            join.add(minHeight + " <= height < " + maxHeight);
        } else if (minHeight != null) {
            join.add(minHeight + " <= height");
        } else if (maxHeight != null) {
            join.add("height < " + maxHeight);
        }

        if (minWidth != null && maxWidth != null) {
            join.add(minWidth + " <= width < " + maxWidth);
        } else if (minWidth != null) {
            join.add(minWidth + " <= width");
        } else if (maxWidth != null) {
            join.add("width < " + maxWidth);
        }
        return join.toString();
    }

    /**
     * Create {@link Query} for container.
     * 
     * @return
     */
    public static Query container() {
        return new Query("container");
    }

    /**
     * Create {@link Query} for the named container.
     * 
     * @return
     */
    public static Query container(String name) {
        return new Query("container " + name);
    }

    /**
     * Create {@link Query} for all.
     * 
     * @return
     */
    public static Query all() {
        return new Query("media all");
    }

    /**
     * Create {@link Query} for print.
     * 
     * @return
     */
    public static Query print() {
        return new Query("media print");
    }

    /**
     * Create {@link Query} for screen.
     * 
     * @return
     */
    public static Query screen() {
        return new Query("media screen");
    }

    /**
     * Create {@link Query} for speech.
     * 
     * @return
     */
    public static Query speech() {
        return new Query("media speech");
    }
}