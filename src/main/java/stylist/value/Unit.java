/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.value;

public interface Unit {
    /**
     * This unit represents the calculated font-size of the element. If used on the font-size
     * property itself, it represents the inherited font-size of the element.
     */
    Unit em = new UnitDef("em");

    /**
     * This unit represents the font-size of the root element (e.g. the font-size of the <html>
     * element). When used on the font-size on this root element, it represents its initial value.
     */
    Unit rem = new UnitDef("rem");

    /**
     * This unit represents the x-height of the element's font. On fonts with the 'x' letter, this
     * is generally the height of lowercase letters in the font; 1ex ≈ 0.5em in many fonts.
     */
    Unit ex = new UnitDef("ex");

    /**
     * x-height of the root element's font.
     */
    Unit rex = new UnitDef("rex");

    /**
     * This unit represents the width, or more precisely the advance measure, of the glyph '0'
     * (zero, the Unicode character U+0030) in the element's font.
     */
    Unit ch = new UnitDef("ch");

    /**
     * Average character advance of a narrow glyph in the root element's font, as represented by the
     * "0" (ZERO, U+0030) glyph.
     */
    Unit rch = new UnitDef("rch");

    /**
     * Cap height (the nominal height of capital letters) of the element's font.
     */
    Unit cap = new UnitDef("cap");

    /**
     * Cap height (the nominal height of capital letters) of the root element's font.
     */
    Unit rcap = new UnitDef("rcap");

    /**
     * Average character advance of a full-width glyph in the element's font, as represented by the
     * "水" (CJK water ideograph, U+6C34) glyph.
     */
    Unit ic = new UnitDef("ic");

    /**
     * Average character advance of a full-width glyph in the root element's font, as represented by
     * the "水" (CJK water ideograph, U+6C34) glyph.
     */
    Unit ric = new UnitDef("ric");

    /**
     * Line height of the element.
     */
    Unit lh = new UnitDef("lh");

    /**
     * Line height of the root element.
     */
    Unit rlh = new UnitDef("rlh");

    /**
     * 1/100th of the height of the viewport.
     */
    Unit vh = new UnitDef("vh");

    /**
     * 1/100th of the width of the viewport.
     */
    Unit vw = new UnitDef("vw");

    /**
     * 1% of viewport's size in the root element's block axis.
     */
    Unit vb = new UnitDef("vb");

    /**
     * 1% of viewport's size in the root element's inline axis.
     */
    Unit vi = new UnitDef("vi");

    /**
     * 1/100th of the minimum value between the height and the width of the viewport.
     */
    Unit vmin = new UnitDef("vmin");

    /**
     * 1/100th of the maximum value between the height and the width of the viewport.
     */
    Unit vmax = new UnitDef("vmax");

    /**
     * 1% of the dynamic viewport's height.
     */
    Unit dvh = new UnitDef("dvh");

    /**
     * 1% of the dynamic viewport's width.
     */
    Unit dvw = new UnitDef("dvw");

    /**
     * 1% of the large viewport's height.
     */
    Unit lvh = new UnitDef("lvh");

    /**
     * 1% of the large viewport's width.
     */
    Unit lvw = new UnitDef("lvw");

    /**
     * 1% of the small viewport's height.
     */
    Unit svh = new UnitDef("svh");

    /**
     * 1% of the small viewport's width.
     */
    Unit svw = new UnitDef("svw");

    /**
     * Container query length units specify a length relative to the dimensions of a query
     * container. 1% of a query container's block size
     */
    Unit cqb = new UnitDef("cqb");

    /**
     * Container query length units specify a length relative to the dimensions of a query
     * container. 1% of a query container's height
     */
    Unit cqh = new UnitDef("cqh");

    /**
     * Container query length units specify a length relative to the dimensions of a query
     * container. 1% of a query container's inline size
     */
    Unit cqi = new UnitDef("cqi");

    /**
     * Container query length units specify a length relative to the dimensions of a query
     * container. The larger value of cqi or cqb
     */
    Unit cqmax = new UnitDef("cqmax");

    /**
     * Container query length units specify a length relative to the dimensions of a query
     * container. The smaller value of cqi or cqb
     */
    Unit cqmin = new UnitDef("cqmin");

    /**
     * Container query length units specify a length relative to the dimensions of a query
     * container. 1% of a query container's width
     */
    Unit cqw = new UnitDef("cqw");

    /**
     * Relative to the viewing device. For screen display, typically one device pixel (dot) of the
     * display. For printers and very high resolution screens one CSS pixel implies multiple device
     * pixels, so that the number of pixel per inch stays around 96.
     */
    Unit px = new UnitDef("px");

    /**
     * One millimeter.
     */
    Unit mm = new UnitDef("mm");

    /**
     * One centimeter (10 millimeters).
     */
    Unit cm = new UnitDef("cm");

    /**
     * One inch (2.54 centimeters).
     */
    Unit in = new UnitDef("in");

    /**
     * One point (which is 1/72 of an inch).
     */
    Unit pt = new UnitDef("pt");

    /**
     * One pica (which is 12 points).
     */
    Unit pc = new UnitDef("pc");

    /**
     * deg which represents an angle in degrees. One full circle is 360deg. E.g. 0deg, 90deg,
     * 360deg.
     */
    Unit deg = new UnitDef("deg");

    /**
     * grad which represents an angle in gradians. One full circle is 400grad. E.g. 0grad, 100grad,
     * 400grad.
     */
    Unit grad = new UnitDef("grad");

    /**
     * rad which represents an angle in radians. One full circle is 2π radians which approximates to
     * 6.2832rad. 1rad is 180/π degrees. E.g. 0rad, 1.0708rad, 6.2832rad.
     */
    Unit ead = new UnitDef("rad");

    /**
     * There is 1 turn in a full circle.
     */
    Unit turn = new UnitDef("turn");

    /**
     * The <percentage> CSS data types represent a percentage value. Many CSS properties can take
     * percentage values, often to define sizes in terms of parent objects. Percentages are formed
     * by a <number> immediately followed by the percentage sign %. Like for all unit in CSS, there
     * is no space between the '%' and the number.
     */
    Unit percent = new UnitDef("%");

    /**
     * a time in seconds. E.g. 0s, 1.5s, -60s.
     */
    Unit s = new UnitDef("s");

    /**
     * a time in milliseconds. E.g. 0ms, 1500ms, -60000ms.
     */
    Unit ms = new UnitDef("ms");

    /**
     * The <flex> data type is specified as a <number> followed by the unit fr. The fr unit
     * represents a fraction of the leftover space in the grid container. As with all CSS
     * dimensions, there is no space between the unit and the number.
     */
    Unit fr = new UnitDef("fr");
}