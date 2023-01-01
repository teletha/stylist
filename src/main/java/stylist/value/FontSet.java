/*
 * Copyright (C) 2023 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import stylist.Stylist;

public class FontSet extends CSSValue {

    /** The set of fonts. */
    private final List<String> set = new ArrayList();

    /**
     * Use local font.
     * 
     * @param font
     * @return
     */
    public final FontSet local(String font) {
        if (font != null) {
            if (font.startsWith("http")) {
                Stylist.useExternalStylesheet(font);
                font = FontInfo.parse(font);
            }
            set.add(hasSpace(font) ? "\"" + font + "\"" : font);
        }
        return this;
    }

    /**
     * Use local font.
     * 
     * @param font
     * @return
     */
    public final FontSet local(Font font) {
        if (font != null) {
            Stylist.useExternalStylesheet(font.uri);
            set.add(hasSpace(font.name) ? "\"" + font.name + "\"" : font.name);
        }
        return this;
    }

    /**
     * Use google font.
     * 
     * @param font
     * @return
     */
    public final FontSet fromGoogle(String font, String... params) {
        return local(Font.fromGoogle(font, params));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String valueFor(Vendor vendor) {
        return String.join(",", set);
    }

    /**
     * Find whitespace.
     * 
     * @param value
     * @return
     */
    private static boolean hasSpace(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (Character.isWhitespace(value.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static class FontInfo {

        /**
         * <p>
         * Locate font.
         * </p>
         * 
         * @param uri
         * @return
         */
        static String parse(String uri) {
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                URLConnection connection = new URL(uri).openConnection();
                connection.connect();
                connection.getInputStream().transferTo(out);

                String contents = out.toString();
                int start = contents.indexOf("font-family");
                int end = contents.indexOf(";", start);

                String name = contents.substring(start + 11, end).trim();

                if (name.charAt(0) == ':') {
                    name = name.substring(1).trim();
                }

                if (name.charAt(0) == '\'') {
                    name = name.substring(1, name.length() - 1);
                }

                return name;
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }
}