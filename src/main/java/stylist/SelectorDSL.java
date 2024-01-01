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

import java.util.ArrayList;
import java.util.List;

import stylist.value.CSSValue;
import stylist.value.Vendor;

public class SelectorDSL {

    /** The root selector. */
    private SelectorDSL root = this;

    /** The child selector. */
    private SelectorDSL child;

    /** The style creation processor. */
    private final Runnable processor;

    /** The simple selector list. */
    String selector = "";

    /** The combinator. */
    private String combinator;

    /** The pseudo element. */
    private CSSValue pseudoElement;

    /** The pseudo class list. */
    private final List<CSSValue> pseudoClasses = new ArrayList();

    /**
     * <p>
     * Create new selector.
     * </p>
     * 
     * @param processor
     */
    SelectorDSL(Runnable processor) {
        this.processor = processor;
    }

    // ===============================================================
    // Basic Selectors
    // ===============================================================

    /**
     * Attribute selectors select an element using the presence of a given attribute or attribute
     * value.
     * 
     * @param name An attribute name.
     * @return Chainable API.
     */
    public final Attribute attr(String name) {
        return new Attribute(this, name);
    }

    /**
     * Attribute selectors select an element using the presence of a given attribute or attribute
     * value.
     * <p>
     * Represents an element with an attribute name.
     * <p>
     * This is shorthand method of {@link Attribute#exist(Style)}
     * 
     * @param name An attribute name.
     * @param sub A sub style rule.
     */
    public final void attr(String name, Style sub) {
        attr(name).exist(sub);
    }

    /**
     * Attribute selectors select an element using the presence of a given attribute or attribute
     * value.
     * <p>
     * Represents an element with an attribute name.
     * <p>
     * This is shorthand method of {@link Attribute#with(Style)}
     * 
     * @param name An attribute name.
     * @param value An attribute value.
     * @param sub A sub style rule.
     */
    public final void attr(String name, String value, Style sub) {
        attr(name).is(value, sub);
    }

    /**
     * In an HTML document, CSS class selectors match an element based on the contents of the
     * element's class attribute.
     * 
     * @param location A class location.
     * @return Chainable API.
     */
    public final SelectorDSL with(Style location) {
        return basic(location.selector());
    }

    /**
     * In an HTML document, CSS class selectors match an element based on the contents of the
     * element's class attribute.
     * 
     * @param location A class location.
     */
    public final void with(Style location, Style sub) {
        with(location.selector(), sub);
    }

    /**
     * In an HTML document, CSS class selectors match an element based on the contents of the
     * element's class attribute.
     * 
     * @param location A class location.
     */
    public final void with(String location, Style sub) {
        basic(location).declare(sub);
    }

    /**
     * Write basic selector.
     * 
     * @param selector A selector expression.
     * @return Chainable API.
     */
    SelectorDSL basic(String selector) {
        this.selector += selector;

        return this;
    }

    // ===============================================================
    // Combinators
    // ===============================================================

    /**
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL ancestor() {
        return combine(" ", false);
    }

    /**
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * 
     * @param sub A sub style rule.
     */
    public final void ancestor(Style sub) {
        ancestor().declare(sub);
    }

    /**
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL descendant() {
        return combine(" ", true);
    }

    /**
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     */
    public final void descendant(Style sub) {
        descendant().declare(sub);
    }

    /**
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     */
    public final void descendant(Style descendant, Style sub) {
        descendant(descendant.selector(), sub);
    }

    /**
     * A descendant combinator — typically represented by a single space ( ) character in the form
     * of selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     */
    public final void descendant(String descendant, Style sub) {
        basic(" " + descendant).declare(sub);
    }

    /**
     * The > combinator separates two selectors and matches only those elements matched by the
     * second selector that are direct children of elements matched by the first. By contrast, when
     * two selectors are combined with the descendant selector, the combined selector expression
     * matches those elements matched by the second selector for which there exists an ancestor
     * element matched by the first selector, regardless of the number of "hops" up the DOM.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL parent() {
        return combine(">", false);
    }

    /**
     * The > combinator separates two selectors and matches only those elements matched by the
     * second selector that are direct children of elements matched by the first. By contrast, when
     * two selectors are combined with the descendant selector, the combined selector expression
     * matches those elements matched by the second selector for which there exists an ancestor
     * element matched by the first selector, regardless of the number of "hops" up the DOM.
     */
    public final void parent(Style sub) {
        parent().declare(sub);
    }

    /**
     * The > combinator separates two selectors and matches only those elements matched by the
     * second selector that are direct children of elements matched by the first. By contrast, when
     * two selectors are combined with the descendant selector, the combined selector expression
     * matches those elements matched by the second selector for which there exists an ancestor
     * element matched by the first selector, regardless of the number of "hops" up the DOM.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL child() {
        return combine(">", true);
    }

    /**
     * The > combinator separates two selectors and matches only those elements matched by the
     * second selector that are direct children of elements matched by the first. By contrast, when
     * two selectors are combined with the descendant selector, the combined selector expression
     * matches those elements matched by the second selector for which there exists an ancestor
     * element matched by the first selector, regardless of the number of "hops" up the DOM.
     */
    public final void child(Style sub) {
        child().declare(sub);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL prev() {
        return combine("+", false);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * 
     * @param sub A sub style.
     */
    public final void prev(Style sub) {
        prev().declare(sub);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * 
     * @param sub A sub style.
     */
    public final void prev(Style prev, Style sub) {
        prev(prev.selector(), sub);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * 
     * @param sub A sub style.
     */
    public final void prev(String prev, Style sub) {
        combine(prev + "+", false).declare(sub);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL next() {
        return combine("+", true);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     */
    public final void next(Style sub) {
        next().declare(sub);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     */
    public final void next(Style next, Style sub) {
        next(next.selector(), sub);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     */
    public final void next(String next, Style sub) {
        basic("+" + next).declare(sub);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL prevs() {
        return combine("~", false);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     */
    public final void prevs(Style sub) {
        prevs().declare(sub);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL nexts() {
        return combine("~", true);
    }

    /**
     * This is referred to as an adjacent selector or next-sibling selector. It will select only the
     * specified element that immediately follows the former specified element.
     */
    public final void nexts(Style sub) {
        nexts().declare(sub);
    }

    /**
     * Write combinator.
     * 
     * @param type A combinator type.
     * @param forward A direction.
     * @return
     */
    SelectorDSL combine(String type, boolean forward) {
        SelectorDSL created = new SelectorDSL(processor);

        if (forward) {
            child = created;
            combinator = type;
            created.root = root;
        } else {
            created.child = this;
            created.combinator = type;
        }
        return created;
    }

    // ===============================================================
    // Pseudo Elements
    // ===============================================================

    /**
     * The CSS :after pseudo-element matches a virtual last child of the selected element. Typically
     * used to add cosmetic content to an element, by using the content CSS property. This element
     * is inline by default.
     * 
     * @param sub A sub style.
     */
    public final void after(Style sub) {
        pseudo(true, "after").declare(sub);
    }

    /**
     * The CSS ::before creates a pseudo-element that is the first child of the element matched. It
     * is often used to add cosmetic content to an element by using the content property. This
     * element is inline by default.
     * 
     * @param sub A sub style.
     */
    public final void before(Style sub) {
        pseudo(true, "before").declare(sub);
    }

    /**
     * The ::first-letter CSS pseudo-element selects the first letter of the first line of a block,
     * if it is not preceded by any other content (such as images or inline tables) on its line.
     * 
     * @param sub A sub style.
     */
    public final void firstLetter(Style sub) {
        pseudo(true, "first-letter").declare(sub);
    }

    /**
     * The ::first-line CSS pseudo-element applies styles only to the first line of an element. The
     * amount of the text on the first line depends of numerous factors, like the width of the
     * elements or of the document, but also of the font size of the text. As all pseudo-elements,
     * the selectors containing ::first-line does not match any real HTML element.
     * 
     * @param sub A sub style.
     */
    public final void firstLine(Style sub) {
        pseudo(true, "first-line").declare(sub);
    }

    /**
     * The ::selection CSS pseudo-element applies rules to the portion of a document that has been
     * highlighted (e.g., selected with the mouse or another pointing device) by the user.
     * <p>
     * Only a small subset of CSS properties can be used in a rule using ::selection in its
     * selector: color, background, background-color and text-shadow. Note that, in particular,
     * background-image is ignored, like any other property.
     * 
     * @param sub A sub style.
     */
    public final void selection(Style sub) {
        // Gecko is the only engine requiring the prefix. Due to the fact that the CSS parsing rules
        // require dropping the whole rule when encountering an invalid pseudo-element, two separate
        // rules must be written: ::-moz-selection, ::selection {...}. The rule would be dropped on
        // non-Gecko browsers as ::-moz-selection is invalid on them.
        pseudo(true, CSSValue.of("selection", Vendor.Mozilla)).declare(sub);
    }

    // ===============================================================
    // Pseudo Classes
    // ===============================================================

    /**
     * The :active CSS pseudo-class matches when an element is being activated by the user. It
     * allows the page to give a feedback that the activation has been detected by the browser. When
     * interacting with a mouse, this is typically the time between the user presses the mouse
     * button and releases it. The :active pseudo-class is also typically matched when using the
     * keyboard tab key. It is frequently used on <a> and <button> HTML elements, but may not be
     * limited to just those.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL active() {
        return pseudo(false, "active");
    }

    /**
     * The :active CSS pseudo-class matches when an element is being activated by the user. It
     * allows the page to give a feedback that the activation has been detected by the browser. When
     * interacting with a mouse, this is typically the time between the user presses the mouse
     * button and releases it. The :active pseudo-class is also typically matched when using the
     * keyboard tab key. It is frequently used on <a> and <button> HTML elements, but may not be
     * limited to just those.
     * 
     * @param sub A sub style.
     */
    public final void active(Style sub) {
        active().declare(sub);
    }

    /**
     * The :checked CSS pseudo-class selector represents any radio (<input type="radio">), checkbox
     * (<input type="checkbox">) or option (<option> in a <select>) element that is checked or
     * toggled to an on state. The user can change this state by clicking on the element, or
     * selecting a different value, in which case the :checked pseudo-class no longer applies to
     * this element, but will to the relevant one.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL checked() {
        return pseudo(false, "checked");
    }

    /**
     * The :checked CSS pseudo-class selector represents any radio (<input type="radio">), checkbox
     * (<input type="checkbox">) or option (<option> in a <select>) element that is checked or
     * toggled to an on state. The user can change this state by clicking on the element, or
     * selecting a different value, in which case the :checked pseudo-class no longer applies to
     * this element, but will to the relevant one.
     * 
     * @param sub A sub style.
     */
    public final void checked(Style sub) {
        checked().declare(sub);
    }

    /**
     * The :default CSS pseudo-class represents any user interface element that is the public final
     * among a group of similar elements.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL defaults() {
        return pseudo(false, "default");
    }

    /**
     * The :default CSS pseudo-class represents any user interface element that is the public final
     * among a group of similar elements.
     */
    public final void defaults(Style sub) {
        defaults().declare(sub);
    }

    /**
     * The :disabled CSS pseudo-class represents any disabled element. An element is disabled if it
     * can't be activated (e.g. selected, clicked on or accept text input) or accept focus. The
     * element also has an enabled state, in which it can be activated or accept focus.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL disabled() {
        return pseudo(false, "disabled");
    }

    /**
     * The :disabled CSS pseudo-class represents any disabled element. An element is disabled if it
     * can't be activated (e.g. selected, clicked on or accept text input) or accept focus. The
     * element also has an enabled state, in which it can be activated or accept focus.
     * 
     * @param sub A sub style.
     */
    public final void disabled(Style sub) {
        disabled().declare(sub);
    }

    /**
     * The :empty pseudo-class represents any element that has no children at all. Only element
     * nodes and text (including whitespace) are considered. Comments or processing instructions do
     * not affect whether an element is considered empty or not.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL empty() {
        return pseudo(false, "empty");
    }

    /**
     * The :empty pseudo-class represents any element that has no children at all. Only element
     * nodes and text (including whitespace) are considered. Comments or processing instructions do
     * not affect whether an element is considered empty or not.
     * 
     * @param sub A sub style.
     */
    public final void empty(Style sub) {
        empty().declare(sub);
    }

    /**
     * The :enabled CSS pseudo-class represents any enabled element. An element is enabled if it can
     * be activated (e.g. selected, clicked on or accept text input) or accept focus. The element
     * also has an disabled state, in which it can't be activated or accept focus.
     * 
     * @return Chainable API.
     */
    public final SelectorDSL enabled() {
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
    public final void enabled(Style sub) {
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
    public final SelectorDSL firstChild() {
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
    public final void firstChild(Style sub) {
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
    public final SelectorDSL firstType() {
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
    public final void firstType(Style sub) {
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
    public final SelectorDSL focus() {
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
    public final void focus(Style sub) {
        focus().declare(sub);
    }

    public final void has(String selector, Style style) {
        pseudo(false, "has(" + selector + ")").declare(style);
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
    public final SelectorDSL hover() {
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
    public final void hover(Style sub) {
        hover().declare(sub);
    }

    /**
     * <p>
     * The :indeterminate CSS pseudo-class represents:
     * </p>
     * 
     * @return Chainable API.
     */
    public final SelectorDSL indeterminate() {
        return pseudo(false, "indeterminate");
    }

    /**
     * <p>
     * The :indeterminate CSS pseudo-class represents:
     * </p>
     * 
     * @param sub A sub style.
     */
    public final void indeterminate(Style sub) {
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
    public final SelectorDSL invalid() {
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
    public final void invalid(Style sub) {
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
    public final SelectorDSL lastChild() {
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
    public final void lastChild(Style sub) {
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
    public final SelectorDSL lastType() {
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
    public final void lastType(Style sub) {
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
    public final SelectorDSL link() {
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
    public final void link(Style sub) {
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
    public final SelectorDSL not(SelectorDSL selector) {
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
    public final void not(SelectorDSL selector, Style sub) {
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
    public final SelectorDSL not(Style selector) {
        // not-pseudo-class accepts simple selector only
        return pseudo(false, "not(." + selector.selector() + ")");
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
    public final void not(Style selector, Style sub) {
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
    public final SelectorDSL nthChild(String pattern) {
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
    public final void nthChild(String pattern, Style sub) {
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
    public final SelectorDSL nthLastChild(String pattern) {
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
    public final void nthLastChild(String pattern, Style sub) {
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
    public final SelectorDSL nthType(String pattern) {
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
    public final void nthType(String pattern, Style sub) {
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
    public final SelectorDSL nthLastType(String pattern) {
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
    public final void nthLastType(String pattern, Style sub) {
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
    public final SelectorDSL onlyChild() {
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
    public final void onlyChild(Style sub) {
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
    public final SelectorDSL onlyType() {
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
    public final void onlyType(Style sub) {
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
    public final SelectorDSL optional() {
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
    public final void optional(Style sub) {
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
    public final SelectorDSL required() {
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
    public final void required(Style sub) {
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
    public final SelectorDSL target() {
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
    public final void target(Style sub) {
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
    public final SelectorDSL valid() {
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
    public final void valid(Style sub) {
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
    public final SelectorDSL visited() {
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
    public final void visited(Style sub) {
        visited().declare(sub);
    }

    /**
     * <p>
     * Write pseudo class.
     * </p>
     * 
     * @param element A pseudo type.
     * @param name A pseudo name.
     * @return Chainable API.
     */
    SelectorDSL pseudo(boolean element, Object name) {
        if (element) {
            pseudoElement = CSSValue.of(name);
        } else {
            pseudoClasses.add(CSSValue.of(name));
        }
        return this;
    }

    public final void media(MediaQuery query, Style style) {
        StyleRule create = Stylist.create(style, create(processor));
        create.query.set(query);
    }

    /**
     * <p>
     * A generic combinator — typically represented by a single space ( ) character in the form of
     * selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * </p>
     * 
     * @param selector A css selector.
     * @return Chainable API.
     */
    public final SelectorDSL select(String selector) {
        SelectorDSL child = combine(" ", true);
        child.selector = selector;
        return child;
    }

    /**
     * A generic combinator — typically represented by a single space ( ) character in the form of
     * selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * 
     * @param selector A css selector.
     * @param sub A sub style.
     */
    public final void select(String selector, Style sub) {
        select(selector).declare(sub);
    }

    /**
     * A generic combinator — typically represented by a single space ( ) character in the form of
     * selector₁ selector₂ — combines two selectors such that elements matched by the second
     * selector (selector₂) are selected if they have an ancestor element matching the first
     * selector (selector₁). Selectors that utilize a descendant combinator are called descendant
     * selectors.
     * 
     * @param selector A css selector.
     * @param sub A sub style.
     */
    public final void select(Style selector, Style sub) {
        select(selector.selector()).declare(sub);
    }

    /**
     * Declare the specified new style with this selector expression.
     * 
     * @param style A style declaration.
     */
    private void declare(Style style) {
        if (style != null) {
            Stylist.create(style, this);

            if (root.processor != null) {
                root.processor.run();
            }
        }
    }

    /**
     * Create the selector expression as {@link CSSValue}.
     * 
     * @return A selector expression.
     */
    final CSSValue selector() {
        return root.computeSelector();
    }

    /**
     * Compute the selector of this {@link SelectorDSL}.
     * 
     * @return A selector expression.
     */
    private CSSValue computeSelector() {
        CSSValue base = CSSValue.of(selector.length() == 0 ? "*" : selector);

        for (CSSValue pseudo : pseudoClasses) {
            base = base.join(":", pseudo);
        }

        if (pseudoElement != null) {
            base = base.join("::", pseudoElement);
        }

        if (combinator != null) {
            base = base.join(combinator, child.computeSelector());
        }
        return base;
    }

    /**
     * Replace selector items by the parent selector.
     * 
     * @param parent
     */
    final void replace(SelectorDSL parent) {
        // replace placeholder by parent selector
        SelectorDSL now = root;

        while (now != null) {
            String selector = parent.toString();
            int hasPasudeElement = selector.indexOf("::");
            if (hasPasudeElement != -1) selector = selector.substring(0, hasPasudeElement);

            now.selector = now.selector.replace("$", selector);
            now = now.child;
        }

        // This is useless?
        // assign pseudo classes
        // pseudoClasses.addAll(0, parent.pseudoClasses);

        // replace pseudo element
        if (parent.pseudoElement != null) {
            pseudoElement = parent.pseudoElement;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return selector().valueFor(Vendor.Standard);
    }

    /**
     * Create new selector builder.
     * 
     * @param processor A style creation process.
     * @return A created selector builder.
     */
    public static final SelectorDSL create(Runnable processor) {
        return new SelectorDSL(processor).basic("$");
    }

    /**
     * @version 2018/08/30 18:56:47
     */
    public static final class Attribute {

        /** The parent */
        private final SelectorDSL parent;

        /** An attribute name. */
        private final String name;

        /** The flag for ignore case. */
        private boolean ignoreCase;

        /**
         * Create attribute selector builder.
         */
        Attribute(SelectorDSL parent, String name) {
            if (name == null || name.equals("")) {
                throw new IllegalArgumentException("Specify attribute name.");
            }
            this.parent = parent;
            this.name = name;
        }

        /**
         * Set case sensitivity.
         * 
         * @return Chainable API.
         */
        public Attribute ignoreCase() {
            ignoreCase = true;
            return this;
        }

        /**
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * <p>
         * Represents an element with an attribute name.
         * 
         * @return Chainable API.
         */
        public SelectorDSL exist() {
            return attr(name, null, null);
        }

        /**
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * <p>
         * Represents an element with an attribute name.
         * 
         * @param sub A sub style.
         */
        public void exist(Style sub) {
            exist().declare(sub);
        }

        /**
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * <p>
         * Represents an element with an attribute name.
         * 
         * @param value An attribute value.
         * @return Chainable API.
         */
        public SelectorDSL is(String value) {
            return attr(name, "", value);
        }

        /**
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * <p>
         * Represents an element with an attribute name.
         * 
         * @param value An attribute value.
         * @param sub A sub style.
         */
        public void is(String value, Style sub) {
            is(value).declare(sub);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr whose value is a
         * whitespace-separated list of words, one of which is exactly "value".
         * </p>
         * 
         * @param value An attribute value.
         * @return Chainable API.
         */
        public SelectorDSL isSpace(String value) {
            return attr(name, "~", value);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr whose value is a
         * whitespace-separated list of words, one of which is exactly "value".
         * </p>
         * 
         * @param value An attribute value.
         * @param sub A sub style.
         */
        public void isSpace(String value, Style sub) {
            isSpace(value).declare(sub);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr. Its value can be exactly “value” or
         * can begin with “value” immediately followed by “-” (U+002D). It can be used for language
         * subcode matches.
         * </p>
         * 
         * @param value An attribute value.
         * @return Chainable API.
         */
        public SelectorDSL isHyphen(String value) {
            return attr(name, "|", value);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr. Its value can be exactly “value” or
         * can begin with “value” immediately followed by “-” (U+002D). It can be used for language
         * subcode matches.
         * </p>
         * 
         * @param value An attribute value.
         * @param sub A sub style.
         */
        public void isHyphen(String value, Style sub) {
            isHyphen(value).declare(sub);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr and whose first value is prefixed by
         * "value".
         * </p>
         * 
         * @param value An attribute value.
         * @return Chainable API.
         */
        public SelectorDSL startsWith(String value) {
            return attr(name, "^", value);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr and whose first value is prefixed by
         * "value".
         * </p>
         * 
         * @param value An attribute value.
         * @param sub A sub style.
         */
        public void startsWith(String value, Style sub) {
            startsWith(value).declare(sub);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr and whose last value is suffixed by
         * "value".
         * </p>
         * 
         * @param value An attribute value.
         * @return Chainable API.
         */
        public SelectorDSL endsWith(String value) {
            return attr(name, "$", value);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr and whose last value is suffixed by
         * "value".
         * </p>
         * 
         * @param value An attribute value.
         * @param sub A sub style.
         */
        public void endsWith(String value, Style sub) {
            endsWith(value).declare(sub);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr and whose value contains at least
         * one occurrence of string "value" as substring.
         * </p>
         * 
         * @param value An attribute value.
         * @return Chainable API.
         */
        public SelectorDSL contains(String value) {
            return attr(name, "*", value);
        }

        /**
         * <p>
         * Attribute selectors select an element using the presence of a given attribute or
         * attribute value.
         * </p>
         * <p>
         * Represents an element with an attribute name of attr and whose value contains at least
         * one occurrence of string "value" as substring.
         * </p>
         * 
         * @param value An attribute value.
         * @param sub A sub style.
         */
        public void contains(String value, Style sub) {
            contains(value).declare(sub);
        }

        /**
         * Write attribute selector pattern.
         * 
         * @param name An attribute name.
         * @param operator A operator.
         * @param value An attribute value.
         * @return Chainable API.
         */
        private SelectorDSL attr(String name, String operator, String value) {
            StringBuilder builder = new StringBuilder();
            builder.append("[").append(name);

            if (operator != null) {
                builder.append(operator).append("=\"").append(value).append('"');

                if (ignoreCase) {
                    builder.append(" i");
                }
            }
            builder.append("]");

            return parent.basic(builder.toString());
        }
    }
}