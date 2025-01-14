/*
 * Copyright (C) 2023 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.property.helper;

import static stylist.value.Numeric.*;

import stylist.value.Numeric;
import stylist.value.Unit;

public class GridNumerics extends Numerics<GridNumerics> {

    public GridNumerics autoMin(double size, Unit unit) {
        values.add("minmax(" + num(size, unit) + ",auto)");
        return this;
    }

    public GridNumerics autoMax(double size, Unit unit) {
        values.add("minmax(auto," + num(size, unit) + ")");
        return this;
    }

    public GridNumerics repeatAutoFit(double minSize, Unit minUnit, double maxSize, Unit maxUnit) {
        return repeatAutoFit(num(minSize, minUnit), num(maxSize, maxUnit));
    }

    public GridNumerics repeatAutoFit(Numeric min, Numeric max) {
        values.add("repeat(auto-fit,minmax(" + min + "," + max + "))");
        return this;
    }

    public GridNumerics repeatAutoFill(double minSize, Unit minUnit, double maxSize, Unit maxUnit) {
        return repeatAutoFill(num(minSize, minUnit), num(maxSize, maxUnit));
    }

    public GridNumerics repeatAutoFill(Numeric min, Numeric max) {
        values.add("repeat(auto-fill,minmax(" + min + "," + max + "))");
        return this;
    }
}
