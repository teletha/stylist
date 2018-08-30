/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import org.junit.jupiter.api.Test;

/**
 * @version 2018/08/30 18:33:07
 */
public class PseudoElementTest extends StyleTester {

    @Test
    public void before() {
        ValidatableStyle parsed = writeStyle(() -> {
            font.color(255, 255, 0);

            before(() -> {
                font.color(0, 255, 0);

                hover(() -> {
                    font.color(0, 0, 0);
                });
            });
        });
        assert parsed.property("color", "rgb(255,255,0)");

        ValidatableStyle before = parsed.sub("before");
        assert before.property("color", "rgb(0,255,0)");

        ValidatableStyle beforeHover = parsed.sub("hover::before");
        assert beforeHover.property("color", "rgb(0,0,0)");
    }

    @Test
    public void after() {
        ValidatableStyle parsed = writeStyle(() -> {
            font.color(255, 255, 0);

            after(() -> {
                font.color(0, 255, 0);

                hover(() -> {
                    font.color(0, 0, 0);
                });
            });
        });
        assert parsed.property("color", "rgb(255,255,0)");

        ValidatableStyle after = parsed.sub("after");
        assert after.property("color", "rgb(0,255,0)");

        ValidatableStyle afterHover = parsed.sub("hover::after");
        assert afterHover.property("color", "rgb(0,0,0)");
    }

    @Test
    public void text() {
        ValidatableStyle style = writeStyle(() -> {
            firstLetter(() -> {
                display.inline();
            });

            firstLine(() -> {
                display.inline();
            });
        });

        assert style.sub("first-letter").property("display", "inline");
        assert style.sub("first-line").property("display", "inline");
    }

    @Test
    public void selection() {
        ValidatableStyle style = writeStyle(() -> {
            selection(() -> {
                display.inline();
            });
        });
        assert style.sub("selection").property("display", "inline");
    }

}
