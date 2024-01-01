/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import org.junit.jupiter.api.Test;

import stylist.value.Color;

/**
 * @version 2018/09/11 14:17:43
 */
class StylistTest extends StyleTester {

    ValidatableStyle empty = writeStyle(() -> {
    });

    @Test
    void selector() {
        Stylist formatter = Stylist.compact().showEmptyStyle(true).selector(" ", " ");

        assert formatter.format(empty.rules).equals(" " + empty.selector() + " {}");
    }

    @Test
    void startBrace() {
        Stylist formatter = Stylist.compact().showEmptyStyle(true).startBrace("\r\n");

        assert formatter.format(empty.rules).equals(empty.selector() + "{\r\n}");
    }

    @Test
    void endBrace() {
        Stylist formatter = Stylist.compact().showEmptyStyle(true).endBrace("\r\n", "");

        assert formatter.format(empty.rules).equals(empty.selector() + "{\r\n}");
    }

    ValidatableStyle one = writeStyle(() -> {
        display.block();
    });

    @Test
    void propertyName() {
        Stylist formatter = Stylist.compact().propertyName(" ", " ");

        assert formatter.format(one.rules).equals(one.selector() + "{ display :block;}");
    }

    @Test
    void propertyValue() {
        Stylist formatter = Stylist.compact().propertyValue(" ", " ");

        assert formatter.format(one.rules).equals(one.selector() + "{display: block ;}");
    }

    @Test
    void propertyLine() {
        Stylist formatter = Stylist.compact().propertyLine("\r\n");

        assert formatter.format(one.rules).equals(one.selector() + "{display:block;\r\n}");
    }

    ValidatableStyle two = writeStyle(() -> {
        display.block().width(10, px);
    });

    @Test
    void properties() {
        Stylist formatter = Stylist.compact().propertyName(" ", "");

        assert formatter.format(two.rules).equals(two.selector() + "{ display:block; width:10px;}");
    }

    ValidatableStyle nest = writeStyle(() -> {
        display.width(10, px);

        $.hover(() -> {
            display.width(20, px);
        });
    });

    @Test
    void nest() {
        Stylist formatter = Stylist.compact().endBrace("", " ");

        assert formatter.format(nest.rules).equals(nest.selector() + "{width:10px;} " + nest.selector() + ":hover{width:20px;} ");
    }

    ValidatableStyle color = writeStyle(() -> {
        font.color("#111111");
    });

    @Test
    void color() {
        Stylist formatter = Stylist.compact().color(Color::toRGB);

        assert formatter.format(color.rules).equals(color.selector() + "{color:rgb(18,18,18);}");
    }
}