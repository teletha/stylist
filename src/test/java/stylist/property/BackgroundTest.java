/*
 * Copyright (C) 2021 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import static stylist.value.Color.*;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;
import stylist.Vendor;
import stylist.property.Background.BackgroundImage;

/**
 * @version 2018/09/05 11:51:47
 */
class BackgroundTest extends StyleTester {

    @Test
    void color() {
        ValidatableStyle parsed = writeStyle(() -> {
            background.color(255, 0, 0);
        });
        assert parsed.property("background-color", "hsl(0,100%,50%)");
    }

    @Test
    void origin() {
        ValidatableStyle parsed = writeStyle(() -> {
            background.contentBox();
        });
        assert parsed.property("background-origin", "content-box");

        parsed = writeStyle(() -> {
            background.paddingBox();
        });
        assert parsed.property("background-origin", "padding-box");

        parsed = writeStyle(() -> {
            background.borderBox();
        });
        assert parsed.property("background-origin", "border-box");
    }

    @Test
    void imageSingle() {
        ValidatableStyle parsed = writeStyle(() -> {
            background.image("test").fixed().cover().repeatX();
        });
        assert parsed.property("background-image", "url(test)");
        assert parsed.property("background-attachment", "fixed");
        assert parsed.property("background-size", "cover");
        assert parsed.property("background-repeat", "repeat-x");
    }

    @Test
    void image() {
        ValidatableStyle parsed = writeStyle(() -> {
            background.image(BackgroundImage.url("test").top().left().fixed().repeat().cover());
        });
        assert parsed.property("background-image", "url(test)");
        assert parsed.property("background-attachment", "fixed");
        assert parsed.property("background-size", "cover");
    }

    @Test
    void images() {
        ValidatableStyle parsed = writeStyle(() -> {
            BackgroundImage one = BackgroundImage.url("one").bottom().right();
            BackgroundImage two = BackgroundImage.url("two").horizontal(1, em).vertical(2, percent).noRepeat().contain().local();

            background.image(one, two);
        });
        assert parsed.property("background-image", "url(one),url(two)");
        assert parsed.property("background-position", "100% 100%,1em 2%");
        assert parsed.property("background-attachment", "scroll,local");
        assert parsed.property("background-repeat", "repeat,no-repeat");
        assert parsed.property("background-size", "auto,contain");
    }

    @Test
    void imageGradient() {
        ValidatableStyle parsed = writeStyle(() -> {
            background.image(BackgroundImage.of($.linear().color(Black, White)));
        });
        assert parsed.property("background-image", "linear-gradient(black,white)");
        assert parsed.property("background-image", Vendor.Webkit, "background-image", "-webkit-linear-gradient(black,white)");
    }

    @Test
    void imageGradients() {
        ValidatableStyle parsed = writeStyle(() -> {
            background.image(BackgroundImage.of($.linear().color(Black, White)), BackgroundImage.of($.linear().color(White, Black)));
        });
        assert parsed.property("background-image", "linear-gradient(black,white),linear-gradient(white,black)");
        assert parsed
                .property("background-image", Vendor.Webkit, "background-image", "-webkit-linear-gradient(black,white),-webkit-linear-gradient(white,black)");
    }
}