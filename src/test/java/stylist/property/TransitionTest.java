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

public class TransitionTest extends StyleTester {

    @Test
    public void transition() {
        ValidatableStyle parsed = writeStyle(() -> {
            display.width(10, px);

            $.transit().duration(1, s).when().hover(() -> {
                display.width(20, px);
            });
        });
        assert parsed.property("transition-property", "width");
        assert parsed.property("transition-duration", "1s");
        assert parsed.property("transition-delay", "0");
        assert parsed.property("transition-timing-function", "ease");
    }
}
