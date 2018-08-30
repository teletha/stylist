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
 * @version 2018/08/30 18:37:33
 */
public class FlexAlignItemsTest extends StyleTester {

    @Test
    public void start() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.start();
        });

        assert style.property("align-items", "flex-start");
        assert style.property("-webkit-align-items", "flex-start");
    }

    @Test
    public void end() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.end();
        });

        assert style.property("align-items", "flex-end");
        assert style.property("-webkit-align-items", "flex-end");
    }

    @Test
    public void center() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.center();
        });

        assert style.property("align-items", "center");
        assert style.property("-webkit-align-items", "center");
    }

    @Test
    public void baseline() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.baseline();
        });

        assert style.property("align-items", "baseline");
        assert style.property("-webkit-align-items", "baseline");
    }

    @Test
    public void stretch() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignItems.stretch();
        });

        assert style.property("align-items", "stretch");
        assert style.property("-webkit-align-items", "stretch");
    }
}
