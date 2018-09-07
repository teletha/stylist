/*
 * Copyright (C) 2017 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 2018/09/08 8:14:41
 */
public class GroupStyle<Key> {

    /** The member manager. */
    private final Map<Key, Style> member = new HashMap();

    public Style of(Key key) {
        if (key == null) {
            return () -> {
            };
        }
        return member.com
    }

    /**
     * Enumerate all members.
     * 
     * @return
     */
    public Style[] member() {
        return null;
    }
}
