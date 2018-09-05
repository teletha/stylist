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
 * @version 2018/09/05 11:19:08
 */
class FlexAlignItemsTest extends StyleTester {

    @Test
    void start() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.start();
        });

        assert style.property("align-items", "flex-start");
        assert style.property("align-items", Vendor.Webkit, "-webkit-align-items", "flex-start");
    }

    @Test
    void end() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.end();
        });

        assert style.property("align-items", "flex-end");
        assert style.property("align-items", Vendor.Webkit, "-webkit-align-items", "flex-end");
    }

    @Test
    void center() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.center();
        });

        assert style.property("align-items", "center");
        assert style.property("align-items", Vendor.Webkit, "-webkit-align-items", "center");
    }

    @Test
    void baseline() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.baseline();
        });

        assert style.property("align-items", "baseline");
        assert style.property("align-items", Vendor.Webkit, "-webkit-align-items", "baseline");
    }

    @Test
    void stretch() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.stretch();
        });

        assert style.property("align-items", "stretch");
        assert style.property("align-items", Vendor.Webkit, "-webkit-align-items", "stretch");
    }
}
