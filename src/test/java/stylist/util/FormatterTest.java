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

import stylist.StyleRule;
import stylist.StyleTester;

/**
 * @version 2018/09/01 19:26:08
 */
class FormatterTest extends StyleTester {

    StyleRule empty = writeStyle("#empty", () -> {
    });

    StyleRule one = writeStyle("#one", () -> {
        display.block();
    });

    @Test
    void selector() {
        Formatter formatter = Formatter.compact().selector(" ", " ");

        assert formatter.format(empty).equals(" #empty {}");
    }

    @Test
    void startBrace() {
        Formatter formatter = Formatter.compact().startBrace("\r\n");

        assert formatter.format(empty).equals("#empty{\r\n}");
    }

    @Test
    void endBrace() {
        Formatter formatter = Formatter.compact().endBrace("\r\n");

        assert formatter.format(empty).equals("#empty{\r\n}");
    }

    @Test
    void propertyName() {
        Formatter formatter = Formatter.compact().propertyName(" ", " ");

        assert formatter.format(one).equals("#one{ display :block;}");
    }

    @Test
    void propertyValue() {
        Formatter formatter = Formatter.compact().propertyValue(" ", " ");

        assert formatter.format(one).equals("#one{display: block ;}");
    }

    @Test
    void propertyLine() {
        Formatter formatter = Formatter.compact().propertyLine("\r\n");

        assert formatter.format(one).equals("#one{display:block;\r\n}");
    }
}
