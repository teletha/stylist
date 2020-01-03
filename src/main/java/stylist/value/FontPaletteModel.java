/*
 * Copyright (C) 2018 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.value;

import icy.manipulator.Icy;

@Icy
interface FontPaletteModel {

    @Icy.Property
    Font base();

    @Icy.Property
    Font title();

    @Icy.Property
    default Font mono() {
        return Font.Monospace;
    }
}
