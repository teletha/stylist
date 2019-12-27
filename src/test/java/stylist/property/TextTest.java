/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import static stylist.Vendor.*;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;

class TextTest extends StyleTester {

    @Test
    void indent() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.indent(3, em);
        });
        assert parsed.property("text-indent", "3em");
    }

    @Test
    void align() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.align.center().verticalAlign.bottom();
        });
        assert parsed.property("text-align", "center");
        assert parsed.property("vertical-align", "bottom");
    }

    @Test
    void decoration() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.decoration.underline();
        });
        assert parsed.property("text-decoration", "underline");
    }

    @Test
    void overflow() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.overflow.ellipsis();
        });
        assert parsed.property("text-overflow", "ellipsis");
    }

    @Test
    void unselectable() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.unselectable();
        });
        assert parsed.property("user-select", "none", Mozilla, MS, Webkit);
        assert parsed.property("cursor", "default");

        ValidatableStyle selection = parsed.sub("selection");
        assert selection.property("background-color", "transparent");
    }

    @Test
    void shadowSingle() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.shadow($.shadow().offset(2, 2, px).blurRadius(1, px).color($.hsl(100, 100, 100)));
        });
        assert parsed.property("text-shadow", "2px 2px 1px hsl(100,100%,100%)");
    }

    @Test
    void shadowMultiple() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.shadow($.shadow().offset(2, 2, px), $.shadow().offset(1, 1, px));
        });
        assert parsed.property("text-shadow", "2px 2px,1px 1px");
    }

    @Test
    void transformCapitalize() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.transform.capitalize();
        });
        assert parsed.property("text-transform", "capitalize");
    }

    @Test
    void transformLowercase() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.transform.lower();
        });
        assert parsed.property("text-transform", "lowercase");
    }

    @Test
    void transformUppercase() {
        ValidatableStyle parsed = writeStyle(() -> {
            text.transform.upper();
        });
        assert parsed.property("text-transform", "uppercase");
    }
}
