/*
 * Copyright (C) 2021 stylist Development Team
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

class ContentTest extends StyleTester {

    @Test
    void content() {
        ValidatableStyle parsed = writeStyle(() -> {
            content.text("test");
        });
        assert parsed.property("content", "'test'");

        parsed = writeStyle(() -> {
            content.attr("href");
        });
        assert parsed.property("content", "attr(href)");
    }

    @Test
    void multiText() {
        ValidatableStyle parsed = writeStyle(() -> {
            content.text("multi ").text("text");
        });
        assert parsed.property("content", "'multi ''text'");
    }

    @Test
    void multiAttr() {
        ValidatableStyle parsed = writeStyle(() -> {
            content.attr("title").attr("href");
        });
        assert parsed.property("content", "attr(title)attr(href)");
    }

    @Test
    void mix() {
        ValidatableStyle parsed = writeStyle(() -> {
            content.attr("title").text(" ").attr("href");
        });
        assert parsed.property("content", "attr(title)' 'attr(href)");
    }
}