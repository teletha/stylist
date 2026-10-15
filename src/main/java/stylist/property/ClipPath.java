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

import java.util.ArrayList;
import java.util.List;

import stylist.PropertyDefinition;
import stylist.value.CSSValue;
import stylist.value.Numeric;
import stylist.value.Unit;

public class ClipPath extends PropertyDefinition<ClipPath> {

    public ClipPath() {
        super("clip-path");
    }

    public ClipPath none() {
        return value("none");
    }

    public ClipPath circle(Numeric radius) {
        return value("circle(" + radius + ")");
    }

    public ClipPath circle(Numeric radius, Numeric x, Numeric y) {
        return value("circle(" + radius + " at " + x + " " + y + ")");
    }

    public ClipPath ellipse(Numeric rx, Numeric ry) {
        return value("ellipse(" + rx + " " + ry + ")");
    }

    public ClipPath ellipse(Numeric rx, Numeric ry, Numeric x, Numeric y) {
        return value("ellipse(" + rx + " " + ry + " at " + x + " " + y + ")");
    }

    public ClipPath inset(Numeric top, Numeric right, Numeric bottom, Numeric left) {
        return value("inset(" + top + " " + right + " " + bottom + " " + left + ")");
    }

    public ClipPath inset(double top, Unit topU, double right, Unit rightU, double bottom, Unit bottomU, double left, Unit leftU) {
        return inset(new Numeric(top, topU), new Numeric(right, rightU), new Numeric(bottom, bottomU), new Numeric(left, leftU));
    }

    public ClipPath polygon(Numeric... points) {
        StringBuilder builder = new StringBuilder("polygon(");
        for (int i = 0; i < points.length; i += 2) {
            if (i > 0) builder.append(", ");
            builder.append(points[i]);
            if (i + 1 < points.length) {
                builder.append(" ").append(points[i + 1]);
            }
        }
        builder.append(")");
        return value(builder.toString());
    }
}