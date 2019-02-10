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

import org.junit.jupiter.api.Test;

import stylist.StyleTester;

/**
 * @version 2018/08/30 18:39:53
 */
public class PositionTest extends StyleTester {

    @Test
    public void relative() {
        ValidatableStyle parsed = writeStyle(() -> {
            position.relative();

            assert position.isRelative() == true;
            assert position.isAbsolute() == false;
        });
        assert parsed.property("position", "relative");
    }

    @Test
    public void absolute() {
        ValidatableStyle parsed = writeStyle(() -> {
            position.absolute();

            assert position.isRelative() == false;
            assert position.isAbsolute() == true;
        });
        assert parsed.property("position", "absolute");
    }

    @Test
    public void fixed() {
        ValidatableStyle parsed = writeStyle(() -> {
            position.fixed();

            assert position.isRelative() == false;
            assert position.isAbsolute() == false;
        });
        assert parsed.property("position", "fixed");
    }

    @Test
    public void position() {
        ValidatableStyle parsed = writeStyle(() -> {
            position.top(1, px).right(2, px).bottom(3, px).left(4, px);
        });
        assert parsed.property("top", "1px");
        assert parsed.property("right", "2px");
        assert parsed.property("bottom", "3px");
        assert parsed.property("left", "4px");
    }
}
