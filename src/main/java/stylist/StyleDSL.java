/*
 * Copyright (C) 2024 The STYLIST Development Team
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
import stylist.property.Container;
import stylist.property.Content;
import stylist.property.Cursor;
import stylist.property.Display;
import stylist.property.Fill;
import stylist.property.FlexItem;
import stylist.property.Font;
import stylist.property.ListStyle;
import stylist.property.Outline;
import stylist.property.Overflows;
import stylist.property.PointerEvents;
import stylist.property.Position;
import stylist.property.Scroll;
import stylist.property.Stroke;
import stylist.property.Text;
import stylist.property.Transform;
import stylist.property.Transition;
import stylist.value.Unit;

@Managed(Singleton.class)
public interface StyleDSL extends StyleDeclarable, Unit {

    /** A set of css functions. */
    public static final FunctionDSL $ = new FunctionDSL();

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
     * An element can be established as a query container for container size queries using the
     * container-type CSS property. container-type is used to define the type of size containment
     * used in a container query.
     * <p>
     * Size containment turns off the ability of an element to get size information from its
     * contents, which is important for container queries to avoid infinite loops. If this were not
     * the case, a CSS rule inside a container query could change the content size, which in turn
     * could make the query evaluate to false and change the parent element's size, which in turn
     * could change the content size and flip the query back to true, and so on.
     * <p>
     * The container size has to be set explicitly or by context — for example, block elements, flex
     * containers, and grid containers stretching to the full width of their parent. If an explicit
     * or contextual size is not available, elements with size containment will collapse.
     */
    public static final Container container = new Container();

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
     * The scroll-behavior CSS property sets the behavior for a scrolling box when scrolling is
     * triggered by the navigation or CSSOM scrolling APIs.
     * </p>
     */
    public static final Scroll scroll = new Scroll();

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
     * 
     */
    public static final Transition transition = new Transition();
}