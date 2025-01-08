/*
 * Copyright (C) 2025 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.property;

import stylist.value.Numeric;
import stylist.value.Unit;

public interface SizeDefinable<Self> {

    /**
     * Configure size.
     * 
     * @param size
     * @param unit
     * @return
     */
    default Self size(double size, Unit unit) {
        return size(Numeric.of(size, unit));
    }

    /**
     * Configure size.
     * 
     * @param size1
     * @param unit1
     * @param size2
     * @param unit2
     * @return
     */
    default Self size(double size1, Unit unit1, double size2, Unit unit2) {
        return size(Numeric.of(size1, unit1), Numeric.of(size2, unit2));
    }

    /**
     * Configure size.
     * 
     * @param size1
     * @param unit1
     * @param size2
     * @param unit2
     * @param size3
     * @param unit3
     * @return
     */
    default Self size(double size1, Unit unit1, double size2, Unit unit2, double size3, Unit unit3) {
        return size(Numeric.of(size1, unit1), Numeric.of(size2, unit2), Numeric.of(size3, unit3));
    }

    /**
     * Configure size.
     * 
     * @param size1
     * @param unit1
     * @param size2
     * @param unit2
     * @param size3
     * @param unit3
     * @param size4
     * @param unit4
     * @return
     */
    default Self size(double size1, Unit unit1, double size2, Unit unit2, double size3, Unit unit3, double size4, Unit unit4) {
        return size(Numeric.of(size1, unit1), Numeric.of(size2, unit2), Numeric.of(size3, unit3), Numeric.of(size4, unit4));
    }

    /**
     * Configure size.
     * 
     * @param size1
     * @param size2
     * @return
     */
    default Self size(double size1, double size2, Unit unit) {
        return size(Numeric.of(size1, unit), Numeric.of(size2, unit));
    }

    /**
     * Configure size.
     * 
     * @param size1
     * @param size2
     * @param size3
     * @param unit
     * @return
     */
    default Self size(double size1, double size2, double size3, Unit unit) {
        return size(Numeric.of(size1, unit), Numeric.of(size2, unit), Numeric.of(size3, unit));
    }

    /**
     * Configure size.
     * 
     * @param size1
     * @param size2
     * @param size3
     * @param size4
     * @param unit
     * @return
     */
    default Self size(double size1, double size2, double size3, double size4, Unit unit) {
        return size(Numeric.of(size1, unit), Numeric.of(size2, unit), Numeric.of(size3, unit), Numeric.of(size4, unit));
    }

    /**
     * Configure size.
     * 
     * @param size1
     * @param size2
     * @param size3
     * @param size4
     * @param size5
     * @param unit
     * @return
     */
    default Self size(double size1, double size2, double size3, double size4, double size5, Unit unit) {
        return size(Numeric.of(size1, unit), Numeric.of(size2, unit), Numeric.of(size3, unit), Numeric.of(size4, unit), Numeric
                .of(size5, unit));
    }

    /**
     * Confiure varible size.
     * 
     * @param sizes
     * @return
     */
    Self size(Numeric... sizes);
}
