/*
 * Copyright (C) 2023 The STYLIST Development Team
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

/**
 * @version 2018/09/05 11:20:14
 */
class FlexJustifyContentTest extends StyleTester {

    @Test
    void start() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().justifyContent.start();
        });

        assert style.property("justify-content", "flex-start");
        assert style.property("justify-content", Vendor.Webkit, "-webkit-justify-content", "flex-start");
    }

    @Test
    void end() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().justifyContent.end();
        });

        assert style.property("justify-content", "flex-end");
        assert style.property("justify-content", Vendor.Webkit, "-webkit-justify-content", "flex-end");
    }

    @Test
    void center() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().justifyContent.center();
        });

        assert style.property("justify-content", "center");
        assert style.property("justify-content", Vendor.Webkit, "-webkit-justify-content", "center");
    }

    @Test
    void spaceAround() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().justifyContent.spaceAround();
        });

        assert style.property("justify-content", "space-around");
        assert style.property("justify-content", Vendor.Webkit, "-webkit-justify-content", "space-around");
    }

    @Test
    void spaceBetween() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().justifyContent.spaceBetween();
        });

        assert style.property("justify-content", "space-between");
        assert style.property("justify-content", Vendor.Webkit, "-webkit-justify-content", "space-between");
    }
}