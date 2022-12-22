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
import stylist.value.Vendor;

/**
 * @version 2018/09/05 11:21:17
 */
class FlexDirectionTest extends StyleTester {

    @Test
    void row() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().direction.row();
        });

        assert style.property("flex-direction", "row");
        assert style.property("flex-direction", Vendor.Webkit, "-webkit-flex-direction", "row");
    }

    @Test
    void rowReverse() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().direction.rowReverse();
        });

        assert style.property("flex-direction", "row-reverse");
        assert style.property("flex-direction", Vendor.Webkit, "-webkit-flex-direction", "row-reverse");
    }

    @Test
    void colmun() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().direction.column();
        });

        assert style.property("flex-direction", "column");
        assert style.property("flex-direction", Vendor.Webkit, "-webkit-flex-direction", "column");
    }

    @Test
    void colmunReverse() {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().direction.columnReverse();
        });

        assert style.property("flex-direction", "column-reverse");
        assert style.property("flex-direction", Vendor.Webkit, "-webkit-flex-direction", "column-reverse");
    }
}