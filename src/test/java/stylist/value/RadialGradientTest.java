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
import stylist.Vendor;
import stylist.property.Background.BackgroundImage;

/**
 * @version 2018/09/05 11:44:47
 */
class RadialGradientTest extends StyleTester {

    private static final Color black = Color.Black;

    private static final Color white = Color.White;

    private static final Numeric one = new Numeric(10, px);

    private static final Numeric two = new Numeric(20, em);

    private static final Position pos = new Position(one, two);

    @Test
    void base() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new RadialGradient().color(black, white)));
        });
        assert style.property("background-image", "radial-gradient(black,white)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-radial-gradient(black,white)");
    }

    @Test
    void colors() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new RadialGradient().color(black, white, black)));
        });
        assert style.property("background-image", "radial-gradient(black,white,black)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-radial-gradient(black,white,black)");
    }

    @Test
    void percentage() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new RadialGradient().color(black, 10).color(white, 90)));
        });
        assert style.property("background-image", "radial-gradient(black 10%,white 90%)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-radial-gradient(black 10%,white 90%)");
    }

    @Test
    void length() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new RadialGradient().color(black, one).color(white, two)));
        });
        assert style.property("background-image", "radial-gradient(black 10px,white 20em)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-radial-gradient(black 10px,white 20em)");
    }

    @Test
    void repeat() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new RadialGradient().repeat().color(black, white)));
        });
        assert style.property("background-image", "repeating-radial-gradient(black,white)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-repeating-radial-gradient(black,white)");
    }

    @Test
    void position() {
        ValidatableStyle style = writeStyle(() -> {
            background.image(BackgroundImage.of(new RadialGradient().position(pos).color(black, white)));
        });
        assert style.property("background-image", "radial-gradient(at 10px 20em,black,white)");
        assert style.property("background-image", Vendor.Webkit, "background-image", "-webkit-radial-gradient(10px 20em,black,white)");
    }
}