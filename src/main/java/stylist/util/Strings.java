/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.util;

/**
 * @version 2018/08/30 18:08:58
 */
public class Strings {

    /**
     * <p>
     * Convert to hyphened lower case.
     * </p>
     * 
     * @param value
     * @return
     */
    public static String hyphenate(String value) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);

            if (c == '_') {
                builder.append('-');
            } else if (Character.isUpperCase(c)) {
                if (i != 0) {
                    builder.append('-');
                }
                builder.append(Character.toLowerCase(c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * <p>
     * Convert to hyphened lower case.
     * </p>
     * 
     * @param value
     * @return
     */
    public static String unhyphenate(String value) {
        StringBuilder builder = new StringBuilder();
        boolean hyphen = false;

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);

            if (c == '-') {
                hyphen = true;
            } else if (hyphen) {
                hyphen = false;
                builder.append(Character.toUpperCase(c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * <p>
     * Find whitespace.
     * </p>
     * 
     * @param value
     * @return
     */
    public static boolean hasSpace(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (Character.isWhitespace(value.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
