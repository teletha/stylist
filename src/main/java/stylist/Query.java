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

    private String media;

    private String orientation;

    private String hover;

    private Numeric maxWidth;

    private Numeric minWidth;

    private Numeric maxHeight;

    private Numeric minHeight;

    /**
     * Hide constructor.
     */
    private Query(String type, String media) {
        this.type = "@" + type;
        this.media = media;
    }

    /**
     * Set the width.
     */
    public Query width(int min, Unit unit) {
        this.minWidth = Numeric.num(min, unit);
        return this;
    }

    /**
     * Set the width.
     */
    public Query width(int min, int max, Unit unit) {
        this.minWidth = Numeric.num(min, unit);
        this.maxWidth = Numeric.num(max, unit);
        return this;
    }

    /**
     * Set the height.
     */
    public Query height(int min, Unit unit) {
        this.minHeight = Numeric.num(min, unit);
        return this;
    }

    /**
     * Set the height.
     */
    public Query height(int min, int max, Unit unit) {
        this.minHeight = Numeric.num(min, unit);
        this.maxHeight = Numeric.num(max, unit);
        return this;
    }

    /**
     * Set the inline size.
     */
    public Query inline(int min, Unit unit) {
        this.minWidth = Numeric.num(min, unit);
        return this;
    }

    /**
     * Set the inline size.
     */
    public Query inline(int min, int max, Unit unit) {
        this.minWidth = Numeric.num(min, unit);
        this.maxWidth = Numeric.num(max, unit);
        return this;
    }

    /**
     * Set the block size.
     */
    public Query block(int min, Unit unit) {
        this.minHeight = Numeric.num(min, unit);
        return this;
    }

    /**
     * Set the block size.
     */
    public Query block(int min, int max, Unit unit) {
        this.minHeight = Numeric.num(min, unit);
        this.maxHeight = Numeric.num(max, unit);
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
        StringJoiner join = new StringJoiner(" and ", type + " ", "").setEmptyValue(type);
        if (media != null) {
            join.add(media);
        }

        if (orientation != null) {
            join.add("(orientation:" + orientation + ")");
        }
        if (hover != null) {
            join.add("(hover:" + hover + ")");
        }

        if (minHeight != null && maxHeight != null) {
            join.add("(" + minHeight + " <= height < " + maxHeight + ")");
        } else if (minHeight != null) {
            join.add("(" + minHeight + " <= height)");
        } else if (maxHeight != null) {
            join.add("(height < " + maxHeight + ")");
        }

        if (minWidth != null && maxWidth != null) {
            join.add("(" + minWidth + " <= width < " + maxWidth + ")");
        } else if (minWidth != null) {
            join.add("(" + minWidth + " <= width)");
        } else if (maxWidth != null) {
            join.add("(width < " + maxWidth + ")");
        }
        return join.toString();
    }

    /**
     * Create {@link Query} for container.
     * 
     * @return
     */
    public static Query container() {
        return new Query("container", null);
    }

    /**
     * Create {@link Query} for the named container.
     * 
     * @return
     */
    public static Query container(String name) {
        return new Query("container " + name, null);
    }

    /**
     * Create {@link Query} for all.
     * 
     * @return
     */
    public static Query all() {
        return new Query("media", "all");
    }

    /**
     * Create {@link Query} for print.
     * 
     * @return
     */
    public static Query print() {
        return new Query("media", "print");
    }

    /**
     * Create {@link Query} for screen.
     * 
     * @return
     */
    public static Query screen() {
        return new Query("media", "screen");
    }

    /**
     * Create {@link Query} for speech.
     * 
     * @return
     */
    public static Query speech() {
        return new Query("media", "speech");
    }
}