/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import stylist.PropertyDefinition;

public class Content extends PropertyDefinition<Content> {

    /**
     * Text content.
     * 
     * @param value
     * @return
     */
    public Content text(String value) {
        return value(readValueAsString("") + "'" + value + "'");
    }

    /**
     * The pseudo-element is not generated.
     * 
     * @return
     */
    public Content none() {
        return value("none");
    }

    /**
     * Returns the value of the element's attribute X as a string. If there is no attribute X, an
     * empty string is returned. The case-sensitivity of attribute names depends on the document
     * language.
     * 
     * @param name
     * @return
     */
    public Content attr(String name) {
        return value(readValueAsString("") + "attr(" + name + ")");
    }

    /**
     * The counter-reset CSS property resets a CSS counter to a given value.
     * 
     * @param counters
     * @return
     */
    public Content counterReset(String... counters) {
        return value("counter-reset", String.join(" ", counters));
    }

    /**
     * The counter-reset CSS property resets a CSS counter to a given value.
     * 
     * @return
     */
    public Content counterReset(String counter, int value) {
        return value("counter-reset", counter + " " + value);
    }

    /**
     * The counter-reset CSS property resets a CSS counter to a given value.
     * 
     * @return
     */
    public Content counterReset(String counter1, int value1, String counter2, int value2) {
        return value("counter-reset", counter1 + " " + value1 + " " + counter2 + " " + value2);
    }

    /**
     * The counter-reset CSS property resets a CSS counter to a given value.
     * 
     * @return
     */
    public Content counterReset(String counter1, int value1, String counter2, int value2, String counter3, int value3) {
        return value("counter-reset", counter1 + " " + value1 + " " + counter2 + " " + value2 + " " + counter3 + " " + value3);
    }

    /**
     * The counter-increment CSS property increases or decreases the value of a CSS counter by a
     * given value.
     * 
     * @param counters
     * @return
     */
    public Content counterIncrement(String... counters) {
        return value("counter-increment", String.join(" ", counters));
    }

    /**
     * The counter-increment CSS property increases or decreases the value of a CSS counter by a
     * given value.
     * 
     * @param counter
     * @return
     */
    public Content counterIncrement(String counter, int value) {
        return value("counter-increment", counter + " " + value);
    }

    /**
     * The counter-increment CSS property increases or decreases the value of a CSS counter by a
     * given value.
     * 
     * @return
     */
    public Content counterIncrement(String counter1, int value1, String counter2, int value2) {
        return value("counter-increment", counter1 + " " + value1 + " " + counter2 + " " + value2);
    }

    /**
     * The counter-increment CSS property increases or decreases the value of a CSS counter by a
     * given value.
     * 
     * @return
     */
    public Content counterIncrement(String counter1, int value1, String counter2, int value2, String counter3, int value3) {
        return value("counter-increment", counter1 + " " + value1 + " " + counter2 + " " + value2 + " " + counter3 + " " + value3);
    }

    /**
     * The counter() function has two forms: 'counter(name)' or 'counter(name, style)'. The
     * generated text is the value of the innermost counter of the given name in scope at the given
     * pseudo-element.The counter is rendered in the specified style (decimal by default).
     * 
     * @param prefix
     * @param counter
     * @param postfix
     * @return
     */
    public Content counter(String prefix, String counter, String postfix) {
        return value("'" + prefix + "' counter(" + counter + ") '" + postfix + "'");
    }

    /**
     * These values are replaced by the appropriate string from the quotes property.
     * 
     * @return
     */
    public Content openQuote() {
        return value("open-quote");
    }

    /**
     * These values are replaced by the appropriate string from the quotes property.
     * 
     * @return
     */
    public Content closeQuote() {
        return value("close-quote");
    }
}