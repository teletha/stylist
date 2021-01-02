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

/**
 * @version 2018/09/10 11:28:42
 */
public enum Vendor {

    /** Internet Explorer */
    MS("-ms-"),

    /** Mozilla */
    Mozilla("-moz-"),

    /** Safari */
    Safari("-webkit-"),

    /** Webkit */
    Webkit("-webkit-"),

    /** Standard */
    Standard("");

    /** The prefix. */
    private final String prefix;

    /**
     * @param prefix
     */
    private Vendor(String prefix) {
        this.prefix = prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return prefix;
    }
}