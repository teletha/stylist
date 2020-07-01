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

class ScrollTest extends StyleTester {

    @Test
    void scrollSmooth() {
        ValidatableStyle parsed = writeStyle(() -> {
            scroll.smooth();
        });
        assert parsed.property("scroll-behavior", "smooth");
    }

    @Test
    void scrollAuto() {
        ValidatableStyle parsed = writeStyle(() -> {
            scroll.auto();
        });
        assert parsed.property("scroll-behavior", "auto");
    }

    @Test
    void scrollPadding() {
        ValidatableStyle parsed = writeStyle(() -> {
            scroll.padding.top(10, px);
        });
        assert parsed.property("scroll-padding-top", "10px");
    }

    @Test
    void scrollMargin() {
        ValidatableStyle parsed = writeStyle(() -> {
            scroll.padding.vertical(10, px);
        });
        assert parsed.property("scroll-padding-top", "10px");
        assert parsed.property("scroll-padding-bottom", "10px");
    }
}