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
 * @version 2018/08/30 18:38:07
 */
public class FlexboxTest extends StyleTester {

    @Test
    public void flex() {
        ValidatableStyle parsed = writeStyle(() -> {
            display.flex().direction.row().alignContent.center().wrap.enable().justifyContent.center().alignItems.end();
        });
        assert parsed.property("display", "flex", "-webkit-flex");
        assert parsed.property("flex-direction", "row");
        assert parsed.property("flex-wrap", "wrap");
        assert parsed.property("align-content", "center");
        assert parsed.property("justify-content", "center");
        assert parsed.property("align-items", "flex-end");

        assert parsed.property("-webkit-flex-direction", "row");
        assert parsed.property("-webkit-flex-wrap", "wrap");
        assert parsed.property("-webkit-align-content", "center");
        assert parsed.property("-webkit-justify-content", "center");
        assert parsed.property("-webkit-align-items", "flex-end");
    }

    @Test
    public void item() {
        ValidatableStyle parsed = writeStyle(() -> {
            flexItem.grow(2).shrink(2);
        });
        assert parsed.property("flex-grow", "2");
        assert parsed.property("flex-shrink", "2");
        assert parsed.property("-webkit-flex-grow", "2");
        assert parsed.property("-webkit-flex-shrink", "2");
    }
}
