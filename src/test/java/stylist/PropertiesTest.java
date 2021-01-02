/*
 * Copyright (C) 2021 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import org.junit.jupiter.api.Test;

import stylist.CSSValue;
import stylist.Properties;

/**
 * @version 2018/09/01 21:32:13
 */
class PropertiesTest {

    @Test
    void compact() {
        Properties properties = new Properties();
        properties.set("1", "1");
        properties.set("2", "2");

        properties.compactTo("compact", "default", "1", "2");
        assert properties.get("compact").get().match("1 2");
    }

    @Test
    void compactWithNone() {
        Properties properties = new Properties();
        properties.set("1", "1");
        properties.set("2", "2");

        properties.compactTo("compact", "default", "1", "2", "3");
        assert properties.get("compact").get().match("1 2 default");
    }

    @Test
    void compactWithAllNone() {
        Properties properties = new Properties();

        properties.compactTo("compact", "default", "1", "2", "3");
        assert properties.get("compact").isPresent() == false;
    }

    @Test
    void mappingKeys() {
        Properties properties = new Properties();
        properties.set("a", "a");
        properties.set("b", "b");

        properties.rename(name -> CSSValue.of(name.toString().toUpperCase()));
        assert properties.size() == 2;
        assert properties.get("A").get().match("a");
        assert properties.get("B").get().match("b");
    }

    @Test
    void mappingValue() {
        Properties properties = new Properties();
        properties.set("a", "a");
        properties.set("b", "b");

        properties.revalue("a", e -> CSSValue.of(e.toString().toUpperCase()));
        assert properties.size() == 2;
        assert properties.get("a").get().match("A");
        assert properties.get("b").get().match("b");

        // null
        properties.revalue("c", e -> CSSValue.of(e.toString().toUpperCase()));
        assert properties.size() == 2;
    }
}