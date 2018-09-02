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

import stylist.Vendor;
import stylist.value.Color;

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
        properties.compactTo("padding", "0", sides("padding-*"));
        properties.compactTo("border-width", "0", sides("border-*-width"));
        properties.compactTo("border-style", "solid", sides("border-*-style"));
        properties.compactTo("border-color", Color.Transparent, sides("border-*-color"));
        properties.value("cursor", value -> cursor.getOrDefault(value, value));

        // assign prefix and map special name
        properties.keys(key -> Vendor.JavaFX + propertyNames.getOrDefault(key, key));
    }

    /**
     * Helper method to build side names.
     * 
     * @param template
     * @return
     */
    private static String[] sides(String template) {
        String[] sides = {"top", "right", "bottom", "left"};

        for (int i = 0; i < sides.length; i++) {
            sides[i] = template.replace("*", sides[i]);
        }
        return sides;
    }
}
