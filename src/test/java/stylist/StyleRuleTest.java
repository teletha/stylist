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
 * @version 2018/09/07 12:01:59
 */
public class StyleRuleTest extends StyleTester {

    @Test
    public void single() {
        Style style = () -> {
            display.block();
        };

        StyleRule rule = StyleRule.create(style);
        assert rule.selector.match("." + style.name());
        assert rule.properties.get("display").get().match("block");
    }

    @Test
    public void hover() {
        Style style = () -> {
            display.block();

            $.hover(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = StyleRule.create(style);
        assert rule.selector.match("." + style.name());
        assert rule.properties.get("display").get().match("block");
        assert rule.children.size() == 1;

        StyleRule child = rule.children.get(0);
        assert child.selector.match("." + style.name() + ":hover");
        assert child.properties.get("text-decoration").get().match("underline");
    }

    @Test
    public void descendant() {
        Style style = () -> {
            display.block();

            $.descendant(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = StyleRule.create(style);
        StyleRule child = rule.children.get(0);
        assert child.selector.match("." + style.name() + " *");
        assert child.properties.get("text-decoration").get().match("underline");
    }

    @Test
    public void child() {
        Style style = () -> {
            display.block();

            $.child(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = StyleRule.create(style);
        StyleRule child = rule.children.get(0);
        assert child.selector.match("." + style.name() + ">*");
        assert child.properties.get("text-decoration").get().match("underline");
    }

    @Test
    public void parent() {
        Style style = () -> {
            display.block();

            $.parent().enabled(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = StyleRule.create(style);
        StyleRule child = rule.children.get(0);
        assert child.selector.match("*:enabled>." + style.name());
        assert child.properties.get("text-decoration").get().match("underline");
    }
}
