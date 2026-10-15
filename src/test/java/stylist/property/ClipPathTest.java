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
import stylist.value.Numeric;

class ClipPathTest extends StyleTester {

    @Test
    void none() {
        ValidatableStyle style = writeStyle(() -> {
            clipPath.none();
        });
        assert style.property("clip-path", "none");
    }

    @Test
    void circle() {
        ValidatableStyle style = writeStyle(() -> {
            clipPath.circle(Numeric.num(50, percent));
        });
        assert style.property("clip-path", "circle(50%)");
    }

    @Test
    void circleAt() {
        ValidatableStyle style = writeStyle(() -> {
            clipPath.circle(Numeric.num(30, percent), Numeric.num(50, percent), Numeric.num(50, percent));
        });
        assert style.property("clip-path", "circle(30% at 50% 50%)");
    }

    @Test
    void ellipse() {
        ValidatableStyle style = writeStyle(() -> {
            clipPath.ellipse(Numeric.num(50, percent), Numeric.num(25, percent));
        });
        assert style.property("clip-path", "ellipse(50% 25%)");
    }

    @Test
    void inset() {
        ValidatableStyle style = writeStyle(() -> {
            clipPath.inset(10, px, 20, px, 10, px, 20, px);
        });
        assert style.property("clip-path", "inset(10px 20px 10px 20px)");
    }

    @Test
    void polygon() {
        ValidatableStyle style = writeStyle(() -> {
            clipPath.polygon(Numeric.num(0, percent), Numeric.num(0, percent),
                    Numeric.num(100, percent), Numeric.num(0, percent),
                    Numeric.num(100, percent), Numeric.num(100, percent));
        });
        assert style.property("clip-path", "polygon(0 0, 100% 0, 100% 100%)");
    }
}