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
 * @version 2018/08/30 18:32:42
 */
public class StyleRuleTest extends StyleTester {

    @Test
    public void single() {
        Style style = () -> {
            display.block();
        };

        StyleRule rule = StyleRule.create("$", style, false);
        assert rule.selector.equals("." + style.name());
        assert rule.properties.get("display").v.match("block");
    }

    @Test
    public void nest() {
        Style style = () -> {
            display.block();

            hover(() -> {
                text.decoration.underline();
            });
        };

        StyleRule rule = StyleRule.create("$", style, false);
        assert rule.selector.equals("." + style.name());
        assert rule.properties.get("display").v.match("block");
        assert rule.children.size() == 1;

        StyleRule child = rule.children.get(0);
        assert child.selector.equals("." + style.name() + ":hover");
        assert child.properties.get("text-decoration").v.match("underline");
    }
}
