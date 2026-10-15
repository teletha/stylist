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

class ObjectFitTest extends StyleTester {

    @Test
    void fill() {
        ValidatableStyle style = writeStyle(() -> {
            objectFit.fill();
        });
        assert style.property("object-fit", "fill");
    }

    @Test
    void contain() {
        ValidatableStyle style = writeStyle(() -> {
            objectFit.contain();
        });
        assert style.property("object-fit", "contain");
    }

    @Test
    void cover() {
        ValidatableStyle style = writeStyle(() -> {
            objectFit.cover();
        });
        assert style.property("object-fit", "cover");
    }

    @Test
    void none() {
        ValidatableStyle style = writeStyle(() -> {
            objectFit.none();
        });
        assert style.property("object-fit", "none");
    }

    @Test
    void scaleDown() {
        ValidatableStyle style = writeStyle(() -> {
            objectFit.scaleDown();
        });
        assert style.property("object-fit", "scale-down");
    }

    @Test
    void positionKeywords() {
        ValidatableStyle style = writeStyle(() -> {
            objectPosition.center();
        });
        assert style.property("object-position", "center");
    }

    @Test
    void positionCorner() {
        ValidatableStyle style = writeStyle(() -> {
            objectPosition.bottomRight();
        });
        assert style.property("object-position", "bottom right");
    }

    @Test
    void positionLength() {
        ValidatableStyle style = writeStyle(() -> {
            objectPosition.position(10, px, 20, px);
        });
        assert style.property("object-position", "10px 20px");
    }

    @Test
    void positionPercentage() {
        ValidatableStyle style = writeStyle(() -> {
            objectPosition.position(Numeric.num(50, percent));
        });
        assert style.property("object-position", "50%");
    }
}