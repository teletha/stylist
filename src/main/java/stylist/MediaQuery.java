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

import stylist.value.Numeric;
import stylist.value.Unit;

public class MediaQuery {

    private String type;

    private String orientation;

    private String hover;

    private Numeric maxWidth;

    private Numeric minWidth;

    private Numeric maxHeight;

    private Numeric minHeight;

    /**
     * Hide constructor.
     * 
     * @param type A media type.
     */
    private MediaQuery(String type) {
        this.type = type;
    }

    /**
     * Set the max-width.
     * 
     * @param width
     * @return
     */
    public MediaQuery maxWidth(int width, Unit unit) {
        this.maxWidth = Numeric.of(width, unit);
        return this;
    }

    /**
     * Set the min-width.
     * 
     * @param width
     * @return
     */
    public MediaQuery minWidth(int width, Unit unit) {
        this.minWidth = Numeric.of(width, unit);
        return this;
    }

    /**
     * Set the max-height.
     * 
     * @param height
     * @return
     */
    public MediaQuery maxHeight(int height, Unit unit) {
        this.maxHeight = Numeric.of(height, unit);
        return this;
    }

    /**
     * Set the min-height.
     * 
     * @param height
     * @return
     */
    public MediaQuery minHeight(int height, Unit unit) {
        this.minHeight = Numeric.of(height, unit);
        return this;
    }

    /**
     * Set the orientation.
     * 
     * @return
     */
    public MediaQuery landscape() {
        this.orientation = "landscape";
        return this;
    }

    /**
     * Set the orientation.
     * 
     * @return
     */
    public MediaQuery portrait() {
        this.orientation = "portrait";
        return this;
    }

    /**
     * Set the device type.
     * 
     * @return
     */
    public MediaQuery hover() {
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
        MediaQuery other = (MediaQuery) obj;
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
        StringBuilder builder = new StringBuilder();
        builder.append("@media ").append(type);
        if (orientation != null) {
            builder.append(" and (orientation:").append(orientation).append(")");
        }
        if (hover != null) {
            builder.append(" and (hover:").append(hover).append(")");
        }
        if (maxWidth != null) {
            builder.append(" and (max-width:").append(maxWidth).append(")");
        }
        if (minWidth != null) {
            builder.append(" and (min-width:").append(minWidth).append(")");
        }
        if (maxHeight != null) {
            builder.append(" and (max-height:").append(maxHeight).append(")");
        }
        if (minHeight != null) {
            builder.append(" and (min-height:").append(minHeight).append(")");
        }

        return builder.toString();
    }

    /**
     * Create {@link MediaQuery} for all.
     * 
     * @return
     */
    public static MediaQuery all() {
        return new MediaQuery("all");
    }

    /**
     * Create {@link MediaQuery} for print.
     * 
     * @return
     */
    public static MediaQuery print() {
        return new MediaQuery("print");
    }

    /**
     * Create {@link MediaQuery} for screen.
     * 
     * @return
     */
    public static MediaQuery screen() {
        return new MediaQuery("screen");
    }

    /**
     * Create {@link MediaQuery} for speech.
     * 
     * @return
     */
    public static MediaQuery speech() {
        return new MediaQuery("speech");
    }
}