/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import java.io.Serializable;

/**
 * @version 2018/08/31 11:14:03
 */
public interface Location extends Serializable {

    /**
     * <p>
     * Compute location name.
     * </p>
     * 
     * @return A location name.
     */
    default String name() {
        return LocationNamingStrategy.compute(this);
    }

    /**
     * <p>
     * Compute location name.
     * </p>
     * 
     * @return A location name.
     */
    default String[] names() {
        return new String[] {name()};
    }
}
