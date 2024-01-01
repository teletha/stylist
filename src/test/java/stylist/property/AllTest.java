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

/**
 * @version 2018/09/05 1:31:04
 */
class AllTest extends StyleTester {

    @Test
    void inherit() {
        ValidatableStyle parsed = writeStyle(() -> {
            all.inherit();
        });
        assert parsed.property("all", "inherit");
    }

    @Test
    void initial() {
        ValidatableStyle parsed = writeStyle(() -> {
            all.initial();
        });
        assert parsed.property("all", "initial");
    }

    @Test
    void unset() {
        ValidatableStyle parsed = writeStyle(() -> {
            all.unset();
        });
        assert parsed.property("all", "unset");
    }
}