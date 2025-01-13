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

import org.junit.jupiter.api.Test;

import stylist.StyleTester;

class FlexAlignItemsTest extends StyleTester {

    @Test
    void start() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.start();
        });

        assert style.property("align-items", "start");
    }

    @Test
    void end() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.end();
        });

        assert style.property("align-items", "end");
    }

    @Test
    void center() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.center();
        });

        assert style.property("align-items", "center");
    }

    @Test
    void baseline() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.baseline();
        });

        assert style.property("align-items", "baseline");
    }

    @Test
    void stretch() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.stretch();
        });

        assert style.property("align-items", "stretch");
    }
}