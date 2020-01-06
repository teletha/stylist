/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;

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
    void inherit() {
        ValidatableStyle style = writeStyle(() -> {
            font.color(Color.Inherit);
        });
        assert style.property("color", "inherit");
    }

    @Test
    void initial() {
        ValidatableStyle style = writeStyle(() -> {
            font.color(Color.Initial);
        });
        assert style.property("color", "initial");
    }

    @Test
    void unset() {
        ValidatableStyle style = writeStyle(() -> {
            font.color(Color.Unset);
        });
        assert style.property("color", "unset");
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
    void toRGB() {
        assert Color.hsl(0, 0, 0, 1).toRGB().equals("rgb(0,0,0)");
        assert Color.hsl(1, 2, 3, 1).toRGB().equals("rgb(8,8,7)");
        assert Color.hsl(10, 20, 30, 0).toRGB().equals("rgba(92,66,61,0)");
        assert Color.hsl(150, 55, 70, 0.5).toRGB().equals("rgba(136,221,179,.5)");
        assert Color.hsl(222, 4, 92).toRGB().equals("rgb(234,234,235)");
        assert Color.hsl(341, 76, 13).toRGB().equals("rgb(58,8,24)");
    }

    @Test
    void toInvalidRGB() {
        assertThrows(IllegalArgumentException.class, () -> Color.hsl(-1, 0, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Color.hsl(361, 0, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Color.hsl(0, -1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Color.hsl(0, 101, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> Color.hsl(0, 0, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> Color.hsl(0, 0, 101, 1));
        assertThrows(IllegalArgumentException.class, () -> Color.hsl(0, 0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> Color.hsl(0, 0, 0, 1.1));
    }

    @Test
    void fromRGB() {
        assert Color.rgb(0, 0, 0).toRGB().equals("rgb(0,0,0)");
        assert Color.rgb(8, 8, 7).toRGB().equals("rgb(8,8,7)");
        assert Color.rgb(92, 66, 61, 0).toRGB().equals("rgba(92,66,61,0)");
        assert Color.rgb(136, 221, 179, 0.5).toRGB().equals("rgba(136,221,179,.5)");
        assert Color.rgb(234, 234, 235).toRGB().equals("rgb(234,234,235)");
        assert Color.rgb(58, 8, 24).toRGB().equals("rgb(58,8,24)");
    }

    @Test
    void parseRGB() {
        assert Color.of("rgb(0,0,0)").toRGB().equals("rgb(0,0,0)");
        assert Color.of("  rgb(  0 ,  0  ,  0  )  ").toRGB().equals("rgb(0,0,0)");
        assert Color.of("rgb(0,0,0,100%)").toRGB().equals("rgb(0,0,0)");
        assert Color.of("rgb(0,0,0,1)").toRGB().equals("rgb(0,0,0)");
        assert Color.of("rgb(0,0,0,0.1)").toRGB().equals("rgba(0,0,0,.1)");
        assert Color.of("rgb(0,0,0,.3)").toRGB().equals("rgba(0,0,0,.3)");
        assert Color.of("rgb(44,50,88)").toRGB().equals("rgb(44,50,88)");
        assert Color.of("rgba(32,118,197,0.1)").toRGB().equals("rgba(32,118,197,.1)");
    }

    @Test
    void parseInvalidRGB() {
        assertThrows(IllegalArgumentException.class, () -> Color.of("rgb()"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("rgb(-1, 0, 0)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("rgb(1, 300, 0)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("rgb( 0, 0, 0, 10)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("rgb( 0, 0, 0, -1)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("rgba( 0, 0, 0, 10)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("rgba( 0, 0, 0, -1)"));
    }

    @Test
    void parseHex() {
        assert Color.of("#d3a").equals(Color.hsl(318, 71, 53));
        assert Color.of("#459F2D").equals(Color.hsl(107, 56, 40));
        assert Color.of(" #372460 ").equals(Color.hsl(259, 45, 26));
    }

    @Test
    void parseInvalidHex() {
        assertThrows(IllegalArgumentException.class, () -> Color.of(""));
        assertThrows(IllegalArgumentException.class, () -> Color.of("#123456789"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("#12"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("# 123456"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("#1234 56"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("#1234X6"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("#1234_6"));
    }

    @Test
    void parseHSL() {
        assert Color.of("hsl(0,0,0)").equals(Color.hsl(0, 0, 0));
        assert Color.of("  hsl(  0 ,  0  ,  0  )  ").equals(Color.hsl(0, 0, 0));
        assert Color.of("hsl(0,0,0,100%)").equals(Color.hsl(0, 0, 0));
        assert Color.of("hsl(0,0,0,1)").equals(Color.hsl(0, 0, 0));
        assert Color.of("hsl(0,0,0,0.1)").equals(Color.hsl(0, 0, 0, 0.1));
        assert Color.of("hsl(0,0,0,.3)").equals(Color.hsl(0, 0, 0, 0.3));
        for (int i = 0; i < 100; i++) {
            assert Color.of("hsl(" + i + "," + i + "%," + i + "%)").equals(Color.hsl(i, i, i));
        }
    }

    @Test
    void parseInvalidHSL() {
        assertThrows(IllegalArgumentException.class, () -> Color.of("hsl()"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("hsl(-1, 0, 0)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("hsl(1, 101%, 0)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("hsl( 0, 0, 0, 10)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("hsl( 0, 0, 0, -1)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("hsla( 0, 0, 0, 10)"));
        assertThrows(IllegalArgumentException.class, () -> Color.of("hsla( 0, 0, 0, -1)"));
    }
}
