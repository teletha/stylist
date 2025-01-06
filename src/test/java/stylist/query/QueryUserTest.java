/*
 * Copyright (C) 2025 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.query;

import org.junit.jupiter.api.Test;

import stylist.Query;
import stylist.StyleTester;

public class QueryUserTest extends StyleTester {

    @Test
    void media() {
        Query small = Query.all().width(0, 600, px);
        Query middle = Query.all().width(600, 1200, px);
        Query large = Query.all().width(1200, px);

        ValidatableStyle parsed = writeStyle(() -> {
            $.when(small, () -> {
                font.size(10, px);
            });

            $.when(middle, () -> {
                font.size(12, px);
            });

            $.when(large, () -> {
                font.size(14, px);
            });
        });

        ValidatableStyle smallStyle = parsed.subBy(small);
        assert smallStyle.property("font-size", "10px");

        ValidatableStyle middleStyle = parsed.subBy(middle);
        assert middleStyle.property("font-size", "12px");

        ValidatableStyle largeStyle = parsed.subBy(large);
        assert largeStyle.property("font-size", "14px");
    }

    @Test
    void container() {
        Query small = Query.container().width(0, 600, px);
        Query middle = Query.container().width(600, 1200, px);
        Query large = Query.container().width(1200, px);

        ValidatableStyle parsed = writeStyle(() -> {
            $.when(small, () -> {
                font.size(10, px);
            });

            $.when(middle, () -> {
                font.size(12, px);
            });

            $.when(large, () -> {
                font.size(14, px);
            });
        });

        ValidatableStyle smallStyle = parsed.subBy(small);
        assert smallStyle.property("font-size", "10px");

        ValidatableStyle middleStyle = parsed.subBy(middle);
        assert middleStyle.property("font-size", "12px");

        ValidatableStyle largeStyle = parsed.subBy(large);
        assert largeStyle.property("font-size", "14px");
    }
}
