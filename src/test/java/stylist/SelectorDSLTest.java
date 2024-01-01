/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

class SelectorDSLTest {

    @Test
    void combinator() throws Exception {
        test(selector().prev(), "*+$");
        test(selector().prev().checked(), "*:checked+$");
        test(selector().next(), "$+*");
        test(selector().next().disabled(), "$+*:disabled");
        test(selector().child(), "$>*");
        test(selector().descendant(), "$ *");
        test(selector().select("#id"), "$ #id");
        test(selector().ancestor(), "* $");
        test(selector().parent(), "*>$");
        test(selector().parent().enabled(), "*:enabled>$");
        test(selector().prevs(), "*~$");
        test(selector().nexts(), "$~*");
    }

    private static final NamedLocation loc1 = new NamedLocation(".loc1");

    @Test
    void with() throws Exception {
        test(selector().with(loc1), "$.loc1");
        test(selector().hover().with(loc1), "$.loc1:hover");
    }

    @Test
    void combinatorWith() throws Exception {
        test(selector().prev().with(loc1), ".loc1+$");
        test(selector().prev().with(loc1).checked(), ".loc1:checked+$");
    }

    @Test
    void pseudoClass() throws Exception {
        test(selector().active(), "$:active");
        test(selector().disabled(), "$:disabled");
        test(selector().enabled(), "$:enabled");
        test(selector().hover(), "$:hover");
    }

    @Test
    void pseudoClasses() throws Exception {
        test(selector().active().hover(), "$:active:hover");
    }

    @Test
    void pseudoElement() throws Exception {
        test(selector -> selector.before(null), "$::before");
    }

    @Test
    void pseudoClassAndElement() throws Exception {
        test(selector -> selector.hover().before(null), "$:hover::before");
    }

    @Test
    void attribute() {
        test(selector().attr("name").exist(), "$[name]");
        test(selector().attr("name").is("value"), "$[name=\"value\"]");
    }

    @Test
    void attributeCaseSensitive() {
        test(selector().attr("name").ignoreCase().exist(), "$[name]");
        test(selector().attr("name").ignoreCase().is("value"), "$[name=\"value\" i]");
    }

    @Test
    void next() {
        test(selector -> selector.next(null), "$+*");
        test(selector -> selector.next("next", null), "$+next");
    }

    /**
     * Helper method.
     */
    private SelectorDSL selector() {
        return SelectorDSL.create(null);
    }

    /**
     * Helper method.
     * 
     * @param selector
     * @param expected
     */
    private void test(SelectorDSL selector, String expected) {
        assert selector.toString().equals(expected);
    }

    /**
     * Helper method.
     * 
     * @param selector
     * @param expected
     */
    private void test(Consumer<SelectorDSL> user, String expected) {
        SelectorDSL selector = selector();
        user.accept(selector);
        assert selector.toString().equals(expected);
    }

    @SuppressWarnings("serial")
    private static final class NamedLocation implements Style {

        private final String name;

        /**
         * @param name
         */
        private NamedLocation(String name) {
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String selector() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void style() {
        }
    }
}