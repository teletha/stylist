/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;
import stylist.value.Numeric;

/**
 * @version 2018/08/30 18:40:05
 */
public class PaddingTest extends StyleTester {

    @Test
    public void each() {
        ValidatableStyle parsed = writeStyle(() -> {
            padding.top(10, em);
            padding.bottom(20, px);
            padding.left(30, percent);
            padding.right(new Numeric(10, ex));
        });
        assert parsed.property("padding-top", "10em");
        assert parsed.property("padding-bottom", "20px");
        assert parsed.property("padding-left", "30%");
        assert parsed.property("padding-right", "10ex");
    }

    @Test
    public void auto() {
        ValidatableStyle parsed = writeStyle(() -> {
            padding.auto();
        });
        assert parsed.property("padding-left", "auto");
        assert parsed.property("padding-right", "auto");
    }

    @Test
    public void shorthand() {
        ValidatableStyle parsed = writeStyle(() -> {
            padding.horizontal(1, em);
        });
        assert parsed.property("padding-left", "1em");
        assert parsed.property("padding-right", "1em");

        parsed = writeStyle(() -> {
            padding.vertical(1, em);
        });
        assert parsed.property("padding-top", "1em");
        assert parsed.property("padding-bottom", "1em");

        parsed = writeStyle(() -> {
            padding.size(1, em);
        });
        assert parsed.property("padding-left", "1em");
        assert parsed.property("padding-right", "1em");
        assert parsed.property("padding-top", "1em");
        assert parsed.property("padding-bottom", "1em");
    }
}
