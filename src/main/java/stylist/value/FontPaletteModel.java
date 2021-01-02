/*
 * Copyright (C) 2021 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import icy.manipulator.Icy;

@Icy
interface FontPaletteModel {

    @Icy.Property
    Font[] base();

    @Icy.Overload("base")
    private Font[] baseBySystem() {
        return new Font[] {
                // Generic
                Font.SystemUI,
                // For Mac
                Font.of("-apple-system"), Font.of("BlinkMacSystemFont"), Font.of("Helvetica Neue"),
                // For Windows
                Font.of("Yu Gothic UI"), Font.of("Verdana"), Font.of("Meiryo"),
                // fallback
                Font.SansSerif};
    }

    @Icy.Property
    Font title();

    @Icy.Property
    Font[] mono();

    @Icy.Overload("mono")
    private Font[] monoBySystem() {
        return new Font[] {
                // For Mac
                Font.of("Menlo"),
                // For Windows
                Font.of("Consolas"),
                // fallback
                Font.Monospace};
    }

    @Icy.Property
    default Font icon() {
        return Font.fromGoogle("Material Icons");
    }
}