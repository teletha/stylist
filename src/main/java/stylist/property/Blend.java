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

import stylist.PropertyDefinition;

public class Blend extends PropertyDefinition<Blend> {

    public Blend() {
        super();
    }

    public Blend mix(Mode mode) {
        return value("mix-blend-mode", mode.value);
    }

    public Blend background(Mode mode) {
        return value("background-blend-mode", mode.value);
    }

    public Blend isolation(boolean isolate) {
        return value("isolation", isolate ? "isolate" : "auto");
    }

    public Blend isolate() {
        return isolation(true);
    }

    public Blend auto() {
        return isolation(false);
    }

    public enum Mode {
        normal,
        multiply,
        screen,
        overlay,
        darken,
        lighten,
        colorDodge("color-dodge"),
        colorBurn("color-burn"),
        hardLight("hard-light"),
        softLight("soft-light"),
        difference,
        exclusion,
        hue,
        saturation,
        color,
        luminosity;

        public final String value;

        private Mode() {
            this.value = name().toLowerCase();
        }

        private Mode(String value) {
            this.value = value;
        }
    }
}