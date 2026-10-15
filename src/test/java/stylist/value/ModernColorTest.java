/*
 * Copyright (C) 2024 The STYLIST Development Team
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

class ModernColorTest extends StyleTester {

    @Test
    void oklch() {
        assert ModernColor.oklch(0.7, 0.15, 200).toString().equals("oklch(0.7 0.15 200)");
    }

    @Test
    void oklchAlpha() {
        assert ModernColor.oklch(0.7, 0.15, 200, 0.5).toString().equals("oklch(0.7 0.15 200 / 0.5)");
    }

    @Test
    void oklab() {
        assert ModernColor.oklab(0.5, 0.1, -0.1).toString().equals("oklab(0.5 0.1 -0.1)");
    }

    @Test
    void lch() {
        assert ModernColor.lch(50, 40, 200).toString().equals("lch(50% 40 200)");
    }

    @Test
    void lab() {
        assert ModernColor.lab(50, 40, 30).toString().equals("lab(50% 40 30)");
    }

    @Test
    void hwb() {
        assert ModernColor.hwb(200, 20, 30).toString().equals("hwb(200 20% 30%)");
    }

    @Test
    void colorFunction() {
        assert ModernColor.color("display-p3", 1, 0.5, 0).toString().equals("color(display-p3 1 0.5 0)");
    }

    @Test
    void colorMix() {
        assert ModernColor.colorMix(CSSValue.of("red"), CSSValue.of("blue")).toString().equals("color-mix(in srgb, red, blue)");
    }

    @Test
    void colorMixWeighted() {
        assert ModernColor.colorMix("srgb", CSSValue.of("red"), 30, CSSValue.of("blue"), 70).toString().equals("color-mix(in srgb, red 30%, blue 70%)");
    }

    @Test
    void lightDark() {
        assert ModernColor.lightDark(CSSValue.of("white"), CSSValue.of("black")).toString().equals("light-dark(white, black)");
    }

    @Test
    void asColor() {
        ValidatableStyle style = writeStyle(() -> {
            font.color($.oklch(0.7, 0.15, 200));
        });
        assert style.property("color", "oklch(0.7 0.15 200)");
    }

    @Test
    void asBackground() {
        ValidatableStyle style = writeStyle(() -> {
            background.color($.hwb(200, 20, 30));
        });
        assert style.property("background-color", "hwb(200 20% 30%)");
    }
}
