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

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import stylist.CSSValue;
import stylist.StyleRule;
import stylist.value.Color;

/**
 * @version 2018/09/08 23:23:36
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

    /** The format style. */
    private Function<Color, String> color = Color::toHSL;

    /** The format style. */
    private boolean comment = false;

    /** The format style. */
    private boolean showEmptyStyle = false;

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
     * Format {@link CSSValue}.
     * 
     * @param value
     * @return
     */
    public Function<Color, String> color() {
        return color;
    }

    /**
     * Add the color writer.
     * 
     * @param writer
     * @return
     */
    public Formatter color(Function<Color, String> writer) {
        if (writer != null) {
            this.color = writer;
        }
        return this;
    }

    /**
     * Accept comment.
     * 
     * @param comment
     * @return
     */
    public Formatter comment(boolean comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Show empty style.
     * 
     * @param showEmptyStyle
     * @return
     */
    public Formatter showEmptyStyle(boolean showEmptyStyle) {
        this.showEmptyStyle = showEmptyStyle;
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
        if (showEmptyStyle == false && rule.properties.size() == 0) {
            return;
        }

        try {
            for (Consumer<Properties> processor : posts) {
                processor.accept(rule.properties);
            }

            appendable.append(beforeSelector)
                    .append(comment(rule.description))
                    .append(rule.selector.toString())
                    .append(afterSelector)
                    .append('{')
                    .append(afterStartBrace);

            for (int i = 0, size = rule.properties.size(); i < size; i++) {
                appendable.append(beforePropertyName)
                        .append(rule.properties.name(i).toString())
                        .append(afterPropertyName)
                        .append(':')
                        .append(beforePropertyValue)
                        .append(rule.properties.value(i).format(this))
                        .append(afterPropertyValue)
                        .append(';')
                        .append(afterPropertyLine);
            }
            appendable.append(beforeEndBrace).append('}').append(afterEndBrace);

            for (StyleRule child : rule.children) {
                format(child, appendable);
            }
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    /**
     * Write comment.
     * 
     * @param message
     */
    private String comment(String message) {
        if (comment && message != null && message.length() != 0) {
            return "/* " + message + " */";
        } else {
            return "";
        }
    }

    /**
     * Default human-readable formatter.
     * 
     * @return
     */
    public static final Formatter pretty() {
        return new Formatter().comment(true)
                .selector("", " ")
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
