/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import kiss.Managed;
import kiss.Singleton;
import stylist.property.Animation;
import stylist.property.Appearance;
import stylist.property.Background;
import stylist.property.Borders;
import stylist.property.BoxLength;
import stylist.property.Content;
import stylist.property.Cursor;
import stylist.property.Display;
import stylist.property.Fill;
import stylist.property.FlexItem;
import stylist.property.Font;
import stylist.property.Line;
import stylist.property.ListStyle;
import stylist.property.Outline;
import stylist.property.Overflows;
import stylist.property.PointerEvents;
import stylist.property.Position;
import stylist.property.Stroke;
import stylist.property.Text;
import stylist.property.Transform;
import stylist.property.Visibility;
import stylist.value.Unit;

@Managed(Singleton.class)
public interface StyleDSL extends StyleDeclarable {

    /** A set of css functions. */
    public static final CSSFunctions $ = new CSSFunctions();

    /**
     * <p>
     * This unit represents the font-size of the root element (e.g. the font-size of the <html>
     * element). When used on the font-size on this root element, it represents its initial value.
     * </p>
     */
    public static final Unit rem = Unit.rem;

    /**
     * <p>
     * The 'em' unit is equal to the computed value of the 'font-size' property of the element on
     * which it is used. The exception is when 'em' occurs in the value of the 'font-size' property
     * itself, in which case it refers to the font size of the parent element. It may be used for
     * vertical or horizontal measurement. (This unit is also sometimes called the quad-width in
     * typographic texts.)
     * </p>
     */
    public static final Unit em = Unit.em;

    /**
     * <p>
     * The 'ex' unit is defined by the element's first available font. The 'x-height' is so called
     * because it is often equal to the height of the lowercase "x". However, an 'ex' is defined
     * even for fonts that don't contain an "x".
     * </p>
     */
    public static final Unit ex = Unit.ex;

    /**
     * <p>
     * The x-height of a font can be found in different ways. Some fonts contain reliable metrics
     * for the x-height. If reliable font metrics are not available, UAs may determine the x-height
     * from the height of a lowercase glyph. One possible heuristics is to look at how far the glyph
     * for the lowercase "o" extends below the baseline, and subtract that value from the top of its
     * bounding box. In the cases where it is impossible or impractical to determine the x-height, a
     * value of 0.5em should be used.
     * </p>
     */
    public static final Unit px = Unit.px;

    /**
     * <p>
     * The x-height of a font can be found in different ways. Some fonts contain reliable metrics
     * for the x-height. If reliable font metrics are not available, UAs may determine the x-height
     * from the height of a lowercase glyph. One possible heuristics is to look at how far the glyph
     * for the lowercase "o" extends below the baseline, and subtract that value from the top of its
     * bounding box. In the cases where it is impossible or impractical to determine the x-height, a
     * value of 0.5em should be used.
     * </p>
     */
    public static final Unit in = Unit.in;

    /**
     * 1/100th of the width of the viewport.
     */
    public static final Unit vh = Unit.vh;

    /**
     * 1/100th of the width of the viewport.
     */
    public static final Unit vw = Unit.vw;

    /**
     * 1/100th of the minimum value between the height and the width of the viewport.
     */
    public static final Unit vmin = Unit.vmin;

    /**
     * 1/100th of the maximum value between the height and the width of the viewport.
     */
    public static final Unit vmax = Unit.vmax;

    /**
     * deg which represents an angle in degrees. One full circle is 360deg. E.g. 0deg, 90deg,
     * 360deg.
     */
    public static final Unit deg = Unit.deg;

    /**
     * <p>
     * The x-height of a font can be found in different ways. Some fonts contain reliable metrics
     * for the x-height. If reliable font metrics are not available, UAs may determine the x-height
     * from the height of a lowercase glyph. One possible heuristics is to look at how far the glyph
     * for the lowercase "o" extends below the baseline, and subtract that value from the top of its
     * bounding box. In the cases where it is impossible or impractical to determine the x-height, a
     * value of 0.5em should be used.
     * </p>
     */
    public static final Unit s = Unit.s;

    /**
     * <p>
     * The x-height of a font can be found in different ways. Some fonts contain reliable metrics
     * for the x-height. If reliable font metrics are not available, UAs may determine the x-height
     * from the height of a lowercase glyph. One possible heuristics is to look at how far the glyph
     * for the lowercase "o" extends below the baseline, and subtract that value from the top of its
     * bounding box. In the cases where it is impossible or impractical to determine the x-height, a
     * value of 0.5em should be used.
     * </p>
     */
    public static final Unit ms = Unit.ms;

    /**
     * <p>
     * The format of a percentage value (denoted by <percentage> in this specification) is a
     * <number> immediately followed by '%'.
     * </p>
     * <p>
     * Percentage values are always relative to another value, for example a length. Each property
     * that allows percentages also defines the value to which the percentage refers. The value may
     * be that of another property for the same element, a property for an ancestor element, or a
     * value of the formatting context (e.g., the width of a containing block). When a percentage
     * value is set for a property of the root element and the percentage is defined as referring to
     * the inherited value of some property, the resultant value is the percentage times the initial
     * value of that property.
     * </p>
     */
    public static final Unit percent = Unit.percent;

    /**
     * <p>
     * The <flex> data type is specified as a <number> followed by the unit fr. The fr unit
     * represents a fraction of the leftover space in the grid container. As with all CSS
     * dimensions, there is no space between the unit and the number.
     * </p>
     */
    public static final Unit fr = Unit.fr;

    public static final Animation animation = new Animation();

    /**
     * <p>
     * The all CSS shorthand property sets all of an element's properties (other than unicode-bidi
     * and direction) to their initial or inherited values, or to the values specified in another
     * stylesheet origin.
     * </p>
     */
    public static final PropertyDefinition all = new PropertyDefinition("all");

    /**
     * <p>
     * The appearance CSS property is used in Gecko (Firefox) to display an element using a
     * platform-native styling based on the operating system's theme.
     * </p>
     */
    public static final Appearance appearance = new Appearance();

    /**
     * <p>
     * The cursor CSS property specifies the mouse cursor displayed when the mouse pointer is over
     * an element.
     * </p>
     */
    public static final Cursor cursor = new Cursor();

    /**
     * <p>
     * The background CSS property is a shorthand for setting the individual background values in a
     * single place in the style sheet. background can be used to set the values for one or more of:
     * background-color, background-image, background-position, background-repeat, background-size,
     * </p>
     */
    public static final Background background = new Background();

    /**
     * <p>
     * The border CSS property is a shorthand property for setting the individual border property
     * values in a single place in the style sheet. border can be used to set the values for one or
     * more of: border-width, border-style, border-color.
     * </p>
     */
    public static final Borders border = new Borders();

    /**
     * <p>
     * The content CSS property is used with the ::before and ::after pseudo-elements to generate
     * content in an element. Objects inserting using the content property are anonymous replaced
     * elements.
     * </p>
     */
    public static final Content content = new Content();

    /**
     * <p>
     * The display CSS property specifies the type of rendering box used for an element. In HTML,
     * default display property values are taken from behaviors described in the HTML specifications
     * or from the browser/user default stylesheet. The default value in XML is inline.
     * </p>
     * <p>
     * In addition to the many different display box types, the value none lets you turn off the
     * display of an element; when you use none, all child elements also have their display turned
     * off. The document is rendered as though the element doesn't exist in the document tree.
     * </p>
     */
    public static final Display display = new Display();

    /** The SVG property. */
    public static final Fill fill = new Fill();

    /**
     * <p>
     * The flex CSS property is a shorthand property specifying the ability of a flex item to alter
     * its dimensions to fill available space. Flex items can be stretched to use available space
     * proportional to their flex grow factor or their flex shrink factor to prevent overflow.
     * </p>
     */
    public static final FlexItem flexItem = new FlexItem();

    /**
     * <p>
     * The font CSS property is either a shorthand property for setting font-style, font-variant,
     * font-weight, font-size, line-height and font-family, or a way to set the element's font to a
     * system font, using specific keywords.
     * </p>
     */
    public static final Font font = new Font();

    /**
     * <p>
     * On inline elements, the line-height CSS property specifies the height that is used in the
     * calculation of the line box height. On block level elements, line-height specifies the
     * minimal height of line boxes within the element.
     * </p>
     */
    public static final Line line = new Line();

    /**
     * <p>
     * The list-style CSS property is a shorthand property for setting list-style-type,
     * list-style-image and list-style-position.
     * </p>
     */
    public static final ListStyle listStyle = new ListStyle();

    /**
     * <p>
     * The margin CSS property sets the margin for all four sides. It is a shorthand to avoid
     * setting each side separately with the other margin properties: margin-top, margin-right,
     * margin-bottom and margin-left. Negative value are also allowed.
     * </p>
     * <p>
     * One single value applies to all four sides.
     * </p>
     */
    public static final BoxLength margin = new BoxLength("margin");

    /**
     * <p>
     * The CSS outline property is a shorthand property for setting one or more of the individual
     * outline properties outline-style, outline-width and outline-color in a single rule. In most
     * cases the use of this shortcut is preferable and more convenient.
     * </p>
     * <p>
     * Outlines differ from borders in the following ways:
     * </p>
     * <ul>
     * <li>Outlines do not take up space, they are drawn above the content.</li>
     * <li>Outlines may be non-rectangular. They are rectangular in Gecko/Firefox. But e.g. Opera
     * draws a non-rectangular shape around a construct like this:</li>
     * </ul>
     */
    public static final Outline outline = new Outline();

    /**
     * <p>
     * The overflow CSS property specifies whether to clip content, render scroll bars or display
     * overflow content of a block-level element.
     * </p>
     * <p>
     * Using the overflow property with a value different than visible, its default, will create a
     * new block formatting context. This is technically necessary as if a float would intersect
     * with the scrolling element it would force to rewrap the content of the scrollable element
     * around intruding floats. The rewrap would happen after each scroll step and would be lead to
     * a far too slow scrolling experience. Note that, by programmatically setting scrollTop to the
     * relevant HTML element, even when overflow has the hidden value an element may need to scroll.
     * </p>
     */
    public static final Overflows overflow = new Overflows();

    /**
     * <p>
     * The padding CSS property sets the required padding space on all sides of an element. The
     * padding area is the space between the content of the element and its border. Negative values
     * are not allowed.
     * </p>
     * <p>
     * The padding property is a shorthand to avoid setting each side separately (padding-top,
     * padding-right, padding-bottom, padding-left).
     * </p>
     */
    public static final BoxLength padding = new BoxLength("padding");

    /**
     * <p>
     * The CSS property pointer-events allows authors to control under what circumstances (if any) a
     * particular graphic element can become the target of mouse events. When this property is
     * unspecified, the same characteristics of the visiblePainted value apply to SVG content.
     * </p>
     */
    public static final PointerEvents pointerEvents = new PointerEvents();

    /**
     * <p>
     * The position CSS property chooses alternative rules for positioning elements, designed to be
     * useful for scripted animation effects.
     * </p>
     */
    public static final Position position = new Position();

    /**
     * <p>
     * The scroll-margin property is a shorthand property which sets all of the scroll-margin
     * longhands, assigning values much like the margin property does for the margin-* longhands
     * </p>
     * <p>
     * In these examples, you can see the effect of scroll-margin by scrolling the output box to a
     * point partway between two of the "pages" of the example's content. The value specified for
     * scroll-margin determines how much of the page that's primarily outside the snapport should
     * remain visible.
     * </p>
     * <p>
     * Thus, the scroll-margin values represent outsets defining the scroll snap area that is used
     * for snapping this box to the snapport. The scroll snap area is determined by taking the
     * transformed border box, finding its rectangular bounding box (axis-aligned in the scroll
     * container’s coordinate space), then adding the specified outsets.
     * </p>
     */
    public static final BoxLength scrollMargin = new BoxLength("scroll-margin");

    /**
     * <p>
     * The scroll-padding property is a shorthand property that sets all of the scroll-padding-*
     * longhands. It assigns values much like the padding property does for the padding-* longhands.
     * </p>
     * <p>
     * The scroll-padding-* properties define offsets for the optimal viewing region of the
     * scrollport: the region used as the target region for placing things in view of the user. This
     * allows the author to exclude regions of the scrollport that are obscured by other content
     * (such as fixed-positioned toolbars or sidebars), or simply to put more breathing room between
     * a targeted element and the edges of the scrollport.
     * </p>
     * 
     */
    public static final BoxLength scrollPadding = new BoxLength("scroll-padding");

    /** The SVG property. */
    public static final Stroke stroke = new Stroke();

    /** The text related style. */
    public static final Text text = new Text();

    /**
     * <p>
     * The CSS transform property lets you modify the coordinate space of the CSS visual formatting
     * model. Using it, elements can be translated, rotated, scaled, and skewed according to the
     * values set.
     * </p>
     * <p>
     * If the property has a value different than none, a stacking context will be created. In that
     * case the object will act as a containing block for position: fixed elements that it contains.
     * </p>
     */
    public static final Transform transform = new Transform();

    /**
     * <p>
     * The visibility CSS property has two purposes:
     * </p>
     */
    public static final Visibility visibility = new Visibility();
}
