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

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MultipleStyleTest {

    @Test
    void multi() {
        Style one = Style.named(".one", () -> {
        });
        Style two = Style.named(".two", () -> {
        });

        Style multi = one.with(two);
        assert Arrays.equals(multi.className(), new String[] {"one", "two"});
    }
}