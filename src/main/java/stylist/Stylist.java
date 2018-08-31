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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import kiss.I;
import stylist.util.HierarchicalNaming;

/**
 * @version 2018/08/31 10:24:08
 */
public class Stylist {

    /** The naming strategy. */
    private static Function<Field, String> naming = new HierarchicalNaming("≫");

    /** The managed locations. */
    private static final Map<Location, String> managed = new ConcurrentHashMap();

    static {
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
        managed.entrySet().stream().forEach(System.out::println);
    }

    /**
     * Set the class name strategy.
     * 
     * @param strategy
     */
    public static final void setNamingStrategy(Function<Field, String> strategy) {
        if (strategy != null) {
            naming = strategy;
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
}
