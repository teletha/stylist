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
interface ColorPaletteModel {

    @Icy.Property
    Color primary();

    @Icy.Property
    Color secondary();

    @Icy.Property
    Color accent();

    /**
     * Set background color.
     * 
     * @return
     */
    @Icy.Property
    Color background();

    /**
     * Set font color.
     * 
     * @return
     */
    @Icy.Property
    Color font();

    /**
     * Set link color.
     * 
     * @return
     */
    @Icy.Property
    default Color link() {
        return font();
    }
}
