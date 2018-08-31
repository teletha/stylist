/*
 * Copyright (C) 2018 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.util;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.function.Function;

/**
 * @version 2018/08/31 14:18:04
 */
public class HierarchicalNaming implements Function<Field, String> {

    /** The separator. */
    private final String separator;

    /**
     * @param separator
     */
    public HierarchicalNaming(String separator) {
        this.separator = Objects.requireNonNull(separator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String apply(Field field) {
        return className(field.getDeclaringClass()) + field.getName();
    }

    /**
     * Compute name from class tree.
     * 
     * @param clazz
     * @return
     */
    private String className(Class clazz) {
        Class parent = clazz.getEnclosingClass();

        return (parent == null ? "" : className(parent)) + clazz.getSimpleName() + separator;
    }
}
