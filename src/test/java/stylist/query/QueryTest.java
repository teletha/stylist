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

public class QueryTest extends StyleTester {

    Query media = Query.all().minWidth(600, px);

    @Test
    void media() {
        ValidatableStyle parsed = writeStyle(() -> {
            $.on(media, () -> {
                font.size(10, px);
            });
        });
        ValidatableStyle sub = parsed.sub("@media");
    }
}
