/*
 * Copyright (C) 2020 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import stylist.property.Transition;
import stylist.value.Color;
import stylist.value.LinearGradient;
import stylist.value.RadialGradient;
import stylist.value.Shadow;

/**
 * @version 2018/09/07 13:50:11
 */
public final class CSSFunctions extends SelectorDSL {

    /**
     * Hide constructor.
     */
    CSSFunctions() {
        super(null);
    }

    /**
     * <p>
     * Create Color without alpha channel.
     * </p>
     * 
     * @param red A red component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param green A green component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param blue A blue component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @return A new color.
     */
    public Color rgb(int red, int green, int blue) {
        return Color.rgb(red, green, blue);
    }

    /**
     * <p>
     * Create Color with alpha channel.
     * </p>
     * 
     * @param red A red component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param green A green component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @param blue A blue component in the range 0-255. If the specified value is out of range, it
     *            will be round up to 0 or 255.
     * @return A new color.
     */
    public Color rgba(int red, int green, int blue, double alpha) {
        return Color.rgb(red, green, blue, alpha);
    }

    /**
     * <p>
     * Create Color without alpha channel.
     * </p>
     * 
     * @param hue The attribute of a visual sensation according to which an area appears to be
     *            similar to one of the perceived colors: red, yellow, green, and blue, or to a
     *            combination of two of them .
     * @param saturation The colorfulness of a stimulus relative to its own brightness.
     * @param lightness The brightness relative to the brightness of a similarly illuminated white.
     * @return A new color.
     */
    public Color hsl(int hue, int saturation, int lightness) {
        return hsla(hue, saturation, lightness, 1);
    }

    /**
     * <p>
     * Create Color without alpha channel.
     * </p>
     * 
     * @param hue The attribute of a visual sensation according to which an area appears to be
     *            similar to one of the perceived colors: red, yellow, green, and blue, or to a
     *            combination of two of them .
     * @param saturation The colorfulness of a stimulus relative to its own brightness.
     * @param lightness The brightness relative to the brightness of a similarly illuminated white.
     * @param alpha The transparency.
     * @return A new color.
     */
    public Color hsla(int hue, int saturation, int lightness, double alpha) {
        return Color.hsl(hue, saturation, lightness, alpha);
    }

    /**
     * <p>
     * The CSS linear-gradient() function creates an <image> which represents a linear gradient of
     * colors. The result of this function is an object of the CSS <gradient> data type. Like any
     * other gradient, a CSS linear gradient is not a CSS <color> but an image with no intrinsic
     * dimensions; that is, it has no natural or preferred size, nor ratio. Its concrete size will
     * match the one of the element it applies to.
     * </p>
     * 
     * @return A new linear gradient image.
     */
    public LinearGradient linear() {
        return new LinearGradient();
    }

    /**
     * <p>
     * The CSS linear-gradient() function creates an <image> which represents a linear gradient of
     * colors. The result of this function is an object of the CSS <gradient> data type. Like any
     * other gradient, a CSS linear gradient is not a CSS <color> but an image with no intrinsic
     * dimensions; that is, it has no natural or preferred size, nor ratio. Its concrete size will
     * match the one of the element it applies to.
     * </p>
     * 
     * @return A new linear gradient image.
     */
    public LinearGradient linear(Color start, Color end) {
        return new LinearGradient().color(start, end);
    }

    /**
     * <p>
     * The CSS radial-gradient() function creates an <image> which represents a gradient of colors
     * radiating from an origin, the center of the gradient. The result of this function is an
     * object of the CSS <gradient> data type.
     * </p>
     * 
     * @return A new linear gradient image.
     */
    public RadialGradient radial() {
        return new RadialGradient();
    }

    /**
     * <p>
     * The CSS radial-gradient() function creates an <image> which represents a gradient of colors
     * radiating from an origin, the center of the gradient. The result of this function is an
     * object of the CSS <gradient> data type.
     * </p>
     * 
     * @return A new linear gradient image.
     */
    public RadialGradient radial(Color start, Color end) {
        return new RadialGradient().color(start, end);
    }

    /**
     * <p>
     * The CSS shadow function creates an generic shadow representation.
     * </p>
     * 
     * @return A new shadow.
     */
    public Shadow shadow() {
        return new Shadow();
    }

    /**
     * <p>
     * Create transitable style rule.
     * </p>
     * 
     * @return
     */
    public Transition transit() {
        return new Transition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    SelectorDSL basic(String selector) {
        return SelectorDSL.create(null).basic(selector);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    SelectorDSL combine(String type, boolean forward) {
        return SelectorDSL.create(null).combine(type, forward);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    SelectorDSL pseudo(boolean isElement, Object name) {
        return SelectorDSL.create(null).pseudo(isElement, name);
    }
}