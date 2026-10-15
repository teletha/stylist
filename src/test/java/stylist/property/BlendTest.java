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

class BlendTest extends StyleTester {

    @Test
    void mixBlendMode() {
        ValidatableStyle style = writeStyle(() -> {
            blend.mix(Blend.Mode.multiply);
        });
        assert style.property("mix-blend-mode", "multiply");
    }

    @Test
    void mixBlendModeHyphenated() {
        ValidatableStyle style = writeStyle(() -> {
            blend.mix(Blend.Mode.colorDodge);
        });
        assert style.property("mix-blend-mode", "color-dodge");
    }

    @Test
    void backgroundBlendMode() {
        ValidatableStyle style = writeStyle(() -> {
            blend.background(Blend.Mode.overlay);
        });
        assert style.property("background-blend-mode", "overlay");
    }

    @Test
    void isolate() {
        ValidatableStyle style = writeStyle(() -> {
            blend.isolate();
        });
        assert style.property("isolation", "isolate");
    }

    @Test
    void auto() {
        ValidatableStyle style = writeStyle(() -> {
            blend.auto();
        });
        assert style.property("isolation", "auto");
    }
}