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

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import kiss.I;

/**
 * @version 2018/08/31 10:24:08
 */
class LocationNamingStrategy {

    /** The managed locations. */
    private static final Map<Location, String> managed = new ConcurrentHashMap();

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
        }

        Class clazz = location.getClass();

        if (clazz.isSynthetic()) {
            // for lambda
            try {
                Method serializer = clazz.getDeclaredMethod("writeReplace");
                serializer.setAccessible(true);
                SerializedLambda serialized = (SerializedLambda) serializer.invoke(location);
                Class<?> declaredClass = Class.forName(serialized.getImplClass().replaceAll("/", "."));

                for (Field field : declaredClass.getDeclaredFields()) {
                    if (Location.class.isAssignableFrom(field.getType()) && Modifier.isStatic(field.getModifiers())) {
                        field.setAccessible(true);

                        Location located = (Location) field.get(null);

                        if (field.isAnnotationPresent(RetainLocation.class) || declaredClass.isAnnotationPresent(RetainLocation.class)) {
                            name = className(declaredClass) + field.getName();
                        } else {
                            name = hash(located);
                        }
                        managed.put(located, name);
                    }
                }
                return managed.computeIfAbsent(location, LocationNamingStrategy::hash);
            } catch (Throwable e) {
                throw I.quiet(e);
            }
        } else {
            // for class
            if (clazz.isAnnotationPresent(RetainLocation.class)) {
                name = clazz.getSimpleName();
            } else {
                name = hash(location);
            }
            managed.put(location, name);

            return name;
        }
    }

    /**
     * Generic hash name.
     * 
     * @param location
     * @return
     */
    private static String hash(Location location) {
        return "AT" + location.hashCode();
    }

    /**
     * Compute name from class tree.
     * 
     * @param clazz
     * @return
     */
    private static String className(Class clazz) {
        Class parent = clazz.getEnclosingClass();

        return (parent == null ? "" : className(parent)) + clazz.getSimpleName() + "≫";
    }
}
