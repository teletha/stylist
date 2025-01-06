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
import stylist.value.Vendor;

class MarginTest extends StyleTester {

    @Test
    void each() {
        ValidatableStyle parsed = writeStyle(() -> {
            margin.top(10, em);
            margin.bottom(20, px);
            margin.left(30, percent);
            margin.right(new Numeric(10, ex));
        });
        assert parsed.property("margin-top", "10em");
        assert parsed.property("margin-bottom", "20px");
        assert parsed.property("margin-left", "30%");
        assert parsed.property("margin-right", "10ex");
    }

    @Test
    void inline() {
        ValidatableStyle parsed = writeStyle(() -> {
            margin.start(10, em);
            margin.end(20, px);
        });
        assert parsed.property("margin-inline-start", "10em");
        assert parsed.property("margin-inline-end", "20px");
    }

    @Test
    void auto() {
        ValidatableStyle parsed = writeStyle(() -> {
            margin.auto();
        });
        assert parsed.property("margin-left", "auto");
        assert parsed.property("margin-right", "auto");
    }

    @Test
    void shorthand() {
        ValidatableStyle parsed = writeStyle(() -> {
            margin.horizontal(1, em);
        });
        assert parsed.property("margin-left", "1em");
        assert parsed.property("margin-right", "1em");

        parsed = writeStyle(() -> {
            margin.vertical(1, em);
        });
        assert parsed.property("margin-top", "1em");
        assert parsed.property("margin-bottom", "1em");

        parsed = writeStyle(() -> {
            margin.size(1, em);
        });
        assert parsed.property("margin-left", "1em");
        assert parsed.property("margin-right", "1em");
        assert parsed.property("margin-top", "1em");
        assert parsed.property("margin-bottom", "1em");
    }

    @Test
    void calc() {
        Numeric one = new Numeric(1, em);
        Numeric two = new Numeric(2, px);

        ValidatableStyle parsed = writeStyle(() -> {
            margin.size(one.plus(two));
        });
        assert parsed.property("margin-left", "calc(1em + 2px)");
        assert parsed.property("margin-left", Vendor.Webkit, "margin-left", "-webkit-calc(1em + 2px)");
        assert parsed.property("margin-right", "calc(1em + 2px)");
        assert parsed.property("margin-right", Vendor.Webkit, "margin-right", "-webkit-calc(1em + 2px)");
        assert parsed.property("margin-top", "calc(1em + 2px)");
        assert parsed.property("margin-top", Vendor.Webkit, "margin-top", "-webkit-calc(1em + 2px)");
        assert parsed.property("margin-bottom", "calc(1em + 2px)");
        assert parsed.property("margin-bottom", Vendor.Webkit, "margin-bottom", "-webkit-calc(1em + 2px)");
    }
}