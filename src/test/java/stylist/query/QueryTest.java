/*
 * Copyright (C) 2025 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.query;

import org.junit.jupiter.api.Test;

import stylist.Query;
import stylist.StyleTester;

public class QueryTest extends StyleTester {

    private boolean check(Query query, String expected) {
        assert query.toString().equals(expected);
        return true;
    }

    @Test
    void all() {
        assert check(Query.all(), "@media all");

    }

    @Test
    void print() {
        assert check(Query.print(), "@media print");
    }

    @Test
    void screen() {
        assert check(Query.screen(), "@media screen");
    }

    @Test
    void speech() {
        assert check(Query.speech(), "@media speech");
    }

    @Test
    void container() {
        assert check(Query.container(), "@container");
    }

    @Test
    void containerNamed() {
        assert check(Query.container("test"), "@container test");
    }

    @Test
    void width() {
        assert check(Query.container().width(100, 200, px), "@container (100px <= width < 200px)");
    }

    @Test
    void minWidth() {
        assert check(Query.container().width(100, px), "@container (100px <= width)");
    }

    @Test
    void height() {
        assert check(Query.container().height(100, 200, px), "@container (100px <= height < 200px)");
    }

    @Test
    void minHeight() {
        assert check(Query.container().height(100, px), "@container (100px <= height)");
    }
}
