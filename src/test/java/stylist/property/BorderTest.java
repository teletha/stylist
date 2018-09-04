/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;
import stylist.value.Color;

/**
 * @version 2018/08/30 18:36:41
 */
public class BorderTest extends StyleTester {

    @Test
    public void top() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.top.width(1, px).solid().color(Color.Black);
        });
        assert parsed.property("border-top-width", "1px");
        assert parsed.property("border-top-style", "solid");
    }

    @Test
    public void bottom() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.bottom.width(1, px).doubles();
        });
        assert parsed.property("border-bottom-width", "1px");
        assert parsed.property("border-bottom-style", "double");
    }

    @Test
    public void left() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.left.width(1, px).dashed();
        });
        assert parsed.property("border-left-width", "1px");
        assert parsed.property("border-left-style", "dashed");
    }

    @Test
    public void right() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.right.width(1, px).groove();
        });
        assert parsed.property("border-right-width", "1px");
        assert parsed.property("border-right-style", "groove");
    }

    @Test
    public void all() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.width(1, px).solid().color(Color.White);
        });
        assert parsed.property("border-color", "white");
        assert parsed.property("border-style", "solid");
        assert parsed.property("border-width", "1px");

    }

    @Test
    public void allWidth() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.width(2, px);
        });
        assert parsed.property("border-width", "2px");
    }

    @Test
    public void allStyle() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.solid();
        });
        assert parsed.property("border-style", "solid");
    }

    @Test
    public void allColor() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.color(Color.White);
        });
        assert parsed.property("border-color", "white");
    }

    @Test
    public void vertical() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.vertical.width(1, em).inset();
        });
        assert parsed.property("border-top-width", "1em");
        assert parsed.property("border-top-style", "inset");
        assert parsed.property("border-bottom-width", "1em");
        assert parsed.property("border-bottom-style", "inset");
    }

    @Test
    public void horizontal() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.horizontal.width(1, ex).outset();
        });
        assert parsed.property("border-left-width", "1ex");
        assert parsed.property("border-left-style", "outset");
        assert parsed.property("border-right-width", "1ex");
        assert parsed.property("border-right-style", "outset");
    }

    @Test
    public void radius() {
        ValidatableStyle parsed = writeStyle(() -> {
            border.top.radius(1, px);
        });
        assert parsed.property("border-top-right-radius", "1px");
    }
}
