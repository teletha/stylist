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

import stylist.SelectorDSL.Attribute;
import stylist.property.Transition;
import stylist.value.Color;
import stylist.value.LinearGradient;
import stylist.value.RadialGradient;
import stylist.value.Shadow;

/**
 * @version 2018/09/03 19:39:55
 */
public final class Functional {

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
        return Color.rgba(red, green, blue, alpha);
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
        return new Color(hue, saturation, lightness, alpha);
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

    // ===============================================================
    // Basic Selectors
    // ===============================================================

    /**
     * <p>
     * Attribute selectors select an element using the presence of a given attribute or attribute
     * value.
     * </p>
     * 
     * @param name An attribute name.
     * @return Chainable API.
     */
    public Attribute attr(String name) {
        return new Attribute(SelectorDSL.create(null), name);
    }

    /**
     * <p>
     * Attribute selectors select an element using the presence of a given attribute or attribute
     * value.
     * </p>
     * <p>
     * Represents an element with an attribute name.
     * </p>
     * <p>
     * This is shorthand method of {@link Attribute#exist(Style)}
     * </p>
     * 
     * @param name An attribute name.
     * @param A sub style rule.
     */
    public void attr(String name, Style sub) {
        attr(name).exist(sub);
    }

    /**
     * <p>
     * Attribute selectors select an element using the presence of a given attribute or attribute
     * value.
     * </p>
     * <p>
     * Represents an element with an attribute name.
     * </p>
     * <p>
     * This is shorthand method of {@link Attribute#is(Style)}
     * </p>
     * 
     * @param name An attribute name.
     * @param value An attribute value.
     * @param A sub style rule.
     */
    public void attr(String name, String value, Style sub) {
        attr(name).is(value, sub);
    }

    /**
     * <p>
     * In an HTML document, CSS class selectors match an element based on the contents of the
     * element's class attribute.
     * </p>
     * 
     * @param location A class location.
     * @return Chainable API.
     */
    public SelectorDSL with(Location location) {
        return basic("." + location.name());
    }

    /**
     * <p>
     * In an HTML document, CSS class selectors match an element based on the contents of the
     * element's class attribute.
     * </p>
     * 
     * @param location A class location.
     */
    public void with(Location location, Style sub) {
        with(location).declare(sub);
    }

    /**
     * <p>
     * Write basic selector.
     * </p>
     * 
     * @param selector A selector expression.
     * @return Chainable API.
     */
    private static SelectorDSL basic(String selector) {
        return SelectorDSL.create(null).basic(selector);
    }

    // ===============================================================
    // Combinators
    // ===============================================================

    /**
     * <p>
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL ancestor() {
        return combine(" ", false);
    }

    /**
     * <p>
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * </p>
     * 
     * @param A sub style rule.
     */
    public void ancestor(Style sub) {
        ancestor().declare(sub);
    }

    /**
     * <p>
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL descendant() {
        return combine(" ", true);
    }

    /**
     * <p>
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * </p>
     * 
     * @return Chainable API.
     */
    public void descendant(Style sub) {
        descendant().declare(sub);
    }

    /**
     * <p>
     * The > combinator separates two selectors and matches only those elements matched by the
     * second selector that are direct children of elements matched by the first. By contrast, when
     * two selectors are combined with the descendant selector, the combined selector expression
     * matches those elements matched by the second selector for which there exists an ancestor
     * element matched by the first selector, regardless of the number of "hops" up the DOM.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL parent() {
        return combine(">", false);
    }

    /**
     * <p>
     * The > combinator separates two selectors and matches only those elements matched by the
     * second selector that are direct children of elements matched by the first. By contrast, when
     * two selectors are combined with the descendant selector, the combined selector expression
     * matches those elements matched by the second selector for which there exists an ancestor
     * element matched by the first selector, regardless of the number of "hops" up the DOM.
     * </p>
     * 
     * @return Chainable API.
     */
    public void parent(Style sub) {
        parent().declare(sub);
    }

    /**
     * <p>
     * The > combinator separates two selectors and matches only those elements matched by the
     * second selector that are direct children of elements matched by the first. By contrast, when
     * two selectors are combined with the descendant selector, the combined selector expression
     * matches those elements matched by the second selector for which there exists an ancestor
     * element matched by the first selector, regardless of the number of "hops" up the DOM.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL child() {
        return combine(">", true);
    }

    /**
     * <p>
     * The > combinator separates two selectors and matches only those elements matched by the
     * second selector that are direct children of elements matched by the first. By contrast, when
     * two selectors are combined with the descendant selector, the combined selector expression
     * matches those elements matched by the second selector for which there exists an ancestor
     * element matched by the first selector, regardless of the number of "hops" up the DOM.
     * </p>
     * 
     * @return Chainable API.
     */
    public void child(Style sub) {
        child().declare(sub);
    }

    /**
     * <p>
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL prev() {
        return combine("+", false);
    }

    /**
     * <p>
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void prev(Style sub) {
        prev().declare(sub);
    }

    /**
     * <p>
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL next() {
        return combine("+", true);
    }

    /**
     * <p>
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * </p>
     * 
     * @return Chainable API.
     */
    public void next(Style sub) {
        next().declare(sub);
    }

    /**
     * <p>
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL prevs() {
        return combine("~", false);
    }

    /**
     * <p>
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * </p>
     * 
     * @return Chainable API.
     */
    public void prevs(Style sub) {
        prevs().declare(sub);
    }

    /**
     * <p>
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL nexts() {
        return combine("~", true);
    }

    /**
     * <p>
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * </p>
     * 
     * @return Chainable API.
     */
    public void nexts(Style sub) {
        nexts().declare(sub);
    }

    /**
     * <p>
     * Write combinator.
     * </p>
     * 
     * @param type A combinator type.
     * @param forward A direction.
     * @return
     */
    private static SelectorDSL combine(String type, boolean forward) {
        return SelectorDSL.create(null).combine(type, forward);
    }

    // ===============================================================
    // Pseudo Elements
    // ===============================================================

    /**
     * <p>
     * The CSS :after pseudo-element matches a virtual last child of the selected element. Typically
     * used to add cosmetic content to an element, by using the content CSS property. This element
     * is inline by default.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void after(Style sub) {
        pseudo(true, "after").declare(sub);
    }

    /**
     * <p>
     * The CSS ::before creates a pseudo-element that is the first child of the element matched. It
     * is often used to add cosmetic content to an element by using the content property. This
     * element is inline by default.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void before(Style sub) {
        pseudo(true, "before").declare(sub);
    }

    /**
     * <p>
     * The ::first-letter CSS pseudo-element selects the first letter of the first line of a block,
     * if it is not preceded by any other content (such as images or inline tables) on its line.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void firstLetter(Style sub) {
        pseudo(true, "first-letter").declare(sub);
    }

    /**
     * <p>
     * The ::first-line CSS pseudo-element applies styles only to the first line of an element. The
     * amount of the text on the first line depends of numerous factors, like the width of the
     * elements or of the document, but also of the font size of the text. As all pseudo-elements,
     * the selectors containing ::first-line does not match any real HTML element.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void firstLine(Style sub) {
        pseudo(true, "first-line").declare(sub);
    }

    /**
     * <p>
     * The ::selection CSS pseudo-element applies rules to the portion of a document that has been
     * highlighted (e.g., selected with the mouse or another pointing device) by the user.
     * </p>
     * <p>
     * Only a small subset of CSS properties can be used in a rule using ::selection in its
     * selector: color, background, background-color and text-shadow. Note that, in particular,
     * background-image is ignored, like any other property.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void selection(Style sub) {
        // Gecko is the only engine requiring the prefix. Due to the fact that the CSS parsing rules
        // require dropping the whole rule when encountering an invalid pseudo-element, two separate
        // rules must be written: ::-moz-selection, ::selection {...}. The rule would be dropped on
        // non-Gecko browsers as ::-moz-selection is invalid on them.
        pseudo(true, Vendor.isMozilla() ? "-moz-selection" : "selection").declare(sub);
    }

    // ===============================================================
    // Pseudo Classes
    // ===============================================================

    /**
     * <p>
     * The :active CSS pseudo-class matches when an element is being activated by the user. It
     * allows the page to give a feedback that the activation has been detected by the browser. When
     * interacting with a mouse, this is typically the time between the user presses the mouse
     * button and releases it. The :active pseudo-class is also typically matched when using the
     * keyboard tab key. It is frequently used on <a> and <button> HTML elements, but may not be
     * limited to just those.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL active() {
        return pseudo(false, "active");
    }

    /**
     * <p>
     * The :active CSS pseudo-class matches when an element is being activated by the user. It
     * allows the page to give a feedback that the activation has been detected by the browser. When
     * interacting with a mouse, this is typically the time between the user presses the mouse
     * button and releases it. The :active pseudo-class is also typically matched when using the
     * keyboard tab key. It is frequently used on <a> and <button> HTML elements, but may not be
     * limited to just those.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void active(Style sub) {
        active().declare(sub);
    }

    /**
     * <p>
     * The :checked CSS pseudo-class selector represents any radio (<input type="radio">), checkbox
     * (<input type="checkbox">) or option (<option> in a <select>) element that is checked or
     * toggled to an on state. The user can change this state by clicking on the element, or
     * selecting a different value, in which case the :checked pseudo-class no longer applies to
     * this element, but will to the relevant one.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL checked() {
        return pseudo(false, "checked");
    }

    /**
     * <p>
     * The :checked CSS pseudo-class selector represents any radio (<input type="radio">), checkbox
     * (<input type="checkbox">) or option (<option> in a <select>) element that is checked or
     * toggled to an on state. The user can change this state by clicking on the element, or
     * selecting a different value, in which case the :checked pseudo-class no longer applies to
     * this element, but will to the relevant one.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void checked(Style sub) {
        checked().declare(sub);
    }

    /**
     * <p>
     * The :default CSS pseudo-class represents any user interface element that is thepublic static
     * final among a group of similar elements.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL defaults() {
        return pseudo(false, "default");
    }

    /**
     * <p>
     * The :default CSS pseudo-class represents any user interface element that is thepublic static
     * final among a group of similar elements.
     * </p>
     * 
     * @return Chainable API.
     */
    public void defaults(Style sub) {
        defaults().declare(sub);
    }

    /**
     * <p>
     * The :disabled CSS pseudo-class represents any disabled element. An element is disabled if it
     * can't be activated (e.g. selected, clicked on or accept text input) or accept focus. The
     * element also has an enabled state, in which it can be activated or accept focus.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL disabled() {
        return pseudo(false, "disabled");
    }

    /**
     * <p>
     * The :disabled CSS pseudo-class represents any disabled element. An element is disabled if it
     * can't be activated (e.g. selected, clicked on or accept text input) or accept focus. The
     * element also has an enabled state, in which it can be activated or accept focus.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void disabled(Style sub) {
        disabled().declare(sub);
    }

    /**
     * <p>
     * The :empty pseudo-class represents any element that has no children at all. Only element
     * nodes and text (including whitespace) are considered. Comments or processing instructions do
     * not affect whether an element is considered empty or not.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL empty() {
        return pseudo(false, "empty");
    }

    /**
     * <p>
     * The :empty pseudo-class represents any element that has no children at all. Only element
     * nodes and text (including whitespace) are considered. Comments or processing instructions do
     * not affect whether an element is considered empty or not.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void empty(Style sub) {
        empty().declare(sub);
    }

    /**
     * <p>
     * The :enabled CSS pseudo-class represents any enabled element. An element is enabled if it can
     * be activated (e.g. selected, clicked on or accept text input) or accept focus. The element
     * also has an disabled state, in which it can't be activated or accept focus.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL enabled() {
        return pseudo(false, "enabled");
    }

    /**
     * <p>
     * The :enabled CSS pseudo-class represents any enabled element. An element is enabled if it can
     * be activated (e.g. selected, clicked on or accept text input) or accept focus. The element
     * also has an disabled state, in which it can't be activated or accept focus.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void enabled(Style sub) {
        enabled().declare(sub);
    }

    /**
     * <p>
     * The :first-child CSS pseudo-class represents any element that is the first child element of
     * its parent.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL firstChild() {
        return pseudo(false, "first-child");
    }

    /**
     * <p>
     * The :first-child CSS pseudo-class represents any element that is the first child element of
     * its parent.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void firstChild(Style sub) {
        firstChild().declare(sub);
    }

    /**
     * <p>
     * The :first-of-type CSS pseudo-class represents the first sibling of its type in the list of
     * children of its parent element.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL firstType() {
        return pseudo(false, "first-of-type");
    }

    /**
     * <p>
     * The :first-of-type CSS pseudo-class represents the first sibling of its type in the list of
     * children of its parent element.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void firstOfType(Style sub) {
        firstType().declare(sub);
    }

    /**
     * <p>
     * The :focus CSS pseudo-class is applied when an element has received focus, either from the
     * user selecting it with the use of a keyboard or by activating with the mouse (e.g. a form
     * input).
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL focus() {
        return pseudo(false, "focus");
    }

    /**
     * <p>
     * The :focus CSS pseudo-class is applied when an element has received focus, either from the
     * user selecting it with the use of a keyboard or by activating with the mouse (e.g. a form
     * input).
     * </p>
     * 
     * @param sub A sub style.
     */
    public void focus(Style sub) {
        focus().declare(sub);
    }

    /**
     * <p>
     * The :hover CSS pseudo-class matches when the user designates an element with a pointing
     * device, but does not necessarily activate it. This style may be overridden by any other
     * link-related pseudo-classes, that is :link, :visited, and :active, appearing in subsequent
     * rules. In order to style appropriately links, you need to put the :hover rule after the :link
     * and :visited rules but before the :active one, as defined by the LVHA-order: :link — :visited
     * — :hover — :active.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL hover() {
        return pseudo(false, "hover");
    }

    /**
     * <p>
     * The :hover CSS pseudo-class matches when the user designates an element with a pointing
     * device, but does not necessarily activate it. This style may be overridden by any other
     * link-related pseudo-classes, that is :link, :visited, and :active, appearing in subsequent
     * rules. In order to style appropriately links, you need to put the :hover rule after the :link
     * and :visited rules but before the :active one, as defined by the LVHA-order: :link — :visited
     * — :hover — :active.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void hover(Style sub) {
        hover().declare(sub);
    }

    /**
     * <p>
     * The :indeterminate CSS pseudo-class represents:
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL indeterminate() {
        return pseudo(false, "indeterminate");
    }

    /**
     * <p>
     * The :indeterminate CSS pseudo-class represents:
     * </p>
     * 
     * @param sub A sub style.
     */
    public void indeterminate(Style sub) {
        indeterminate().declare(sub);
    }

    /**
     * <p>
     * The :invalid CSS pseudo-class represents any <input> or <form> element whose content fails to
     * validate according to the input's type setting. This allows you to easily have invalid fields
     * adopt an appearance that helps the user identify and correct errors.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL invalid() {
        return pseudo(false, "invalid");
    }

    /**
     * <p>
     * The :invalid CSS pseudo-class represents any <input> or <form> element whose content fails to
     * validate according to the input's type setting. This allows you to easily have invalid fields
     * adopt an appearance that helps the user identify and correct errors.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void invalid(Style sub) {
        invalid().declare(sub);
    }

    /**
     * <p>
     * The :last-child CSS pseudo-class represents any element that is the last child element of its
     * parent.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL lastChild() {
        return pseudo(false, "last-child");
    }

    /**
     * <p>
     * The :last-child CSS pseudo-class represents any element that is the last child element of its
     * parent.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void lastChild(Style sub) {
        lastChild().declare(sub);
    }

    /**
     * <p>
     * The :last-of-type CSS pseudo-class represents the last sibling with the given element name in
     * the list of children of its parent element.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL lastType() {
        return pseudo(false, "last-of-type");
    }

    /**
     * <p>
     * The :last-of-type CSS pseudo-class represents the last sibling of its type in the list of
     * children of its parent element.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void lastType(Style sub) {
        lastType().declare(sub);
    }

    /**
     * <p>
     * The :link CSS pseudo-class lets you select links inside elements. This will select any link
     * which has not yet been visited, even those already styled using selector with other
     * link-related pseudo-classes like :hover, :active or :visited. In order to appropriately style
     * links, you need to put the :link rule before the other ones, as defined by the LVHA-order:
     * :link — :visited — :hover — :active. The :focus pseudo-class is usually placed right before
     * or right after :hover, depending on the expected effect.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL link() {
        return pseudo(false, "link");
    }

    /**
     * <p>
     * The :link CSS pseudo-class lets you select links inside elements. This will select any link
     * which has not yet been visited, even those already styled using selector with other
     * link-related pseudo-classes like :hover, :active or :visited. In order to appropriately style
     * links, you need to put the :link rule before the other ones, as defined by the LVHA-order:
     * :link — :visited — :hover — :active. The :focus pseudo-class is usually placed right before
     * or right after :hover, depending on the expected effect.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void link(Style sub) {
        link().declare(sub);
    }

    /**
     * <p>
     * The negation CSS pseudo-class, :not(X), is a functional notation taking a simple selector X
     * as an argument. It matches an element that is not represented by the argument. X must not
     * contain another negation selector.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL not(SelectorDSL selector) {
        // not-pseudo-class accepts simple selector only
        return pseudo(false, "not(" + selector.toString().replaceAll("\\$", "") + ")");
    }

    /**
     * <p>
     * The negation CSS pseudo-class, :not(X), is a functional notation taking a simple selector X
     * as an argument. It matches an element that is not represented by the argument. X must not
     * contain another negation selector.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void not(SelectorDSL selector, Style sub) {
        not(selector).declare(sub);
    }

    /**
     * <p>
     * The negation CSS pseudo-class, :not(X), is a functional notation taking a simple selector X
     * as an argument. It matches an element that is not represented by the argument. X must not
     * contain another negation selector.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL not(Style selector) {
        // not-pseudo-class accepts simple selector only
        return pseudo(false, "not(." + selector.name() + ")");
    }

    /**
     * <p>
     * The negation CSS pseudo-class, :not(X), is a functional notation taking a simple selector X
     * as an argument. It matches an element that is not represented by the argument. X must not
     * contain another negation selector.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void not(Style selector, Style sub) {
        not(selector).declare(sub);
    }

    /**
     * <p>
     * The :nth-child CSS pseudo-class matches an element that has an+b-1 siblings before it in the
     * document tree, for a given positive or zero value for n, and has a parent element.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL nthChild(String pattern) {
        return pseudo(false, "nth-child(" + pattern + ")");
    }

    /**
     * <p>
     * The :nth-child CSS pseudo-class matches an element that has an+b-1 siblings before it in the
     * document tree, for a given positive or zero value for n, and has a parent element.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void nthChild(String pattern, Style sub) {
        nthChild(pattern).declare(sub);
    }

    /**
     * <p>
     * The :nth-last-child CSS pseudo-class matches an element that has an+b-1 siblings after it in
     * the document tree, for a given positive or zero value for n, and has a parent element. See
     * :nth-child for a more thorough description of the syntax of its argument.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL nthLastChild(String pattern) {
        return pseudo(false, "nth-last-child(" + pattern + ")");
    }

    /**
     * <p>
     * The :nth-last-child CSS pseudo-class matches an element that has an+b-1 siblings after it in
     * the document tree, for a given positive or zero value for n, and has a parent element. See
     * :nth-child for a more thorough description of the syntax of its argument.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void nthLastChild(String pattern, Style sub) {
        nthLastChild(pattern).declare(sub);
    }

    /**
     * <p>
     * The :nth-of-type CSS pseudo-class matches an element that has an+b-1 siblings with the same
     * element name before it in the document tree, for a given positive or zero value for n, and
     * has a parent element. See :nth-child for a more thorough description of the syntax of its
     * argument. This is a more flexible and useful pseudo selector if you want to ensure you're
     * selecting the same type of tag no matter where it is inside the parent element, or what other
     * different tags appear before it.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL nthType(String pattern) {
        return pseudo(false, "nth-of-type(" + pattern + ")");
    }

    /**
     * <p>
     * The :nth-of-type CSS pseudo-class matches an element that has an+b-1 siblings with the same
     * element name before it in the document tree, for a given positive or zero value for n, and
     * has a parent element. See :nth-child for a more thorough description of the syntax of its
     * argument. This is a more flexible and useful pseudo selector if you want to ensure you're
     * selecting the same type of tag no matter where it is inside the parent element, or what other
     * different tags appear before it.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void nthType(String pattern, Style sub) {
        nthType(pattern).declare(sub);
    }

    /**
     * <p>
     * The :nth-last-of-type CSS pseudo-class matches an element that has an+b-1 siblings with the
     * same element name after it in the document tree, for a given positive or zero value for n,
     * and has a parent element. See :nth-child for a more thorough description of the syntax of its
     * argument.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL nthLastType(String pattern) {
        return pseudo(false, "nth-last-of-type(" + pattern + ")");
    }

    /**
     * <p>
     * The :nth-last-of-type CSS pseudo-class matches an element that has an+b-1 siblings with the
     * same element name after it in the document tree, for a given positive or zero value for n,
     * and has a parent element. See :nth-child for a more thorough description of the syntax of its
     * argument.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void nthLastType(String pattern, Style sub) {
        nthLastType(pattern).declare(sub);
    }

    /**
     * <p>
     * The :only-child CSS pseudo-class represents any element which is the only child of its
     * parent. This is the same as :first-child:last-child or :nth-child(1):nth-last-child(1), but
     * with a lower specificity.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL onlyChild() {
        return pseudo(false, "only-child");
    }

    /**
     * <p>
     * The :only-child CSS pseudo-class represents any element which is the only child of its
     * parent. This is the same as :first-child:last-child or :nth-child(1):nth-last-child(1), but
     * with a lower specificity.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void onlyChild(Style sub) {
        onlyChild().declare(sub);
    }

    /**
     * <p>
     * The :only-of-type CSS pseudo-class represents any element that has no siblings of the given
     * type.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL onlyType() {
        return pseudo(false, "only-of-type");
    }

    /**
     * <p>
     * The :only-of-type CSS pseudo-class represents any element that has no siblings of the given
     * type.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void onlyType(Style sub) {
        onlyType().declare(sub);
    }

    /**
     * <p>
     * The :optional CSS pseudo-class represents any <input> element that does not have the required
     * attribute set on it. This allows forms to easily indicate optional fields, and to style them
     * accordingly.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL optional() {
        return pseudo(false, "optional");
    }

    /**
     * <p>
     * The :optional CSS pseudo-class represents any <input> element that does not have the required
     * attribute set on it. This allows forms to easily indicate optional fields, and to style them
     * accordingly.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void optional(Style sub) {
        optional().declare(sub);
    }

    /**
     * <p>
     * The :required CSS pseudo-class represents any <input> element that has the required attribute
     * set on it. This allows forms to easily indicate which fields must have valid data before the
     * form can be submitted.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL required() {
        return pseudo(false, "required");
    }

    /**
     * <p>
     * The :required CSS pseudo-class represents any <input> element that has the required attribute
     * set on it. This allows forms to easily indicate which fields must have valid data before the
     * form can be submitted.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void required(Style sub) {
        required().declare(sub);
    }

    /**
     * <p>
     * The :target pseudo-class represents the unique element, if any, with an id matching the
     * fragment identifier of the URI of the document.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL target() {
        return pseudo(false, "target");
    }

    /**
     * <p>
     * The :target pseudo-class represents the unique element, if any, with an id matching the
     * fragment identifier of the URI of the document.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void target(Style sub) {
        target().declare(sub);
    }

    /**
     * <p>
     * The :valid CSS pseudo-class represents any <input> or <form> element whose content validates
     * correctly according to the input's type setting. This allows to easily make valid fields
     * adopt an appearance that helps the user confirm that their data is formatted properly.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL valid() {
        return pseudo(false, "valid");
    }

    /**
     * <p>
     * The :valid CSS pseudo-class represents any <input> or <form> element whose content validates
     * correctly according to the input's type setting. This allows to easily make valid fields
     * adopt an appearance that helps the user confirm that their data is formatted properly.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void valid(Style sub) {
        valid().declare(sub);
    }

    /**
     * <p>
     * The :visited CSS pseudo-class lets you select only links that have been visited. This style
     * may be overridden by any other link-related pseudo-classes, that is :link, :hover, and
     * :active, appearing in subsequent rules. In order to style appropriately links, you need to
     * put the :visited rule after the :link rule but before the other ones, defined in the
     * LVHA-order: :link — :visited — :hover — :active.
     * </p>
     * 
     * @return Chainable API.
     */
    public SelectorDSL visited() {
        return pseudo(false, "visited");
    }

    /**
     * <p>
     * The :visited CSS pseudo-class lets you select only links that have been visited. This style
     * may be overridden by any other link-related pseudo-classes, that is :link, :hover, and
     * :active, appearing in subsequent rules. In order to style appropriately links, you need to
     * put the :visited rule after the :link rule but before the other ones, defined in the
     * LVHA-order: :link — :visited — :hover — :active.
     * </p>
     * 
     * @param sub A sub style.
     */
    public void visited(Style sub) {
        visited().declare(sub);
    }

    /**
     * {@inheritDoc}
     */
    private static SelectorDSL pseudo(boolean isElement, String name) {
        return SelectorDSL.create(null).pseudo(isElement, name);
    }
}
