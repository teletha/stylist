/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import stylist.PropertyDefinition;
import stylist.value.Numeric;
import stylist.value.Unit;

/**
 * @version 2018/08/30 18:25:57
 */
public class Line extends PropertyDefinition<Line> {

    /**
     * <p>
     * On inline elements, the line-height CSS property specifies the height that is used in the
     * calculation of the line box height. On block level elements, line-height specifies the
     * minimal height of line boxes within the element.
     * </p>
     * 
     * @param size
     * @return
     */
    public Line height(double size) {
        return value("line-height", size);
    }

    /**
     * <p>
     * On inline elements, the line-height CSS property specifies the height that is used in the
     * calculation of the line box height. On block level elements, line-height specifies the
     * minimal height of line boxes within the element.
     * </p>
     * 
     * @param size
     * @return
     */
    public Line height(Numeric size) {
        return value("line-height", size);
    }

    /**
     * <p>
     * On inline elements, the line-height CSS property specifies the height that is used in the
     * calculation of the line box height. On block level elements, line-height specifies the
     * minimal height of line boxes within the element.
     * </p>
     * 
     * @param size
     * @return
     */
    public Line height(double size, Unit unit) {
        return height(new Numeric(size, unit));
    }
}
