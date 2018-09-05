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
 * @version 2018/09/05 11:52:22
 */
class FontTest extends StyleTester {

    @Test
    void rgb() {
        ValidatableStyle parsed = writeStyle(() -> {
            font.color(255, 0, 0);
        });
        assert parsed.property("color", "hsl(0,100%,50%)");
    }

    @Test
    void family() {
        ValidatableStyle parsed = writeStyle(() -> {
            font.family("http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600", stylist.value.Font.Serif);
        });
        assert parsed.property("font-family", "\"Source Sans Pro\",serif");
    }
}
