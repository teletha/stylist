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
import stylist.value.Vendor;

class DisplayTest extends StyleTester {

    @Test
    void block() {
        ValidatableStyle style = writeStyle(() -> {
            display.block();
        });
        assert style.property("display", "block");
    }

    @Test
    void inline() {
        ValidatableStyle style = writeStyle(() -> {
            display.inline();
        });
        assert style.property("display", "inline");
    }

    @Test
    void inlineBlock() {
        ValidatableStyle style = writeStyle(() -> {
            display.inlineBlock();
        });
        assert style.property("display", "inline-block");
    }

    @Test
    void flex() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex();
        });
        assert style.property("display", "flex");
        assert style.property("display", Vendor.Webkit, "display", "-webkit-flex");
    }

    @Test
    void inlineFlex() {
        ValidatableStyle style = writeStyle(() -> {
            display.inlineFlex();
        });
        assert style.property("display", "inline-flex");
        assert style.property("display", Vendor.Webkit, "display", "-webkit-inline-flex");
    }

    @Test
    void grid() {
        ValidatableStyle style = writeStyle(() -> {
            display.grid();
        });
        assert style.property("display", "grid");
    }

    @Test
    void inlineGrid() {
        ValidatableStyle style = writeStyle(() -> {
            display.inlineGrid();
        });
        assert style.property("display", "inline-grid");
    }

    @Test
    void heightFill() {
        ValidatableStyle style = writeStyle(() -> {
            display.height.fill();
        });
        assert style.property("height", "fill");
    }

    @Test
    void widthFill() {
        ValidatableStyle style = writeStyle(() -> {
            display.width.fill();
        });
        assert style.property("width", "fill");
    }

    @Test
    void floating() {
        ValidatableStyle style = writeStyle(() -> {
            display.floatLeft();
        });
        assert style.property("float", "left");
    }

    @Test
    void width() {
        ValidatableStyle style = writeStyle(() -> {
            display.width(50, px);
            display.maxWidth(100, px);
            display.minWidth(10, px);
        });
        assert style.property("width", "50px");
        assert style.property("max-width", "100px");
        assert style.property("min-width", "10px");
    }

    @Test
    void height() {
        ValidatableStyle style = writeStyle(() -> {
            display.height(50, px);
            display.maxHeight(100, px);
            display.minHeight(10, px);
        });
        assert style.property("height", "50px");
        assert style.property("max-height", "100px");
        assert style.property("min-height", "10px");
    }

    @Test
    void inlineSize() {
        ValidatableStyle style = writeStyle(() -> {
            display.inline(50, px);
            display.maxInline(100, px);
            display.minInline(10, px);
        });
        assert style.property("inline-size", "50px");
        assert style.property("max-inline-size", "100px");
        assert style.property("min-inline-size", "10px");
    }

    @Test
    void inlineShorthand() {
        ValidatableStyle style = writeStyle(() -> {
            display.inline(10, 50, 100, px);
        });
        assert style.property("inline-size", "50px");
        assert style.property("max-inline-size", "100px");
        assert style.property("min-inline-size", "10px");
    }

    @Test
    void blockSize() {
        ValidatableStyle style = writeStyle(() -> {
            display.block(50, px);
            display.maxBlock(100, px);
            display.minBlock(10, px);
        });
        assert style.property("block-size", "50px");
        assert style.property("max-block-size", "100px");
        assert style.property("min-block-size", "10px");
    }

    @Test
    void blockShorthand() {
        ValidatableStyle style = writeStyle(() -> {
            display.block(10, 50, 100, px);
        });
        assert style.property("block-size", "50px");
        assert style.property("max-block-size", "100px");
        assert style.property("min-block-size", "10px");
    }
}