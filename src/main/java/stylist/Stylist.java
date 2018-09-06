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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import kiss.I;
import stylist.util.Formatter;
import stylist.util.HierarchicalNaming;

/**
 * @version 2018/09/05 14:04:35
 */
public class Stylist {

    /** The naming strategy. */
    private static Function<Field, String> naming = new HierarchicalNaming("≫");

    /** The managed locations. */
    private static final Map<Location, String> managed = new ConcurrentHashMap();

    static {
        load();
    }

    /**
     * Load all styles eargerly.
     */
    private static void load() {
        for (Class domain : I.findAs(StyleDSL.class)) {
            for (Field field : domain.getDeclaredFields()) {
                try {
                    if (Location.class.isAssignableFrom(field.getType()) && Modifier.isStatic(field.getModifiers())) {
                        field.setAccessible(true);

                        Location located = (Location) field.get(null);

                        if (located != null) {
                            managed.put(located, naming.apply(field));
                        }
                    }
                } catch (Throwable e) {
                    throw I.quiet(e);
                }
            }
        }
    }

    /**
     * Set the class name strategy.
     * 
     * @param strategy
     */
    public static final void setNamingStrategy(Function<Field, String> strategy) {
        if (strategy != null) {
            naming = strategy;
            load();
        }
    }

    /**
     * Compute the location name.
     * 
     * @param location
     * @return
     */
    static String compute(Location location) {
        String name = managed.get(location);

        if (name != null) {
            return name;
        } else {
            return "AT" + location.hashCode();
        }
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

        I.signal(managed.keySet()).as(Style.class).map(StyleRule::create).sort(Comparator.naturalOrder()).to(e -> {
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
