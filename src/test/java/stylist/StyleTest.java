/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import java.util.Collection;

import org.junit.jupiter.api.Test;

/**
 * @version 2018/09/08 18:20:25
 */
class StyleTest extends StyleTester {

    @Test
    void group() {
        Style style = () -> {
        };

        Collection<Style> group = style.group();
        assert group.size() == 1;
        assert group.contains(style);
    }
}
