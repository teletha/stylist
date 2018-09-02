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

import org.junit.jupiter.api.Test;

import stylist.StyleTester;

/**
 * @version 2018/08/30 18:34:37
 */
public class ColorTest extends StyleTester {

    @Test
    public void white() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            font.color(255, 255, 255);
        });
        assert style.property("color", "white");
    }

    @Test
    public void black() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            font.color(0, 0, 0);
        });
        assert style.property("color", "black");
    }

    @Test
    public void hsl() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            font.color(255, 0, 0);
        });
        assert style.property("color", "hsl(0,100%,50%)");
    }

    @Test
    public void hsla() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            font.color(255, 255, 255, 0.9);
        });
        assert style.property("color", "hsla(0,0%,100%,0.9)");
    }

    @Test
    public void zeroAlpha() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            font.color(255, 255, 255, 0);
        });
        assert style.property("color", "transparent");
    }

    @Test
    public void hex3() throws Exception {
        Color color = Color.rgb("#d3a");
        assert color.equals(Color.rgb(221, 51, 170));
    }

    @Test
    public void hex6() throws Exception {
        Color color = Color.rgb("#459F2D");
        assert color.equals(Color.rgb(69, 159, 45));
        assert color.alpha == 1;
        assert color.hue == 107;
        assert color.saturation == 56;
        assert color.lightness == 40;
    }

    @Test
    public void withoutSharp() throws Exception {
        Color color = Color.rgb("459F2D");
        assert color.equals(Color.rgb(69, 159, 45));

        color = Color.rgb("d3a");
        assert color.equals(Color.rgb(221, 51, 170));
    }

    @Test
    public void invalidHex() throws Exception {
        Color color = Color.rgb("");
        assert color == Color.Transparent;

        color = Color.rgb("#123456789");
        assert color == Color.Transparent;

        color = Color.rgb("#12");
        assert color == Color.Transparent;
    }

    @Test
    void colorConversion() {
        Color color = Color.rgb("#730D48");
        assert color.toHSL().equals("hsl(325,80%,25%)");
        assert color.toRGB().equals("rgb(115,13,72)");

        color = Color.rgb("#2d539f");
        assert color.toHSL().equals("hsl(220,56%,40%)");
        assert color.toRGB().equals("rgb(45,83,159)");
    }
}
