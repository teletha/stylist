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
import stylist.Vendor;

/**
 * @version 2018/09/05 11:40:00
 */
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
}
