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

import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kiss.I;
import stylist.design.DesignScheme;
import stylist.design.DesignScheme.DefinedTheme;
import stylist.util.Properties;
import stylist.value.CSSValue;
import stylist.value.Color;

public final class Stylist {

    /** The URL of reset css. */
    public static final String ResetCSS = "https://cdn.jsdelivr.net/npm/modern-css-reset@1.4.0/dist/reset.min.css";

    /** The URL of normalize css. */
    public static final String NormalizeCSS = "https://cdn.jsdelivr.net/npm/ress@4.0.0/dist/ress.min.css";

    /** The animation manager. */
    static final Set<AnimationFrames> animations = ConcurrentHashMap.newKeySet();

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
    private Function<Color, String> color = Color::toString;

    /** The format style. */
    private boolean comment = false;

    /** The format style. */
    private boolean showEmptyStyle = false;

    /** The manager of post processors. */
    private final List<PostProcessor> posts = new ArrayList(I.find(PostProcessor.class));

    /** The imported stylesheets. */
    private final Set<String> imports = new ConcurrentSkipListSet();

    /** The target styles. */
    private final Set<Style> styles = new HashSet();

    /** The queried styles. */
    private Map<Query, StringBuilder> queried = new ConcurrentHashMap();

    /** The user scheme. */
    private DesignScheme scheme;

    /**
     * Hide constructor.
     */
    private Stylist() {
    }

    /**
     * Decorate selector.
     * 
     * @param before A before decoration.
     * @param after An after decoration.
     * @return Chainable API.
     */
    public Stylist selector(String before, String after) {
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
    public Stylist startBrace(String after) {
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
    public Stylist endBrace(String before, String after) {
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
    public Stylist propertyName(String before, String after) {
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
    public Stylist propertyValue(String before, String after) {
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
    public Stylist propertyLine(String after) {
        if (after != null) {
            this.afterPropertyLine = after;
        }
        return this;
    }

    /**
     * Format {@link Color} to normalize color code.
     * 
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
    public Stylist color(Function<Color, String> writer) {
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
    public Stylist comment(boolean comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Show empty style.
     * 
     * @param showEmptyStyle
     * @return
     */
    public Stylist showEmptyStyle(boolean showEmptyStyle) {
        this.showEmptyStyle = showEmptyStyle;
        return this;
    }

    /**
     * Add the post-processor.
     * 
     * @param processor
     * @return
     */
    public Stylist postProcessor(PostProcessor processor) {
        if (processor != null) {
            posts.add(processor);
        }
        return this;
    }

    /**
     * Apply the post-processor which renames physical property name to logical property name.
     * 
     * @return
     */
    public Stylist forceLogicalProperty() {
        postProcessor((selector, properties) -> {
            properties.rename(p -> {
                if (p.match("width")) {
                    return CSSValue.of("inline-size");
                } else if (p.match("max-width")) {
                    return CSSValue.of("max-inline-size");
                } else if (p.match("min-width")) {
                    return CSSValue.of("max-inline-size");
                } else if (p.match("height")) {
                    return CSSValue.of("block-size");
                } else if (p.match("max-height")) {
                    return CSSValue.of("max-block-size");
                } else if (p.match("min-height")) {
                    return CSSValue.of("min-block-size");
                } else if (p.match("padding-left")) {
                    return CSSValue.of("padding-inline-start");
                } else if (p.match("padding-right")) {
                    return CSSValue.of("padding-inline-end");
                } else if (p.match("padding-top")) {
                    return CSSValue.of("padding-block-start");
                } else if (p.match("padding-bottom")) {
                    return CSSValue.of("padding-block-end");
                } else if (p.match("margin-left")) {
                    return CSSValue.of("margin-inline-start");
                } else if (p.match("margin-right")) {
                    return CSSValue.of("margin-inline-end");
                } else if (p.match("margin-top")) {
                    return CSSValue.of("margin-block-start");
                } else if (p.match("margin-bottom")) {
                    return CSSValue.of("margin-block-end");
                } else {
                    return p;
                }
            });
        });
        return this;
    }

    /**
     * Import reset stylesheet.
     * 
     * @return Chainable API.
     */
    public Stylist importResetStyle() {
        return importStyle(ResetCSS);
    }

    /**
     * Import normalize stylesheet.
     * 
     * @return Chainable API.
     */
    public Stylist importNormalizeStyle() {
        return importStyle(NormalizeCSS);
    }

    /**
     * Import external stylesheet.
     * 
     * @param uri A target stylesheet uri to import.
     * @return Chainable API.
     */
    public Stylist importStyle(String uri) {
        if (uri != null && uri.length() != 0) {
            imports.add(uri);
        }
        return this;
    }

    /**
     * Specify the design scheme.
     * 
     * @param scheme
     * @return
     */
    public final Stylist scheme(Class<? extends DesignScheme> scheme) {
        if (scheme != null) {
            this.scheme = I.make(scheme);
        }
        return this;
    }

    /**
     * Specify the class containing the style definition to be converted.
     * 
     * @param definitions The style definitions.
     * @return Chainable API.
     */
    public final Stylist styles(List<Class<StyleDeclarable>> definitions) {
        I.signal(definitions).flatIterable(Stylist::collectStyles).toCollection(styles);
        return this;
    }

    /**
     * Specify the class containing the style definition to be converted.
     * 
     * @param definitions The style definitions.
     * @return Chainable API.
     */
    public final Stylist styles(Class... definitions) {
        I.signal(definitions).flatIterable(Stylist::collectStyles).toCollection(styles);
        return this;
    }

    /**
     * Specify the class containing the style definition to be converted.
     * 
     * @param definitions The style definitions.
     * @return Chainable API.
     */
    public final Stylist styles(StyleDeclarable... definitions) {
        I.signal(definitions).flatIterable(Stylist::collectStyles).toCollection(styles);
        return this;
    }

    /**
     * Specify the style definition to be converted.
     * 
     * @param styles The style definitions.
     * @return Chainable API.
     */
    public final Stylist styles(Style... styles) {
        I.signal(styles).toCollection(this.styles);
        return this;
    }

    /**
     * Write out all managed styles.
     * 
     * @return
     */
    public final String format() {
        return format(styles.isEmpty() ? StyleManager.names.keySet() : styles);
    }

    /**
     * Write out all managed styles.
     * 
     * @param output A style output buffer.
     * @return
     */
    public final Path formatTo(String output) {
        return formatTo(Path.of(output));
    }

    /**
     * Write out all managed styles.
     * 
     * @param output A style output buffer.
     * @return
     */
    public final Path formatTo(Path output) {
        try {
            if (Files.notExists(output)) {
                Files.createDirectories(output.getParent());
            }
            formatTo(Files.newBufferedWriter(output, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw I.quiet(e);
        }
        return output;
    }

    /**
     * Write out all managed styles.
     * 
     * @param output A style output buffer.
     */
    public final void formatTo(Appendable output) {
        try {
            output.append(format());
        } catch (IOException e) {
            throw I.quiet(e);
        } finally {
            I.quiet(output);
        }
    }

    /**
     * Write out the specified {@link Style}.
     * 
     * @param styles The style definitions.
     * @return A stylesheet representation.
     */
    private String format(Iterable<Style> styles) {
        StringBuilder builder = new StringBuilder();

        I.signal(styles).map(Stylist::create).sort(Comparator.naturalOrder()).to(e -> {
            format(e, builder);
        });

        for (Entry<Query, StringBuilder> entry : queried.entrySet()) {
            builder.append(entry.getKey()).append(afterSelector).append('{').append(afterStartBrace);
            builder.append(entry.getValue());
            builder.append(beforeEndBrace).append('}').append(afterEndBrace);
        }

        imports.addAll(externals);
        StringBuilder addition = new StringBuilder();
        for (String external : imports) {
            addition.append("@import url(\"").append(external).append("\");").append(afterPropertyLine);
        }

        for (AnimationFrames frames : animations) {
            format(frames, addition);
        }

        if (scheme != null) {
            for (DefinedTheme theme : scheme.themes) {
                format(scheme, theme.isMain, theme, addition);
            }
        }

        builder.insert(0, addition);

        return builder.toString();
    }

    /**
     * Format the specified {@link AnimationFrames}.
     * 
     * @param theme
     * @param appendable
     */
    final void format(DesignScheme scheme, boolean isDefault, DefinedTheme theme, Appendable appendable) {
        try {
            String selector = isDefault ? scheme.rootClass() : "." + theme.name + scheme.rootClass();

            appendable.append(selector).append(afterSelector).append('{').append(afterStartBrace);
            for (int i = 0; i < theme.variables.size(); i++) {
                CSSValue value = theme.variables.value(i);

                appendable.append(beforePropertyName)
                        .append(scheme.variablePrefix())
                        .append(theme.variables.name(i).toString())
                        .append(afterPropertyName)
                        .append(':')
                        .append(beforePropertyValue)
                        .append(value instanceof Color c ? color().apply(c) : value.toString())
                        .append(afterPropertyValue)
                        .append(';')
                        .append(afterPropertyLine);
            }
            appendable.append(beforeEndBrace).append('}').append(afterEndBrace);
        } catch (IOException e) {
            throw I.quiet(e);
        }
    }

    /**
     * Format the specified {@link AnimationFrames}.
     * 
     * @param frames
     * @param appendable
     */
    final void format(AnimationFrames frames, Appendable appendable) {
        try {
            appendable.append("@keyframes ").append(frames.name).append(afterSelector).append('{').append(afterStartBrace);
            for (int i = 0; i < frames.progressions.size(); i++) {
                String progress = IntStream.of(frames.progressions.get(i)).mapToObj(e -> e + "%").collect(Collectors.joining(","));
                Properties properties = frames.styles.get(i).properties;

                appendable.append(progress).append(afterSelector).append('{').append(afterStartBrace);
                for (int j = 0; j < properties.size(); j++) {
                    appendable.append(beforePropertyName)
                            .append(properties.name(j).toString())
                            .append(afterPropertyName)
                            .append(':')
                            .append(beforePropertyValue)
                            .append(properties.value(j).format(this))
                            .append(afterPropertyValue)
                            .append(';')
                            .append(afterPropertyLine);
                }
                appendable.append(beforeEndBrace).append('}').append(afterEndBrace);
            }
            appendable.append(beforeEndBrace).append('}').append(afterEndBrace);
        } catch (IOException e) {
            throw I.quiet(e);
        }
    }

    /**
     * Format the specified {@link StyleRule}.
     * 
     * @param rule A target to format.
     * @return A formatted text.
     */
    final String format(StyleRule rule) {
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
    final void format(StyleRule rule, Appendable appendable) {
        if (rule.query.isPresent()) {
            appendable = queried.computeIfAbsent(rule.query.v, query -> new StringBuilder());
        }

        try {
            for (PostProcessor processor : posts) {
                processor.accept(rule.selector.toString(), rule.properties);
            }

            if (showEmptyStyle == false && rule.properties.size() == 0) {
                for (StyleRule child : rule.children) {
                    format(child, appendable);
                }
                return;
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
    public static final Stylist pretty() {
        return new Stylist().comment(true)
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
    public static final Stylist compact() {
        return new Stylist();
    }

    /** The external stylesheets. */
    private static final Set<String> externals = new HashSet();

    static {
        I.load(Stylist.class);

        for (Class domain : I.findAs(StyleDeclarable.class)) {
            collectStyles(domain).forEach(Style::selector);
        }
    }

    /**
     * Collect all styles in the specified style definitions.
     * 
     * @param definition The style definitions
     * @return
     */
    private static List<Style> collectStyles(Class definition) {
        List<Style> styles = new ArrayList();

        for (Field field : definition.getFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);

                    if (Style.class.isAssignableFrom(field.getType())) {
                        Style located = (Style) field.get(null);

                        if (located != null) {
                            styles.addAll(located.group());
                        }
                    } else if (ValueStyle.class.isAssignableFrom(field.getType())) {
                        Type type = field.getGenericType();

                        if (type instanceof ParameterizedType) {
                            ParameterizedType parameterized = (ParameterizedType) type;
                            Type[] args = parameterized.getActualTypeArguments();

                            if (args.length == 1 && args[0] instanceof Class) {
                                Class param = (Class) args[0];

                                if (param.isEnum()) {
                                    ValueStyle style = (ValueStyle) field.get(null);

                                    for (Object constant : param.getEnumConstants()) {
                                        styles.add(style.of(constant));
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                throw I.quiet(e);
            }
        }
        return styles;
    }

    /**
     * Collect all styles in the specified style definitions.
     * 
     * @param definition The style definitions
     * @return
     */
    private static List<Style> collectStyles(StyleDeclarable definition) {
        List<Style> styles = new ArrayList();

        for (Field field : definition.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);

                if (Style.class.isAssignableFrom(field.getType())) {
                    Style located = (Style) field.get(definition);

                    if (located != null) {
                        styles.add(located);
                    }
                } else if (ValueStyle.class.isAssignableFrom(field.getType())) {
                    Type type = field.getGenericType();

                    if (type instanceof ParameterizedType) {
                        ParameterizedType parameterized = (ParameterizedType) type;
                        Type[] args = parameterized.getActualTypeArguments();

                        if (args.length == 1 && args[0] instanceof Class) {
                            Class param = (Class) args[0];

                            if (param.isEnum()) {
                                ValueStyle style = (ValueStyle) field.get(definition);

                                for (Object constant : param.getEnumConstants()) {
                                    styles.add(style.of(constant));
                                }
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                throw I.quiet(e);
            }
        }
        return styles;
    }

    /**
     * Register the external stylesheet to import.
     * 
     * @param uri A target uri.
     */
    public static void useExternalStylesheet(String uri) {
        if (uri != null && uri.startsWith("http")) {
            externals.add(uri);
        }
    }

    /**
     * Create {@link StyleRule} from the specified {@link Style}.
     * 
     * @param style
     * @return A create new {@link StyleRule}.
     */
    static StyleRule create(Style style) {
        return create(style, null);
    }

    /**
     * Create {@link StyleRule} from the specified {@link Style}.
     * 
     * @param style A style description.
     * @return A create new {@link StyleRule}.
     */
    static synchronized StyleRule create(Style style, SelectorDSL selector) {
        if (selector == null) {
            selector = SelectorDSL.create(null);
        }

        // store parent rule
        StyleRule parent = PropertyDefinition.rule;
        String description;

        if (parent == null) {
            selector.selector = style.selector();
            description = style.detail();
        } else {
            selector.replace(parent.internal);
            description = parent.description;
        }

        // create child rule
        StyleRule child = new StyleRule(selector, description);

        // swap context rule and execute it
        PropertyDefinition.rule = child;
        style.style();
        PropertyDefinition.rule = parent;

        if (parent != null) {
            parent.children.add(child);
        }

        // API definition
        return child;
    }
}