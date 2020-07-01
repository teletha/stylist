/*
 * Copyright (C) 2020 stylist Development Team
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

class OverflowTest extends StyleTester {

    @Test
    void normal() {
        ValidatableStyle style = writeStyle(() -> {
            overflow.hidden();
        });
        assert style.property("overflow", "hidden");
    }

    @Test
    void x() {
        ValidatableStyle style = writeStyle(() -> {
            overflow.x.scroll();
        });

        assert style.property("overflow-x", "scroll");
    }

    @Test
    void y() {
        ValidatableStyle style = writeStyle(() -> {
            overflow.y.visible();
        });

        assert style.property("overflow-y", "visible");
    }

    @Test
    void xy() {
        ValidatableStyle style = writeStyle(() -> {
            overflow.x.auto().y.hidden();
        });

        assert style.property("overflow-x", "auto");
        assert style.property("overflow-y", "hidden");
    }

    @Test
    void scrollbar() {
        ValidatableStyle style = writeStyle(() -> {
            overflow.scrollbar.color(Color.Black).scrollbar.width(10, px);
        });

        assert style.property("scrollbar-width", "10px");
        assert style.property("scrollbar-color", "black");
    }
}