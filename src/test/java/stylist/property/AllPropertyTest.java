/*
 * Copyright (C) 2017 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.property;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;

/**
 * @version 2018/09/05 1:31:04
 */
class AllPropertyTest extends StyleTester {

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
