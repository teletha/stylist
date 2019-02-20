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

import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import kiss.I;
import stylist.util.Formatter;

/**
 * @version 2018/09/08 17:22:21
 */
public class Stylist {

    /** 1byte charset. */
    private static final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    /** The start index. */
    private static final int base = chars.length;

    /** The managed locations. */
    private static final Map<Location, String> id = new HashMap();

    /** The id manager. */
    private static final AtomicInteger counter = new AtomicInteger();

    static {
        for (Class domain : I.findAs(StyleDSL.class)) {
            styles(domain).forEach(Style::name);
        }
    }

    /**
     * Compute identifier for the specified {@link Location}.
     * 
     * @param location A target location.
     * @return An identifier.
     */
    static String id(Location location) {
        return id.computeIfAbsent(location, key -> {
            int id = counter.getAndIncrement();

            if (id == 0) {
                return String.valueOf(chars[0]);
            }

            StringBuilder builder = new StringBuilder();

            while (id != 0) {
                builder.append(chars[id % base]);
                id /= base;
            }
            return builder.toString();
        });
    }

    /**
     * Write out all styles to the specified path.
     * 
     * @param path
     * @return
     */
    public static final Path writeTo(String path) {
        return writeTo(Paths.get(path), Formatter.pretty());
    }

    /**
     * Write out all styles to the specified path.
     * 
     * @param path
     * @return
     */
    public static final Path writeTo(Path path, Formatter formatter) {
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            Files.writeString(path, write(formatter, I.signal(id.keySet()).as(Style.class).toList()));
        } catch (IOException e) {
            throw new IOError(e);
        }
        return path;
    }

    /**
     * Write out all styles in the specified style definitio with {@link Formatter#pretty()}.
     * 
     * @param definitions The style definitions.
     * @return A stylesheet representation.
     */
    public static String write(Class... definitions) {
        return write(null, definitions);
    }

    /**
     * Write out all styles in the specified style definition with your {@link Formatter}.
     * 
     * @param formatter A style formatter.
     * @param definitions The style definitions.
     * @return A stylesheet representation.
     */
    public static String write(Formatter formatter, Class... definitions) {
        Set<Style> styles = new HashSet();

        for (Class definition : definitions) {
            styles.addAll(styles(definition));
        }

        return write(formatter, styles.toArray(new Style[styles.size()]));
    }

    private static Set<Style> styles(Class definition) {
        Set<Style> styles = new HashSet();

        for (Field field : definition.getDeclaredFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);

                    if (Style.class.isAssignableFrom(field.getType())) {
                        Style located = (Style) field.get(null);

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
     * Write out the specified {@link Style} with {@link Formatter#pretty()}.
     * 
     * @param styles The style definitions.
     * @return A stylesheet representation.
     */
    public static String write(Style... styles) {
        return write(null, styles);
    }

    /**
     * Write out the specified {@link Style} with your {@link Formatter}.
     * 
     * @param formatter A style formatter.
     * @param styles The style definitions.
     * @return A stylesheet representation.
     */
    public static String write(Formatter formatter, Style... styles) {
        return write(formatter, List.of(styles));
    }

    /**
     * Write out the specified {@link Style} with your {@link Formatter}.
     * 
     * @param formatter A style formatter.
     * @param styles The style definitions.
     * @return A stylesheet representation.
     */
    private static String write(Formatter formatter, Iterable<Style> styles) {
        Formatter format = formatter == null ? Formatter.pretty() : formatter;

        StringBuilder builder = new StringBuilder();

        I.signal(styles).map(StyleRule::create).sort(Comparator.naturalOrder()).to(e -> {
            format.format(e, builder);
        });

        return builder.toString();
    }
}
