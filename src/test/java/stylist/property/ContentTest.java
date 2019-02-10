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

/**
 * @version 2018/08/30 18:37:14
 */
public class ContentTest extends StyleTester {

    @Test
    public void rgb() {
        ValidatableStyle parsed = writeStyle(() -> {
            content.text("test");
        });
        assert parsed.property("content", "'test'");

        parsed = writeStyle(() -> {
            content.attr("href");
        });
        assert parsed.property("content", "attr(href)");
    }
}
