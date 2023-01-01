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
 * @version 2018/09/05 11:22:06
 */
class FlexWrapTest extends StyleTester {

    @Test
    void noWrap() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().wrap.disable();
        });

        assert style.property("flex-wrap", "nowrap");
        assert style.property("flex-wrap", Vendor.Webkit, "-webkit-flex-wrap", "nowrap");
    }

    @Test
    void wrap() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().wrap.enable();
        });
        assert style.property("flex-wrap", "wrap");
        assert style.property("flex-wrap", Vendor.Webkit, "-webkit-flex-wrap", "wrap");
    }

    @Test
    void wrapReverse() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().wrap.reverse();
        });

        assert style.property("flex-wrap", "wrap-reverse");
        assert style.property("flex-wrap", Vendor.Webkit, "-webkit-flex-wrap", "wrap-reverse");
    }
}