/*
 * Copyright (C) 2021 stylist Development Team
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
import stylist.property.Background.BackgroundImage;

/**
 * @version 2018/09/05 11:42:58
 */
class LinearGradientTest extends StyleTester {

    private static final Color black = Color.Black;

    private static final Color white = Color.White;

    private static final Numeric one = new Numeric(10, px);

    private static final Numeric two = new Numeric(20, em);

    @Test
    void base() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new LinearGradient().color(black, white)));
        });
        assert style.property("background-image", "linear-gradient(black,white)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-linear-gradient(black,white)");
    }

    @Test
    void angle() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new LinearGradient().angle(10, deg).color(black, white)));
        });
        assert style.property("background-image", "linear-gradient(10deg,black,white)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-linear-gradient(280deg,black,white)");
    }

    @Test
    void colors() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new LinearGradient().color(black, white, black)));
        });
        assert style.property("background-image", "linear-gradient(black,white,black)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-linear-gradient(black,white,black)");
    }

    @Test
    void percentage() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new LinearGradient().color(black, 10).color(white, 90)));
        });
        assert style.property("background-image", "linear-gradient(black 10%,white 90%)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-linear-gradient(black 10%,white 90%)");
    }

    @Test
    void length() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new LinearGradient().color(black, one).color(white, two)));
        });
        assert style.property("background-image", "linear-gradient(black 10px,white 20em)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-linear-gradient(black 10px,white 20em)");
    }

    @Test
    void repeat() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new LinearGradient().repeat().color(black, white)));
        });
        assert style.property("background-image", "repeating-linear-gradient(black,white)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-repeating-linear-gradient(black,white)");
    }
}