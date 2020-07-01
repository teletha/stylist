/*
 * Copyright (C) 2020 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;
import stylist.Vendor;

/**
 * @version 2018/09/05 11:46:51
 */
class CalcTest extends StyleTester {

    private static final Numeric one = new Numeric(1, px);

    private static final Numeric two = new Numeric(2, em);

    @Test
    void normal() {
        ValidatableStyle style = writeStyle(() -> {
            display.width(one);
        });
        assert style.property("width", "1px");
    }

    @Test
    void add() {
        ValidatableStyle style = writeStyle(() -> {
            display.width(one.add(two));
        });
        assert style.property("width", "calc(1px + 2em)");
        assert style.property("width", Vendor.Webkit, "width", "-webkit-calc(1px + 2em)");
    }

    @Test
    void inPrefixedProperty() {
        ValidatableStyle style = writeStyle(() -> {
            transform.translateY(one.add(two));
        });
        assert style.property("transform", "translateY(calc(1px + 2em))");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "translateY(-webkit-calc(1px + 2em))");
    }

    @Test
    void noneInPrefixedProperty() {
        ValidatableStyle style = writeStyle(() -> {
            transform.translateY(one);
        });
        assert style.property("transform", "translateY(1px)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "translateY(1px)");
    }
}