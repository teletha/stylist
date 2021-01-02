/*
 * Copyright (C) 2021 stylist Development Team
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
 * @version 2018/09/05 11:18:01
 */
class FlexAlignContentTest extends StyleTester {

    @Test
    void start() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.start();
        });

        assert style.property("align-content", "flex-start");
        assert style.property("align-content", Vendor.Webkit, "-webkit-align-content", "flex-start");
    }

    @Test
    void end() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.end();
        });

        assert style.property("align-content", "flex-end");
        assert style.property("align-content", Vendor.Webkit, "-webkit-align-content", "flex-end");
    }

    @Test
    void center() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.center();
        });

        assert style.property("align-content", "center");
        assert style.property("align-content", Vendor.Webkit, "-webkit-align-content", "center");
    }

    @Test
    void spaceAround() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.spaceAround();
        });

        assert style.property("align-content", "space-around");
        assert style.property("align-content", Vendor.Webkit, "-webkit-align-content", "space-around");
    }

    @Test
    void spaceBetween() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.spaceBetween();
        });

        assert style.property("align-content", "space-between");
        assert style.property("align-content", Vendor.Webkit, "-webkit-align-content", "space-between");
    }

    @Test
    void stretch() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.stretch();
        });
        assert style.property("align-content", "stretch");
        assert style.property("align-content", Vendor.Webkit, "-webkit-align-content", "stretch");
    }
}