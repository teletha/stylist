/*
 * Copyright (C) 2024 The STYLIST Development Team
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

class PaddingTest extends StyleTester {

    @Test
    void each() {
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
    void inline() {
        ValidatableStyle parsed = writeStyle(() -> {
            padding.inline(10, em);
        });
        assert parsed.property("padding-inline-start", "10em");
        assert parsed.property("padding-inline-end", "10em");
    }

    @Test
    void inlineStartEnd() {
        ValidatableStyle parsed = writeStyle(() -> {
            padding.inlineStart(10, em);
            padding.inlineEnd(20, px);
        });
        assert parsed.property("padding-inline-start", "10em");
        assert parsed.property("padding-inline-end", "20px");
    }

    @Test
    void block() {
        ValidatableStyle parsed = writeStyle(() -> {
            padding.block(10, em);
        });
        assert parsed.property("padding-block-start", "10em");
        assert parsed.property("padding-block-end", "10em");
    }

    @Test
    void blockStartEnd() {
        ValidatableStyle parsed = writeStyle(() -> {
            padding.blockStart(10, em);
            padding.blockEnd(20, px);
        });
        assert parsed.property("padding-block-start", "10em");
        assert parsed.property("padding-block-end", "20px");
    }

    @Test
    void auto() {
        ValidatableStyle parsed = writeStyle(() -> {
            padding.auto();
        });
        assert parsed.property("padding-inline-start", "auto");
        assert parsed.property("padding-inline-end", "auto");
    }

    @Test
    void shorthand() {
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