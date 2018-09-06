/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @version 2018/09/06 13:37:03
 */
public interface Location extends Serializable {

    /**
     * Compute location name.
     * 
     * @return A location name.
     */
    default String name() {
        return Stylist.id(this);
    }

    /**
     * Compute location name.
     * 
     * @return A location name.
     */
    default String[] names() {
        return new String[] {name()};
    }

    /**
     * Describe detailed information for this {@link Location}.
     * 
     * @return
     */
    default String detail() {
        try {
            Method serializer = getClass().getDeclaredMethod("writeReplace");
            serializer.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) serializer.invoke(this);
            Class clazz = Class.forName(lambda.getCapturingClass().replace('/', '.'));
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                if (Modifier.isStatic(field.getModifiers()) && Location.class.isAssignableFrom(field.getType())) {
                    if (field.get(null) == this) {
                        return className(clazz) + field.getName();
                    }
                }
            }
            return clazz.getSimpleName() + "$" + lambda.getImplMethodName();
        } catch (Throwable e) {
            // ignore
        }
        return "";
    }

    /**
     * Compute name from class tree.
     * 
     * @param clazz
     * @return
     */
    private String className(Class clazz) {
        Class parent = clazz.getEnclosingClass();

        return (parent == null ? "" : className(parent)) + clazz.getSimpleName() + "≫";
    }
}
