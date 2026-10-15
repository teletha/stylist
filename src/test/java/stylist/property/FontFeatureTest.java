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

class FontFeatureTest extends StyleTester {

    @Test
    void featureSettings() {
        ValidatableStyle style = writeStyle(() -> {
            font.featureSettings("\"liga\" 1", "\"kern\" 0");
        });
        assert style.property("font-feature-settings", "\"liga\" 1, \"kern\" 0");
    }

    @Test
    void featureSettingsSingle() {
        ValidatableStyle style = writeStyle(() -> {
            font.featureSettings("\"palt\" 1");
        });
        assert style.property("font-feature-settings", "\"palt\" 1");
    }

    @Test
    void variationSettings() {
        ValidatableStyle style = writeStyle(() -> {
            font.variationSettings("\"wght\" 700", "\"wdth\" 100");
        });
        assert style.property("font-variation-settings", "\"wght\" 700, \"wdth\" 100");
    }

    @Test
    void opticalSizing() {
        ValidatableStyle style = writeStyle(() -> {
            font.opticalSizing.auto();
        });
        assert style.property("font-optical-sizing", "auto");
    }

    @Test
    void opticalSizingNone() {
        ValidatableStyle style = writeStyle(() -> {
            font.opticalSizing.none();
        });
        assert style.property("font-optical-sizing", "none");
    }
}
