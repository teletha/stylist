/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import stylist.CSSValue;
import stylist.PropertyDefinition;
import stylist.value.Color;

/**
 * @version 2018/08/30 18:23:58
 */
public abstract class Colorable<T extends Colorable> extends PropertyDefinition<T> {

    /**
     * 
     */
    protected Colorable() {
    }

    /**
     * 
     */
    protected Colorable(String name) {
        super(name);
    }

    /**
     * @param name
     * @param context
     */
    protected Colorable(String name, T context) {
        super(name, context);
    }

    /**
     * <p>
     * The format of an RGB value in the functional notation is 'rgb(' followed by a comma-separated
     * list of three numerical values (either three integer values or three percentage values)
     * followed by ')'. The integer value 255 corresponds to 100%, and to F or FF in the hexadecimal
     * notation: rgb(255,255,255) = rgb(100%,100%,100%) = #FFF. White space characters are allowed
     * around the numerical values.
     * </p>
     * 
     * @param color
     * @return Chainable API.
     */
    public abstract T color(CSSValue color);

    /**
     * <p>
     * The format of an RGB value in the functional notation is 'rgb(' followed by a comma-separated
     * list of three numerical values (either three integer values or three percentage values)
     * followed by ')'. The integer value 255 corresponds to 100%, and to F or FF in the hexadecimal
     * notation: rgb(255,255,255) = rgb(100%,100%,100%) = #FFF. White space characters are allowed
     * around the numerical values.
     * </p>
     * 
     * @param colorCode
     * @return Chainable API.
     */
    public T color(String colorCode) {
        try {
            return color(Color.of(colorCode));
        } catch (IllegalArgumentException e) {
            return color(CSSValue.of(colorCode));
        }
    }

    /**
     * <p>
     * The format of an RGB value in the functional notation is 'rgb(' followed by a comma-separated
     * list of three numerical values (either three integer values or three percentage values)
     * followed by ')'. The integer value 255 corresponds to 100%, and to F or FF in the hexadecimal
     * notation: rgb(255,255,255) = rgb(100%,100%,100%) = #FFF. White space characters are allowed
     * around the numerical values.
     * </p>
     * 
     * @param red
     * @param green
     * @param blue
     * @return Chainable API.
     */
    public T color(int red, int green, int blue) {
        return color(Color.rgb(red, green, blue));
    }

    /**
     * <p>
     * The format of an RGB value in the functional notation is 'rgb(' followed by a comma-separated
     * list of three numerical values (either three integer values or three percentage values)
     * followed by ')'. The integer value 255 corresponds to 100%, and to F or FF in the hexadecimal
     * notation: rgb(255,255,255) = rgb(100%,100%,100%) = #FFF. White space characters are allowed
     * around the numerical values.
     * </p>
     * 
     * @param red
     * @param green
     * @param blue
     * @return Chainable API.
     */
    public T color(int red, int green, int blue, double alpha) {
        return color(Color.rgb(red, green, blue, alpha));
    }
}
