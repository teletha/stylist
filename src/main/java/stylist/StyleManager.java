/*
 * Copyright (C) 2025 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class StyleManager {

    /** 1byte charset. */
    private static final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    /** The identical counter. */
    private static final AtomicInteger counter = new AtomicInteger();

    /** The managed styles. */
    static final Map<Style, String> names = new ConcurrentHashMap();

    /**
     * Compute identifier for the specified {@link Style}.
     * 
     * @param style A target location.
     * @return An identifier.
     */
    static String computeName(Style style) {
        return names.computeIfAbsent(style, x -> {
            int id = counter.getAndIncrement();

            if (id == 0) {
                return ".A";
            } else {
                StringBuilder name = new StringBuilder(".");
                while (id != 0) {
                    name.append(chars[id % chars.length]);
                    id /= chars.length;
                }
                return name.toString();
            }
        });
    }
}
