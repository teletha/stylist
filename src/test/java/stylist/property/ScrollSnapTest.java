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

class ScrollSnapTest extends StyleTester {

    @Test
    void snapType() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.snapType.yMandatory();
        });
        assert style.property("scroll-snap-type", "y mandatory");
    }

    @Test
    void snapTypeNone() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.snapType.none();
        });
        assert style.property("scroll-snap-type", "none");
    }

    @Test
    void snapTypeProximity() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.snapType.bothProximity();
        });
        assert style.property("scroll-snap-type", "both proximity");
    }

    @Test
    void snapAlign() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.snapAlign.center();
        });
        assert style.property("scroll-snap-align", "center");
    }

    @Test
    void snapAlignStart() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.snapAlign.start();
        });
        assert style.property("scroll-snap-align", "start");
    }

    @Test
    void snapStop() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.snapStop.always();
        });
        assert style.property("scroll-snap-stop", "always");
    }

    @Test
    void overscroll() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.overscroll.contain();
        });
        assert style.property("overscroll-behavior", "contain");
    }

    @Test
    void overscrollX() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.overscroll.x(Scroll.Behavior.none);
        });
        assert style.property("overscroll-behavior-x", "none");
    }

    @Test
    void overscrollY() {
        ValidatableStyle style = writeStyle(() -> {
            scroll.overscroll.y(Scroll.Behavior.auto);
        });
        assert style.property("overscroll-behavior-y", "auto");
    }
}
