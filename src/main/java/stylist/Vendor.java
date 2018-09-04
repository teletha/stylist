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
 * @version 2018/09/04 13:48:13
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

    /** JavaFX */
    JavaFX("-fx-"),

    /** Standard */
    Standard("");

    /** The default {@link Vendor} in the current environment. */
    public static final Vendor Now = valueOf(System.getProperty(Vendor.class.getName(), Standard.name()));

    /** The current vendor state in this environment. */
    public static final boolean isMozilla() {
        return Now == Mozilla;
    }

    /** The current vendor state in this environment. */
    public static final boolean isWebkit() {
        return Now == Webkit;
    }

    /** The current vendor state in this environment. */
    public static final boolean isFX() {
        return Now == JavaFX;
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
