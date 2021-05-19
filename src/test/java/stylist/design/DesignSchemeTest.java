/*
 * Copyright (C) 2021 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.design;

import java.util.Map;

import org.junit.jupiter.api.Test;

import stylist.CSSValue;
import stylist.value.Color;

class DesignSchemeTest {

    @Test
    void theme() {
        class Scheme extends DesignScheme {

            @Theme
            void one() {
            }
        }

        Scheme scheme = new Scheme();
        assert scheme.themes.size() == 1;
        assert scheme.themes.get(0).name.equals("one");
    }

    @Test
    void themes() {
        class Scheme extends DesignScheme {

            @Theme
            void one() {
            }

            @Theme
            void other() {
            }
        }

        Scheme scheme = new Scheme();
        assert scheme.themes.size() == 2;
        assert scheme.themes.get(0).name.equals("other");
        assert scheme.themes.get(1).name.equals("one");
    }

    @Test
    void variable() {
        class Scheme extends DesignScheme {

            @Theme
            void one() {
                primary = Color.White;
            }
        }

        Scheme scheme = new Scheme();
        Map<String, CSSValue> variables = scheme.themes.get(0).variables;
        assert variables.size() == 1;
        assert variables.containsKey("primary");
        assert variables.get("primary").equals(Color.White);

        assert scheme.primary.toString().equals("var(--primary)");
    }

    @Test
    void derivative() {
        class Scheme extends DesignScheme {

            @Theme
            void one() {
                primary = Color.White;
            }

            @Theme
            void other() {
                primary = Color.Black;
            }
        }

        Scheme scheme = new Scheme();
        Color opacified = scheme.primary.opacify(-0.2);

        Map<String, CSSValue> variables = scheme.themes.get(0).variables;
        assert variables.size() == 2;
        assert variables.containsKey("primary-opacify--02");
    }
}
