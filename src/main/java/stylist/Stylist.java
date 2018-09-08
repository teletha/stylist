/*
 * Copyright (C) 2018 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist;

import static java.nio.charset.StandardCharsets.*;

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
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import kiss.I;
import stylist.util.Formatter;

/**
 * @version 2018/09/06 16:29:18
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
            for (Field field : domain.getDeclaredFields()) {
                try {
                    if (Modifier.isStatic(field.getModifiers())) {
                        field.setAccessible(true);

                        if (Location.class.isAssignableFrom(field.getType())) {

                            Location located = (Location) field.get(null);

                            if (located != null) {
                                located.name();
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
                                            style.of(constant).name();
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
        StringBuilder builder = new StringBuilder();

        I.signal(id.keySet()).as(Style.class).map(StyleRule::create).sort(Comparator.naturalOrder()).to(e -> {
            formatter.format(e, builder);
        });

        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            Files.write(path, builder.toString().getBytes(UTF_8));
        } catch (IOException e) {
            throw new IOError(e);
        }
        return path;
    }
}
