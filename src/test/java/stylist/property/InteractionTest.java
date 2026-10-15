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
import stylist.value.Color;

class InteractionTest extends StyleTester {

    @Test
    void touchActionNone() {
        ValidatableStyle style = writeStyle(() -> {
            interaction.touch.none();
        });
        assert style.property("touch-action", "none");
    }

    @Test
    void touchActionPan() {
        ValidatableStyle style = writeStyle(() -> {
            interaction.touch.panXY();
        });
        assert style.property("touch-action", "pan-x pan-y");
    }

    @Test
    void touchActionManipulation() {
        ValidatableStyle style = writeStyle(() -> {
            interaction.touch.manipulation();
        });
        assert style.property("touch-action", "manipulation");
    }

    @Test
    void caretColor() {
        ValidatableStyle style = writeStyle(() -> {
            interaction.caret.color(Color.Black);
        });
        assert style.property("caret-color", "black");
    }

    @Test
    void accentColor() {
        ValidatableStyle style = writeStyle(() -> {
            interaction.accent.color(255, 0, 0);
        });
        assert style.property("accent-color", "hsl(0,100%,50%)");
    }

    @Test
    void resize() {
        ValidatableStyle style = writeStyle(() -> {
            interaction.resize.both();
        });
        assert style.property("resize", "both");
    }

    @Test
    void willChange() {
        ValidatableStyle style = writeStyle(() -> {
            interaction.willChange.add("transform").add("opacity");
        });
        assert style.property("will-change", "transform, opacity");
    }

    @Test
    void willChangeAuto() {
        ValidatableStyle style = writeStyle(() -> {
            interaction.willChange.add("transform").auto();
        });
        assert style.property("will-change", "auto");
    }
}