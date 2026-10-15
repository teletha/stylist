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

class TypographyTest extends StyleTester {

    @Test
    void hyphensAuto() {
        ValidatableStyle style = writeStyle(() -> {
            text.hyphens.auto();
        });
        assert style.property("hyphens", "auto");
    }

    @Test
    void hyphensNone() {
        ValidatableStyle style = writeStyle(() -> {
            text.hyphens.none();
        });
        assert style.property("hyphens", "none");
    }

    @Test
    void hyphensManual() {
        ValidatableStyle style = writeStyle(() -> {
            text.hyphens.manual();
        });
        assert style.property("hyphens", "manual");
    }

    @Test
    void alignLast() {
        ValidatableStyle style = writeStyle(() -> {
            text.alignLast.center();
        });
        assert style.property("text-align-last", "center");
    }

    @Test
    void direction() {
        ValidatableStyle style = writeStyle(() -> {
            text.direction.rtl();
        });
        assert style.property("direction", "rtl");
    }

    @Test
    void unicodeBidi() {
        ValidatableStyle style = writeStyle(() -> {
            text.unicodeBidi.isolate();
        });
        assert style.property("unicode-bidi", "isolate");
    }

    @Test
    void unicodeBidiHyphenated() {
        ValidatableStyle style = writeStyle(() -> {
            text.unicodeBidi.bidiOverride();
        });
        assert style.property("unicode-bidi", "bidi-override");
    }

    @Test
    void writingMode() {
        ValidatableStyle style = writeStyle(() -> {
            text.writingMode.verticalRl();
        });
        assert style.property("writing-mode", "vertical-rl");
    }

    @Test
    void tabSize() {
        ValidatableStyle style = writeStyle(() -> {
            text.tabSize(4);
        });
        assert style.property("tab-size", "4");
    }

    @Test
    void userSelect() {
        ValidatableStyle style = writeStyle(() -> {
            text.userSelect.none();
        });
        assert style.property("user-select", "none");
    }

    @Test
    void userSelectAll() {
        ValidatableStyle style = writeStyle(() -> {
            text.userSelect.all();
        });
        assert style.property("user-select", "all");
    }
}
