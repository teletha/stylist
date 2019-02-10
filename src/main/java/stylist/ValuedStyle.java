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

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link Style} with the context value.
 * 
 * @version 2018/09/08 16:30:51
 */
@SuppressWarnings("serial")
final class ValuedStyle<V> implements Style {

    /** The cache repository. */
    static final Map<ValueStyle, Map<Object, Style>> cache = new HashMap();

    /** The original {@link ValueStyle}. */
    private final ValueStyle<V> base;

    /** The context value. */
    private final V value;

    /**
     * <p>
     * Create {@link Style} with the context value.
     * </p>
     * 
     * @param base A original style.
     * @param value A context value.
     */
    ValuedStyle(ValueStyle<V> base, V value) {
        this.value = value;
        this.base = base;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void style() {
        base.style(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Style> group() {
        return Collections.unmodifiableCollection(cache.get(base).values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String detail() {
        if (value instanceof Enum) {
            Enum e = (Enum) value;
            return locate(base) + "(" + e.getDeclaringClass().getSimpleName() + "#" + e.name() + ")";
        } else {
            return value.toString();
        }
    }

    /**
     * Compute locationa info.
     * 
     * @return
     */
    static String locate(Object o) {
        try {
            Method serializer = o.getClass().getDeclaredMethod("writeReplace");
            serializer.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) serializer.invoke(o);
            Class clazz = Class.forName(lambda.getCapturingClass().replace('/', '.'));
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                if (Modifier.isStatic(field.getModifiers()) && Serializable.class.isAssignableFrom(field.getType())) {
                    if (field.get(null) == o) {
                        return clazz.getCanonicalName() + "#" + field.getName();
                    }
                }
            }
            return clazz.getCanonicalName() + "#" + lambda.getImplMethodName();
        } catch (Throwable e) {
            // ignore
        }
        return String.valueOf(o.hashCode());
    }
}
