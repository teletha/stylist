/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property.helper;

import stylist.value.Numeric;
import stylist.value.Unit;

public interface WidthHelper<R> {

    default R width(double width, Unit unit) {
        return width(Numeric.num(width, unit));
    }

    R width(Numeric width);
}