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

class StyleRuleTest extends StyleTester {

    @Test
    void single() {
        Style style = () -> {
            display.block();
        };

        StyleRule rule = Stylist.create(style);
        assert rule.selector.match(style.selector());
        assert rule.properties.get("display").get().match("block");
    }

    @Test
    void hover() {
        Style style = () -> {
            display.block();

            $.hover(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = Stylist.create(style);
        assert rule.selector.match(style.selector());
        assert rule.properties.get("display").get().match("block");
        assert rule.children.size() == 1;

        StyleRule child = rule.children.get(0);
        assert child.selector.match(style.selector() + ":hover");
        assert child.properties.get("text-decoration").get().match("underline");
    }

    @Test
    void descendant() {
        Style style = () -> {
            display.block();

            $.descendant(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = Stylist.create(style);
        StyleRule child = rule.children.get(0);
        assert child.selector.match(style.selector() + " *");
        assert child.properties.get("text-decoration").get().match("underline");
    }

    @Test
    void child() {
        Style style = () -> {
            display.block();

            $.child(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = Stylist.create(style);
        StyleRule child = rule.children.get(0);
        assert child.selector.match(style.selector() + ">*");
        assert child.properties.get("text-decoration").get().match("underline");
    }

    @Test
    void parent() {
        Style style = () -> {
            display.block();

            $.parent().enabled(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = Stylist.create(style);
        StyleRule child = rule.children.get(0);
        assert child.selector.match("*:enabled>" + style.selector());
        assert child.properties.get("text-decoration").get().match("underline");
    }
}