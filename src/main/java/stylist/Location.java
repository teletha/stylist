/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

/**
 * @version 2018/09/08 22:41:00
 */
public interface Location {

    /**
     * Compute location name.
     * 
     * @return A location name.
     */
    default String name() {
        return Stylist.id(this);
    }

    /**
     * Compute location name.
     * 
     * @return A location name.
     */
    default String[] names() {
        return new String[] {name()};
    }

    /**
     * Describe detailed information for this {@link Location}.
     * 
     * @return
     */
    default String detail() {
        return ValuedStyle.locate(this);
    }
}
