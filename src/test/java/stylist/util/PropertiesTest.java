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

import org.junit.jupiter.api.Test;

/**
 * @version 2018/09/01 21:32:13
 */
class PropertiesTest {

    @Test
    void compact() {
        Properties properties = new Properties();
        properties.add("1", "1");
        properties.add("2", "2");

        properties.compactTo("compact", "default", "1", "2");
        assert properties.get("compact").is("1 2");
    }

    @Test
    void compactWithNone() {
        Properties properties = new Properties();
        properties.add("1", "1");
        properties.add("2", "2");

        properties.compactTo("compact", "default", "1", "2", "3");
        assert properties.get("compact").is("1 2 default");
    }

    @Test
    void mappingKey() {
        Properties properties = new Properties();
        properties.add("a", "a");
        properties.add("b", "b");

        properties.keys(String::toUpperCase);
        assert properties.size() == 2;
        assert properties.get("A").is("a");
        assert properties.get("B").is("b");
    }
}
