/*
 * Copyright (C) 2021 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import stylist.PropertyDefinition;
import stylist.Vendor;

/**
 * @version 2018/08/30 18:23:29
 */
public class Appearance extends PropertyDefinition<Appearance> {

    /**
     * 
     */
    public Appearance() {
        super("appearance", null, Vendor.Mozilla, Vendor.Webkit, Vendor.MS);
    }

    /**
     * No special styling is applied. This is the default.
     */
    public Appearance none() {
        return value("none");
    }
}