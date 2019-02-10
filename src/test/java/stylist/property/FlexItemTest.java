/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;

/**
 * @version 2018/08/30 18:37:55
 */
public class FlexItemTest extends StyleTester {

    @Test
    public void grow() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            flexItem.grow(2);
        });

        assert style.property("flex-grow", "2");
        assert style.property("-webkit-flex-grow", "2");
    }

    @Test
    public void shrink() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            flexItem.shrink(2);
        });

        assert style.property("flex-shrink", "2");
        assert style.property("-webkit-flex-shrink", "2");
    }

    @Test
    public void order() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            flexItem.order(2);
        });

        assert style.property("order", "2");
        assert style.property("-webkit-order", "2");
    }
}
