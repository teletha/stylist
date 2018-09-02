/*
 * Copyright (C) 2017 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import kiss.I;
import stylist.StyleRule;

/**
 * @version 2018/09/01 21:17:27
 */
public final class Formatter {

    /** The format style. */
    private String beforeSelector = "";

    /** The format style. */
    private String afterSelector = "";

    /** The format style. */
    private String afterStartBrace = "";

    /** The format style. */
    private String beforeEndBrace = "";

    /** The format style. */
    private String afterEndBrace = "";

    /** The format style. */
    private String beforePropertyName = "";

    /** The format style. */
    private String afterPropertyName = "";

    /** The format style. */
    private String beforePropertyValue = "";

    /** The format style. */
    private String afterPropertyValue = "";

    /** The format style. */
    private String afterPropertyLine = "";

    /** The manager of post processors. */
    private final List<Consumer<Properties>> posts = new ArrayList();

    /**
     * Hide constructor.
     */
    private Formatter() {
    }

    /**
     * Decorate selector.
     * 
     * @param before A before decoration.
     * @param after An after decoration.
     * @return Chainable API.
     */
    public Formatter selector(String before, String after) {
        if (before != null) {
            this.beforeSelector = before;
        }

        if (after != null) {
            this.afterSelector = after;
        }
        return this;
    }

    /**
     * Decorate start brace.
     * 
     * @param after An after decoration.
     * @return Chainable API.
     */
    public Formatter startBrace(String after) {
        if (after != null) {
            this.afterStartBrace = after;
        }
        return this;
    }

    /**
     * Decorate end brace.
     * 
     * @param before A before decoration.
     * @param after An after decoration.
     * @return Chainable API.
     */
    public Formatter endBrace(String before, String after) {
        if (before != null) {
            this.beforeEndBrace = before;
        }

        if (after != null) {
            this.afterEndBrace = after;
        }
        return this;
    }

    /**
     * Decorate property name.
     * 
     * @param before A before decoration.
     * @param after An after decoration.
     * @return Chainable API.
     */
    public Formatter propertyName(String before, String after) {
        if (before != null) {
            this.beforePropertyName = before;
        }

        if (after != null) {
            this.afterPropertyName = after;
        }
        return this;
    }

    /**
     * Decorate property value.
     * 
     * @param before A before decoration.
     * @param after An after decoration.
     * @return Chainable API.
     */
    public Formatter propertyValue(String before, String after) {
        if (before != null) {
            this.beforePropertyValue = before;
        }

        if (after != null) {
            this.afterPropertyValue = after;
        }
        return this;
    }

    /**
     * Decorate start brace.
     * 
     * @param after An after decoration.
     * @return Chainable API.
     */
    public Formatter propertyLine(String after) {
        if (after != null) {
            this.afterPropertyLine = after;
        }
        return this;
    }

    /**
     * Add the post-processor.
     * 
     * @param processor
     * @return
     */
    public Formatter postProcessor(Consumer<Properties> processor) {
        if (processor != null) {
            posts.add(processor);
        }
        return this;
    }

    /**
     * Format the specified {@link StyleRule}.
     * 
     * @param rule A target to format.
     * @return A formatted text.
     */
    public String format(StyleRule rule) {
        StringBuilder builder = new StringBuilder();
        format(rule, builder);
        return builder.toString();
    }

    /**
     * Format the specified {@link StyleRule}.
     * 
     * @param rule A target to format.
     * @param appendable An output for the formatted text.
     */
    public void format(StyleRule rule, Appendable appendable) {
        try {
            for (Consumer<Properties> processor : posts) {
                processor.accept(rule.properties);
            }

            appendable.append(beforeSelector).append(rule.selector).append(afterSelector).append('{').append(afterStartBrace);

            for (int i = 0, size = rule.properties.size(); i < size; i++) {
                appendable.append(beforePropertyName)
                        .append(rule.properties.key(i))
                        .append(afterPropertyName)
                        .append(':')
                        .append(beforePropertyValue)
                        .append(rule.properties.value(i).toString())
                        .append(afterPropertyValue)
                        .append(';')
                        .append(afterPropertyLine);
            }
            appendable.append(beforeEndBrace).append('}').append(afterEndBrace);

            for (StyleRule child : rule.children) {
                format(child, appendable);
            }
        } catch (IOException e) {
            throw I.quiet(e);
        }
    }

    /**
     * Default human-readable formatter.
     * 
     * @return
     */
    public static final Formatter pretty() {
        return new Formatter().selector("", " ")
                .startBrace("\r\n")
                .propertyName("\t", "")
                .propertyValue(" ", "")
                .propertyLine("\r\n")
                .endBrace("", "\r\n\r\n");
    }

    /**
     * Default computer-readable formatter.
     * 
     * @return
     */
    public static final Formatter compact() {
        return new Formatter();
    }
}
