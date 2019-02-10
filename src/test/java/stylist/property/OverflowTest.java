/*
 * Copyright (C) 2019 stylist Development Team
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
 * @version 2018/08/30 18:39:19
 */
public class OverflowTest extends StyleTester {

    @Test
    public void normal() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            overflow.hidden();
        });
        assert style.property("overflow", "hidden");
    }

    @Test
    public void x() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            overflow.x.scroll();
        });

        assert style.property("overflow-x", "scroll");
    }

    @Test
    public void y() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            overflow.y.visible();
        });

        assert style.property("overflow-y", "visible");
    }

    @Test
    public void xy() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            overflow.x.auto().y.hidden();
        });

        assert style.property("overflow-x", "auto");
        assert style.property("overflow-y", "hidden");
    }
}
