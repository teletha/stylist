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
import stylist.value.Color;

class FilterTest extends StyleTester {

    @Test
    void blur() {
        ValidatableStyle style = writeStyle(() -> {
            filter.blur(5, px);
        });
        assert style.property("filter", "blur(5px)");
    }

    @Test
    void brightness() {
        ValidatableStyle style = writeStyle(() -> {
            filter.brightness(1.2);
        });
        assert style.property("filter", "brightness(1.2)");
    }

    @Test
    void contrast() {
        ValidatableStyle style = writeStyle(() -> {
            filter.contrast(0.5);
        });
        assert style.property("filter", "contrast(0.5)");
    }

    @Test
    void grayscale() {
        ValidatableStyle style = writeStyle(() -> {
            filter.grayscale(1);
        });
        assert style.property("filter", "grayscale(1)");
    }

    @Test
    void hueRotate() {
        ValidatableStyle style = writeStyle(() -> {
            filter.hueRotate(90, deg);
        });
        assert style.property("filter", "hue-rotate(90deg)");
    }

    @Test
    void invert() {
        ValidatableStyle style = writeStyle(() -> {
            filter.invert(1);
        });
        assert style.property("filter", "invert(1)");
    }

    @Test
    void opacity() {
        ValidatableStyle style = writeStyle(() -> {
            filter.opacity(0.3);
        });
        assert style.property("filter", "opacity(0.3)");
    }

    @Test
    void saturate() {
        ValidatableStyle style = writeStyle(() -> {
            filter.saturate(2);
        });
        assert style.property("filter", "saturate(2)");
    }

    @Test
    void sepia() {
        ValidatableStyle style = writeStyle(() -> {
            filter.sepia(0.5);
        });
        assert style.property("filter", "sepia(0.5)");
    }

    @Test
    void dropShadow() {
        ValidatableStyle style = writeStyle(() -> {
            filter.dropShadow($.shadow().offset(1, 1, px).color(Color.Black));
        });
        assert style.property("filter", "drop-shadow(1px 1px black)");
    }

    @Test
    void combined() {
        ValidatableStyle style = writeStyle(() -> {
            filter.blur(5, px).brightness(1.2).grayscale(1);
        });
        assert style.property("filter", "blur(5px) brightness(1.2) grayscale(1)");
    }

    @Test
    void none() {
        ValidatableStyle style = writeStyle(() -> {
            filter.none();
        });
        assert style.property("filter", "none");
    }

    @Test
    void backdrop() {
        ValidatableStyle style = writeStyle(() -> {
            backdropFilter.blur(10, px);
        });
        assert style.property("backdrop-filter", "blur(10px)");
    }
}