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

import org.junit.jupiter.api.Test;

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
        assert scheme.variablesFor("one") != null;
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
        assert scheme.variablesFor("one") != null;
        assert scheme.variablesFor("other") != null;
    }

    @Test
    void variable() {
        class Scheme extends AbstractDesignScheme {

            @Theme
            void one() {
                primary = Color.White;
            }
        }

        Scheme scheme = new Scheme();
        assert scheme.variablesFor("one").get("primary").is(Color.White);
        assert scheme.primary.toString().equals("var(--primary)");
    }

    @Test
    void colorAdjustHue() {
        class Scheme extends AbstractDesignScheme {

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

        // use the derivative value
        scheme.primary.adjustHue(10);

        assert scheme.variablesFor("one").get("primary").is(Color.White);
        assert scheme.variablesFor("one").get("primary-adjustHue-10").is(Color.hsl(10, 0, 100));

        assert scheme.variablesFor("other").get("primary").is(Color.Black);
        assert scheme.variablesFor("other").get("primary-adjustHue-10").is(Color.hsl(10, 0, 0));
    }

    @Test
    void colorSaturate() {
        class Scheme extends AbstractDesignScheme {

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

        // use the derivative value
        scheme.primary.saturate(10);

        assert scheme.variablesFor("one").get("primary").is(Color.White);
        assert scheme.variablesFor("one").get("primary-saturate-10").is(Color.hsl(0, 10, 100));

        assert scheme.variablesFor("other").get("primary").is(Color.Black);
        assert scheme.variablesFor("other").get("primary-saturate-10").is(Color.hsl(0, 10, 0));
    }

    @Test
    void colorLighten() {
        class Scheme extends AbstractDesignScheme {

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

        // use the derivative value
        scheme.primary.lighten(10);

        assert scheme.variablesFor("one").get("primary").is(Color.White);
        assert scheme.variablesFor("one").get("primary-lighten-10").is(Color.hsl(0, 0, 100));

        assert scheme.variablesFor("other").get("primary").is(Color.Black);
        assert scheme.variablesFor("other").get("primary-lighten-10").is(Color.hsl(0, 0, 10));
    }

    @Test
    void colorLightenWithDirection() {
        class Scheme extends AbstractDesignScheme {

            @Theme
            void one() {
                primary = Color.White;
                secondary = Color.Black;
            }

            @Theme
            void other() {
                primary = Color.Black;
                secondary = Color.White;
            }
        }

        Scheme scheme = new Scheme();

        // use the derivative value
        scheme.primary.lighten(scheme.secondary, 10);

        assert scheme.variablesFor("one").get("primary").is(Color.White);
        assert scheme.variablesFor("one").get("primary-lightenD-10").is(Color.hsl(0, 0, 90));

        assert scheme.variablesFor("other").get("primary").is(Color.Black);
        assert scheme.variablesFor("other").get("primary-lightenD-10").is(Color.hsl(0, 0, 10));
    }

    @Test
    void colorOpacify() {
        class Scheme extends AbstractDesignScheme {

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

        // use the derivative value
        scheme.primary.opacify(-0.2);

        assert scheme.variablesFor("one").get("primary").is(Color.White);
        assert scheme.variablesFor("one").get("primary-opacify--02").is(Color.hsl(0, 0, 100, 0.8));

        assert scheme.variablesFor("other").get("primary").is(Color.Black);
        assert scheme.variablesFor("other").get("primary-opacify--02").is(Color.hsl(0, 0, 0, 0.8));
    }
}
