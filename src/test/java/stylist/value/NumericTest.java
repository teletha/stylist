/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

import static stylist.value.Unit.*;

import org.junit.jupiter.api.Test;

/**
 * @version 2018/08/30 18:35:06
 */
public class NumericTest {

    @Test
    public void degUnderflow() throws Exception {
        Numeric numeric = new Numeric(-100, deg);

        assert numeric.size == 260;
    }

    @Test
    public void degOverflow() throws Exception {
        Numeric numeric = new Numeric(400, deg);

        assert numeric.size == 40;
    }
}
