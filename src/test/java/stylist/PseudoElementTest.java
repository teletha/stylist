/*
 * Copyright (C) 2021 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import org.junit.jupiter.api.Test;

import stylist.value.Vendor;

/**
 * @version 2018/09/05 11:53:49
 */
class PseudoElementTest extends StyleTester {

    @Test
    void before() {
        ValidatableStyle parsed = writeStyle(() -> {
            font.color(255, 255, 0);

            $.before(() -> {
                font.color(0, 255, 0);

                $.hover(() -> {
                    font.color(0, 0, 0);
                });
            });
        });
        assert parsed.property("color", "hsl(60,100%,50%)");

        ValidatableStyle before = parsed.sub("before");
        assert before.property("color", "hsl(120,100%,50%)");

        ValidatableStyle beforeHover = parsed.sub("hover::before");
        assert beforeHover.property("color", "black");
    }

    @Test
    void after() {
        ValidatableStyle parsed = writeStyle(() -> {
            font.color(255, 255, 0);

            $.after(() -> {
                font.color(0, 255, 0);

                $.hover(() -> {
                    font.color(0, 0, 0);
                });
            });
        });
        assert parsed.property("color", "hsl(60,100%,50%)");

        ValidatableStyle after = parsed.sub("after");
        assert after.property("color", "hsl(120,100%,50%)");

        ValidatableStyle afterHover = parsed.sub("hover::after");
        assert afterHover.property("color", "black");
    }

    @Test
    void text() {
        ValidatableStyle style = writeStyle(() -> {
            $.firstLetter(() -> {
                display.inline();
            });

            $.firstLine(() -> {
                display.inline();
            });
        });

        assert style.sub("first-letter").property("display", "inline");
        assert style.sub("first-line").property("display", "inline");
    }

    @Test
    void selection() {
        ValidatableStyle style = writeStyle(() -> {
            $.selection(() -> {
                display.inline();
            });
        });
        assert style.sub("selection", Vendor.Mozilla).property("display", "inline");
    }

}