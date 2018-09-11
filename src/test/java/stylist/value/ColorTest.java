/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;

/**
 * @version 2018/09/02 16:04:47
 */
class ColorTest extends StyleTester {

    @Test
    void white() {
        ValidatableStyle style = writeStyle(() -> {
            font.color(255, 255, 255);
        });
        assert style.property("color", "white");
    }

    @Test
    void black() {
        ValidatableStyle style = writeStyle(() -> {
            font.color(0, 0, 0);
        });
        assert style.property("color", "black");
    }

    @Test
    void hsl() {
        ValidatableStyle style = writeStyle(() -> {
            font.color(255, 0, 0);
        });
        assert style.property("color", "hsl(0,100%,50%)");
    }

    @Test
    void hsla() {
        ValidatableStyle style = writeStyle(() -> {
            font.color(255, 255, 255, 0.9);
        });
        assert style.property("color", "hsla(0,0%,100%,0.9)");
    }

    @Test
    void zeroAlpha() {
        ValidatableStyle style = writeStyle(() -> {
            font.color(255, 255, 255, 0);
        });
        assert style.property("color", "transparent");
    }

    @Test
    void hex3() {
        Color color = Color.rgb("#d3a");
        assert color.equals(Color.rgb(221, 51, 170));
    }

    @Test
    void hex6() {
        Color color = Color.rgb("#459F2D");
        assert color.equals(Color.rgb(69, 159, 45));
        assert color.alpha == 1;
        assert color.hue == 107;
        assert color.saturation == 56;
        assert color.lightness == 40;
    }

    @Test
    void withoutSharp() {
        Color color = Color.rgb("459F2D");
        assert color.equals(Color.rgb(69, 159, 45));

        color = Color.rgb("d3a");
        assert color.equals(Color.rgb(221, 51, 170));
    }

    @Test
    void invalidHex() {
        assertThrows(IllegalArgumentException.class, () -> Color.rgb(""));
        assertThrows(IllegalArgumentException.class, () -> Color.rgb("#123456789"));
        assertThrows(IllegalArgumentException.class, () -> Color.rgb("#12"));
    }

    @Test
    void toRGB() {
        assert new Color(0, 0, 0, 1).toRGB().equals("rgb(0,0,0)");
        assert new Color(1, 2, 3, 1).toRGB().equals("rgb(8,8,7)");
        assert new Color(10, 20, 30, 0).toRGB().equals("rgb(92,66,61,0)");
        assert new Color(150, 55, 70, 0.5).toRGB().equals("rgb(136,221,179,.5)");
        assert new Color(222, 4, 92).toRGB().equals("rgb(234,234,235)");
        assert new Color(341, 76, 13).toRGB().equals("rgb(58,8,24)");
    }

    @Test
    void toInvalidRGB() {
        assertThrows(IllegalArgumentException.class, () -> new Color(-1, 0, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Color(361, 0, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Color(0, -1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Color(0, 101, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Color(0, 0, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Color(0, 0, 101, 1));
        assertThrows(IllegalArgumentException.class, () -> new Color(0, 0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> new Color(0, 0, 0, 1.1));
    }
}
