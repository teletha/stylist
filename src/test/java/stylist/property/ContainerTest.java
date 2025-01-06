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

class ContainerTest extends StyleTester {

    @Test
    void size() {
        ValidatableStyle style = writeStyle(() -> {
            container.size();
        });
        assert style.property("container-type", "size");
    }

    @Test
    void inlineSize() {
        ValidatableStyle style = writeStyle(() -> {
            container.inlineSize();
        });
        assert style.property("container-type", "inline-size");
    }

    @Test
    void named() {
        ValidatableStyle style = writeStyle(() -> {
            container.name("test");
        });
        assert style.property("container-name", "test");
    }
}