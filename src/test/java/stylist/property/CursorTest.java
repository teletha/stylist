/*
 * Copyright (C) 2023 The STYLIST Development Team
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
import stylist.value.Vendor;

/**
 * @version 2018/09/05 11:47:49
 */
class CursorTest extends StyleTester {

    @Test
    void cursor() {
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
        assert parsed.property("cursor", "grab");
        assert parsed.property("cursor", Vendor.Webkit, "cursor", "-webkit-grab");

        parsed = writeStyle(() -> {
            cursor.grabbing();
        });
        assert parsed.property("cursor", "grabbing");
        assert parsed.property("cursor", Vendor.Webkit, "cursor", "-webkit-grabbing");
    }
}