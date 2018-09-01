/*
 * Copyright (C) 2017 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.util;

import java.util.Map;
import java.util.function.Consumer;

import kiss.Variable;
import stylist.Vendor;

/**
 * @version 2018/09/01 21:21:24
 */
public class JavaFXLizer implements Consumer<Properties> {

    /** The property name mapping. */
    private static final Map<String, String> propertyNames = Map.of("width", "pref-width", "height", "pref-height");

    /** The property value mapping. */
    private static final Map<String, String> cursor = Map.of("pointer", "hand");

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(Properties properties) {
        properties.compactTo("padding", "0", "padding-top", "padding-right", "padding-bottom", "padding-left");
        properties.value("cursor", value -> cursor.getOrDefault(value, value));

        Variable<String> variable = properties.get("border-bottom");
        if (variable.isPresent()) {
            System.out.println(variable);
        }

        // assign prefix and map special name
        properties.keys(key -> Vendor.JavaFX + propertyNames.getOrDefault(key, key));
    }
}
