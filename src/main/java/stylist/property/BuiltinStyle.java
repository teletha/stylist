/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import stylist.Style;
import stylist.StyleDSL;
import stylist.value.Color;

/**
 * @version 2018/08/30 18:24:47
 */
class BuiltinStyle implements StyleDSL {

    /**
     * Make text unselectable.
     */
    Style unselectable = () -> {
        cursor.defaults();

        $.selection(() -> {
            background.color(Color.Transparent);
        });
    };
}
