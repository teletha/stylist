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
 * @version 2018/08/30 18:38:01
 */
public class FlexDirectionTest extends StyleTester {

    @Test
    public void row() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().direction.row();
        });

        assert style.property("flex-direction", "row");
        assert style.property("-webkit-flex-direction", "row");
    }

    @Test
    public void rowReverse() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().direction.rowReverse();
        });

        assert style.property("flex-direction", "row-reverse");
        assert style.property("-webkit-flex-direction", "row-reverse");
    }

    @Test
    public void colmun() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().direction.column();
        });

        assert style.property("flex-direction", "column");
        assert style.property("-webkit-flex-direction", "column");
    }

    @Test
    public void colmunReverse() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.flex().direction.columnReverse();
        });

        assert style.property("flex-direction", "column-reverse");
        assert style.property("-webkit-flex-direction", "column-reverse");
    }
}
