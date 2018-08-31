/*
 * Copyright (C) 2018 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist;

import org.junit.jupiter.api.Test;

/**
 * @version 2018/08/31 11:44:35
 */
class NamingTest extends StyleTester {

    @Test
    void implementation() {
        StyleRule rule = StyleRule.create(new Implementation());
        assert rule.selector.matches("\\.AT\\d+");
    }

    /**
     * @version 2018/08/31 9:33:24
     */
    private static class Implementation implements Style {

        /**
         * {@inheritDoc}
         */
        @Override
        public void style() {
        }
    }

    @Test
    void inlineLambda() {
        StyleRule rule = StyleRule.create(() -> {
        });
        assert rule.selector.matches("\\.AT\\d+");
    }

    static Style fieldLambda = () -> {
    };

    @Test
    void fieldLambda() {
        StyleRule rule = StyleRule.create(fieldLambda);
        assert rule.selector.matches(".NamingTest≫fieldLambda");
    }

    @Test
    void fieldLambdaInMemberCLass() {
        StyleRule rule = StyleRule.create(Member.style);
        assert rule.selector.equals(".NamingTest≫Member≫style");
    }

    /**
     * @version 2018/08/31 11:18:38
     */
    private static class Member extends StyleDSL {

        static Style style = () -> {
        };
    }
}
