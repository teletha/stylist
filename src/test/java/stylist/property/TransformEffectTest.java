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

class TransformEffectTest extends StyleTester {

    @Test
    void perspective() {
        ValidatableStyle style = writeStyle(() -> {
            transform.perspective(500, px);
        });
        assert style.property("transform", "perspective(500px)");
    }

    @Test
    void transformStyle() {
        ValidatableStyle style = writeStyle(() -> {
            transform.style.preserve3d();
        });
        assert style.property("transform-style", "preserve-3d");
    }

    @Test
    void transformStyleFlat() {
        ValidatableStyle style = writeStyle(() -> {
            transform.style.flat();
        });
        assert style.property("transform-style", "flat");
    }

    @Test
    void backfaceVisibility() {
        ValidatableStyle style = writeStyle(() -> {
            transform.backfaceVisibility.hidden();
        });
        assert style.property("backface-visibility", "hidden");
    }

    @Test
    void perspectiveOrigin() {
        ValidatableStyle style = writeStyle(() -> {
            transform.perspectiveOrigin.center();
        });
        assert style.property("perspective-origin", "center");
    }

    @Test
    void perspectiveOriginPosition() {
        ValidatableStyle style = writeStyle(() -> {
            transform.perspectiveOrigin.position(50, percent, 50, percent);
        });
        assert style.property("perspective-origin", "50% 50%");
    }
}
