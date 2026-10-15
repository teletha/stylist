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
import stylist.property.helper.ColorHelper;
import stylist.value.CSSValue;

public class Interaction extends PropertyDefinition<Interaction> {

    public final Touch touch = new Touch(this);
    public final Caret caret = new Caret(this);
    public final Accent accent = new Accent(this);
    public final Resize resize = new Resize(this);
    public final WillChange willChange = new WillChange(this);

    public Interaction() {
        super();
    }

    public static class Touch extends PropertyDefinition<Interaction> {
        private Touch(Interaction parent) {
            super("touch-action", parent);
        }

        public Interaction auto() {
            return value("auto");
        }

        public Interaction none() {
            return value("none");
        }

        public Interaction panX() {
            return value("pan-x");
        }

        public Interaction panY() {
            return value("pan-y");
        }

        public Interaction panLeft() {
            return value("pan-left");
        }

        public Interaction panRight() {
            return value("pan-right");
        }

        public Interaction panUp() {
            return value("pan-up");
        }

        public Interaction panDown() {
            return value("pan-down");
        }

        public Interaction panXY() {
            return value("pan-x pan-y");
        }

        public Interaction manipulation() {
            return value("manipulation");
        }

        public Interaction pinchZoom() {
            return value("pinch-zoom");
        }
    }

    public static class Caret extends PropertyDefinition<Interaction> implements ColorHelper<Interaction> {
        private Caret(Interaction parent) {
            super("caret-color", parent);
        }

        @Override
        public Interaction color(CSSValue color) {
            return value(color);
        }
    }

    public static class Accent extends PropertyDefinition<Interaction> implements ColorHelper<Interaction> {
        private Accent(Interaction parent) {
            super("accent-color", parent);
        }

        @Override
        public Interaction color(CSSValue color) {
            return value(color);
        }
    }

    public static class Resize extends PropertyDefinition<Interaction> {
        private Resize(Interaction parent) {
            super("resize", parent);
        }

        public Interaction none() {
            return value("none");
        }

        public Interaction both() {
            return value("both");
        }

        public Interaction horizontal() {
            return value("horizontal");
        }

        public Interaction vertical() {
            return value("vertical");
        }

        public Interaction block() {
            return value("block");
        }

        public Interaction inline() {
            return value("inline");
        }
    }

    public static class WillChange extends PropertyDefinition<Interaction> {
        private final List<CSSValue> properties = new ArrayList();

        private WillChange(Interaction parent) {
            super("will-change", parent);
        }

        public WillChange auto() {
            properties.clear();
            value("auto");

            return this;
        }

        public WillChange add(String propertyName) {
            properties.add(CSSValue.of(propertyName));
            value("will-change", properties, ", ");

            return this;
        }
    }

}