/*
 * Copyright (C) 2025 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.property.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import stylist.value.Numeric;
import stylist.value.Unit;

public class Numerics<Self extends Numerics> {

    protected final List<Object> values = new ArrayList();

    /**
     * Configure size.
     * 
     * @param size
     * @param unit
     * @return
     */
    public Self size(double size, Unit unit) {
        return size(Numeric.num(size, unit));
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
    public Self size(double size1, Unit unit1, double size2, Unit unit2) {
        return size(Numeric.num(size1, unit1), Numeric.num(size2, unit2));
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
    public Self size(double size1, Unit unit1, double size2, Unit unit2, double size3, Unit unit3) {
        return size(Numeric.num(size1, unit1), Numeric.num(size2, unit2), Numeric.num(size3, unit3));
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
    public Self size(double size1, Unit unit1, double size2, Unit unit2, double size3, Unit unit3, double size4, Unit unit4) {
        return size(Numeric.num(size1, unit1), Numeric.num(size2, unit2), Numeric.num(size3, unit3), Numeric.num(size4, unit4));
    }

    /**
     * Configure size.
     * 
     * @param size1
     * @param size2
     * @return
     */
    public Self size(double size1, double size2, Unit unit) {
        return size(Numeric.num(size1, unit), Numeric.num(size2, unit));
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
    public Self size(double size1, double size2, double size3, Unit unit) {
        return size(Numeric.num(size1, unit), Numeric.num(size2, unit), Numeric.num(size3, unit));
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
    public Self size(double size1, double size2, double size3, double size4, Unit unit) {
        return size(Numeric.num(size1, unit), Numeric.num(size2, unit), Numeric.num(size3, unit), Numeric.num(size4, unit));
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
    public Self size(double size1, double size2, double size3, double size4, double size5, Unit unit) {
        return size(Numeric.num(size1, unit), Numeric.num(size2, unit), Numeric.num(size3, unit), Numeric.num(size4, unit), Numeric
                .num(size5, unit));
    }

    /**
     * Confiure varible size.
     * 
     * @param sizes
     * @return
     */
    public Self size(Numeric... sizes) {
        return size(List.of(sizes));
    }

    /**
     * Confiure varible size.
     * 
     * @param sizes
     * @return
     */
    public Self size(List<Numeric> sizes) {
        values.addAll(sizes);
        return (Self) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return values.stream().map(Object::toString).collect(Collectors.joining(" "));
    }
}
