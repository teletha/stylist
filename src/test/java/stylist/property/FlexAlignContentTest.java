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
 * @version 2018/08/30 18:37:26
 */
public class FlexAlignContentTest extends StyleTester {

    @Test
    public void start() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.start();
        });

        assert style.property("align-content", "flex-start");
        assert style.property("-webkit-align-content", "flex-start");
    }

    @Test
    public void end() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.end();
        });

        assert style.property("align-content", "flex-end");
        assert style.property("-webkit-align-content", "flex-end");
    }

    @Test
    public void center() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.center();
        });

        assert style.property("align-content", "center");
        assert style.property("-webkit-align-content", "center");
    }

    @Test
    public void spaceAround() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.spaceAround();
        });

        assert style.property("align-content", "space-around");
        assert style.property("-webkit-align-content", "space-around");
    }

    @Test
    public void spaceBetween() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.spaceBetween();
        });

        assert style.property("align-content", "space-between");
        assert style.property("-webkit-align-content", "space-between");
    }

    @Test
    public void stretch() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().alignContent.stretch();
        });
        assert style.property("align-content", "stretch");
        assert style.property("-webkit-align-content", "stretch");
    }
}
