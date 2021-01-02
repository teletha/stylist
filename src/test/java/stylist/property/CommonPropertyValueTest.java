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

/**
 * @version 2018/09/05 1:31:04
 */
class CommonPropertyValueTest extends StyleTester {

    @Test
    void inherit() {
        ValidatableStyle parsed = writeStyle(() -> {
            display.inherit();
        });
        assert parsed.property("display", "inherit");
    }

    @Test
    void initial() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.align.initial();
        });
        assert parsed.property("text-align", "initial");
    }

    @Test
    void unset() {
        ValidatableStyle parsed = writeStyle(() -> {
            cursor.unset();
        });
        assert parsed.property("cursor", "unset");
    }
}