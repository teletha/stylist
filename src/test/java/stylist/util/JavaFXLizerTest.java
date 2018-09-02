/*
 * Copyright (C) 2017 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.util;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;
import stylist.value.Color;

/**
 * @version 2018/09/01 22:21:23
 */
class JavaFXLizerTest extends StyleTester {

    JavaFXLizer fxlizer = new JavaFXLizer();

    @Test
    void width() {
        ValidatableStyle style = writeStyle(() -> {
            display.width(10, px);
            display.maxWidth(10, px);
            display.minWidth(10, px);
        }, fxlizer);

        assert style.property("-fx-pref-width", "10px");
        assert style.property("-fx-max-width", "10px");
        assert style.property("-fx-min-width", "10px");
    }

    @Test
    void height() {
        ValidatableStyle style = writeStyle(() -> {
            display.height(10, px);
            display.maxHeight(10, px);
            display.minHeight(10, px);
        }, fxlizer);

        assert style.property("-fx-pref-height", "10px");
        assert style.property("-fx-max-height", "10px");
        assert style.property("-fx-min-height", "10px");
    }

    @Test
    void padding() {
        ValidatableStyle style = writeStyle(() -> {
            padding.size(10, px);
        }, fxlizer);
        assert style.property("-fx-padding", "10px 10px 10px 10px");

        style = writeStyle(() -> {
            padding.horizontal(10, px);
        }, fxlizer);
        assert style.property("-fx-padding", "0 10px 0 10px");

        style = writeStyle(() -> {
            padding.top(10, px).left(5, px);
        }, fxlizer);
        assert style.property("-fx-padding", "10px 0 0 5px");
    }

    @Test
    void curosr() {
        ValidatableStyle style = writeStyle(() -> {
            cursor.pointer();
        }, fxlizer);
        assert style.property("-fx-cursor", "hand");

        style = writeStyle(() -> {
            cursor.waiting();
        }, fxlizer);
        assert style.property("-fx-cursor", "wait");

        style = writeStyle(() -> {
            cursor.text();
        }, fxlizer);
        assert style.property("-fx-cursor", "text");
    }

    @Test
    void borderWidth() {
        ValidatableStyle style = writeStyle(() -> {
            border.bottom.width(2, px).solid().color(Color.Black);
        }, fxlizer);
        assert style.property("-fx-border-width", "0 0 2px 0");
    }

    @Test
    void borderColor() {
        ValidatableStyle style = writeStyle(() -> {
            border.bottom.width(2, px).solid().color(Color.Black);
        }, fxlizer);
        assert style.property("-fx-border-color", "transparent transparent black transparent");
    }
}
