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
import stylist.property.helper.Content;

class FlexGapTest extends StyleTester {

    @Test
    void gap() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().gap(10, px);
        });
        assert style.property("row-gap", "10px");
        assert style.property("column-gap", "10px");
    }

    @Test
    void gapBoth() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().gap(10, px, 20, px);
        });
        assert style.property("row-gap", "10px");
        assert style.property("column-gap", "20px");
    }

    @Test
    void rowGap() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().rowGap(5, px);
        });
        assert style.property("row-gap", "5px");
    }

    @Test
    void columnGap() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().columnGap(5, px);
        });
        assert style.property("column-gap", "5px");
    }

    @Test
    void placeContent() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().placeContent(Content.SpaceBetween);
        });
        assert style.property("place-content", "space-between");
    }
}