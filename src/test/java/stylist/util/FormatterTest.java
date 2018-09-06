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
 * @version 2018/09/06 13:20:32
 */
class FormatterTest extends StyleTester {

    ValidatableStyle empty = writeStyle(() -> {
    });

    @Test
    void selector() {
        Formatter formatter = Formatter.compact().selector(" ", " ");

        assert formatter.format(empty.rules).equals(" " + empty.selector() + " {}");
    }

    @Test
    void startBrace() {
        Formatter formatter = Formatter.compact().startBrace("\r\n");

        assert formatter.format(empty.rules).equals(empty.selector() + "{\r\n}");
    }

    @Test
    void endBrace() {
        Formatter formatter = Formatter.compact().endBrace("\r\n", "");

        assert formatter.format(empty.rules).equals(empty.selector() + "{\r\n}");
    }

    ValidatableStyle one = writeStyle(() -> {
        display.block();
    });

    @Test
    void propertyName() {
        Formatter formatter = Formatter.compact().propertyName(" ", " ");

        assert formatter.format(one.rules).equals(one.selector() + "{ display :block;}");
    }

    @Test
    void propertyValue() {
        Formatter formatter = Formatter.compact().propertyValue(" ", " ");

        assert formatter.format(one.rules).equals(one.selector() + "{display: block ;}");
    }

    @Test
    void propertyLine() {
        Formatter formatter = Formatter.compact().propertyLine("\r\n");

        assert formatter.format(one.rules).equals(one.selector() + "{display:block;\r\n}");
    }

    ValidatableStyle two = writeStyle(() -> {
        display.block().width(10, px);
    });

    @Test
    void properties() {
        Formatter formatter = Formatter.compact().propertyName(" ", "");

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
        Formatter formatter = Formatter.compact().endBrace("", " ");

        assert formatter.format(nest.rules).equals(nest.selector() + "{width:10px;} " + nest.selector() + ":hover{width:20px;} ");
    }

    ValidatableStyle color = writeStyle(() -> {
        font.color("#111111");
    });

    @Test
    void color() {
        Formatter formatter = Formatter.compact().color(Color::toRGB);

        assert formatter.format(color.rules).equals(color.selector() + "{color:rgb(18,18,18);}");
    }
}
