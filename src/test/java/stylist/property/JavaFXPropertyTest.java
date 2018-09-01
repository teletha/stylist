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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import stylist.StyleTester;
import stylist.Stylist;
import stylist.Vendor;

/**
 * @version 2018/09/01 20:24:59
 */
class JavaFXPropertyTest extends StyleTester {

    @BeforeAll
    static void init() {
        Stylist.setCurrentVendor(Vendor.JavaFX);
    }

    @AfterAll
    static void restore() {
        Stylist.setCurrentVendor(Vendor.Standard);
    }

    @Test
    void width() {
        ValidatableStyle style = writeStyle(() -> {
            display.width(10, px);
            display.maxWidth(10, px);
            display.minWidth(10, px);
        });
        assert style.property("-fx-pref-width", "10px");
        assert style.property("-fx-max-width", "10px");
        assert style.property("-fx-min-width", "10px");
    }

    @Test
    void height() {
        ValidatableStyle style = writeStyle(() -> {
            display.height(10, px);
            display.maxHeight(10, px);
            display.minHeight(10, px);
        });
        assert style.property("-fx-pref-height", "10px");
        assert style.property("-fx-max-height", "10px");
        assert style.property("-fx-min-height", "10px");
    }
}
