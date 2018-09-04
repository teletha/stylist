/*
 * Copyright (C) 2018 stylist Development Team
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
 * @version 2018/08/30 18:37:07
 */
public class CursorTest extends StyleTester {

    @Test
    public void cursor() {
        ValidatableStyle parsed = writeStyle(() -> {
            cursor.help();
        });
        assert parsed.property("cursor", "help");

        parsed = writeStyle(() -> {
            cursor.alias();
        });
        assert parsed.property("cursor", "alias");

        parsed = writeStyle(() -> {
            cursor.copy();
        });
        assert parsed.property("cursor", "copy");

        parsed = writeStyle(() -> {
            cursor.grab();
        });
        assert parsed.property("cursor", "-webkit-grab", "grab");

        parsed = writeStyle(() -> {
            cursor.grabbing();
        });
        assert parsed.property("cursor", "-webkit-grabbing", "grabbing");
    }
}
