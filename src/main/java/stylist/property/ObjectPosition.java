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

public class ObjectPosition extends PropertyDefinition<ObjectPosition> {

    public ObjectPosition() {
        super("object-position");
    }

    public ObjectPosition center() {
        return value("center");
    }

    public ObjectPosition top() {
        return value("top");
    }

    public ObjectPosition bottom() {
        return value("bottom");
    }

    public ObjectPosition left() {
        return value("left");
    }

    public ObjectPosition right() {
        return value("right");
    }

    public ObjectPosition topLeft() {
        return value("top left");
    }

    public ObjectPosition topRight() {
        return value("top right");
    }

    public ObjectPosition bottomLeft() {
        return value("bottom left");
    }

    public ObjectPosition bottomRight() {
        return value("bottom right");
    }

    public ObjectPosition position(double x, Unit unitX, double y, Unit unitY) {
        return position(new Numeric(x, unitX), new Numeric(y, unitY));
    }

    public ObjectPosition position(Numeric x, Numeric y) {
        List<CSSValue> list = new ArrayList();
        list.add(x);
        list.add(y);
        return value("object-position", list, " ");
    }

    public ObjectPosition position(double x, Unit unit) {
        return position(new Numeric(x, unit));
    }

    public ObjectPosition position(Numeric x) {
        List<CSSValue> list = new ArrayList();
        list.add(x);
        return value("object-position", list, " ");
    }
}