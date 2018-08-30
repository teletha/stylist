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

/**
 * @version 2018/08/30 18:40:11
 */
public class FlexWrapTest extends StyleTester {

    @Test
    public void noWrap() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().wrap.disable();
        });

        assert style.property("flex-wrap", "nowrap");
        assert style.property("-webkit-flex-wrap", "nowrap");
    }

    @Test
    public void wrap() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().wrap.enable();
        });
        assert style.property("flex-wrap", "wrap");
        assert style.property("-webkit-flex-wrap", "wrap");
    }

    @Test
    public void wrapReverse() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().wrap.reverse();
        });

        assert style.property("flex-wrap", "wrap-reverse");
        assert style.property("-webkit-flex-wrap", "wrap-reverse");
    }
}
