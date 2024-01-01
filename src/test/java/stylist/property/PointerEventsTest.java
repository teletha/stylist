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
 * @version 2018/08/30 18:39:58
 */
public class PointerEventsTest extends StyleTester {

    @Test
    public void pointerEventNone() {
        ValidatableStyle parsed = writeStyle(() -> {
            pointerEvents.none();
        });
        assert parsed.property("pointer-events", "none");
    }
}