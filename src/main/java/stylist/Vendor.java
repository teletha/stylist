/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

/**
 * @version 2018/08/30 17:54:48
 */
public enum Vendor {

    /** Internet Explorer */
    IE("-ms-"),

    /** Mozilla */
    Mozilla("-moz-"),

    /** Safari */
    Safari("-webkit-"),

    /** Webkit */
    Webkit("-webkit-"),

    /** JavaFX */
    JavaFX("-fx-"),

    /** Standard */
    Standard("");

    /** The current vendor in this environment. */
    public static Vendor Current = Standard;

    /** The current vendor state in this environment. */
    public static final boolean isMozilla() {
        return Current == Mozilla;
    }

    /** The current vendor state in this environment. */
    public static final boolean isWebkit() {
        return Current == Webkit;
    }

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
