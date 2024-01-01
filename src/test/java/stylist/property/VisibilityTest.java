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

class VisibilityTest extends StyleTester {

    @Test
    void visibility() {
        ValidatableStyle parsed = writeStyle(() -> {
            display.visibility.collapse();
        });
        assert parsed.property("visibility", "collapse");

        parsed = writeStyle(() -> {
            display.visibility.hidden();
        });
        assert parsed.property("visibility", "hidden");

        parsed = writeStyle(() -> {
            display.visibility.visible();
        });
        assert parsed.property("visibility", "visible");
    }
}