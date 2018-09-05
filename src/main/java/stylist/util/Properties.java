/*
 * Copyright (C) 2018 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import kiss.I;
import kiss.Variable;
import stylist.CSSValue;

/**
 * @version 2018/09/05 10:42:06
 */
public class Properties {

    /** The key list. */
    private final ArrayList<CSSValue> keys = new ArrayList();

    /** The value list. */
    private final ArrayList<CSSValue> values = new ArrayList();

    /**
     * <p>
     * Retrieve the first matched value by the specified key.
     * </p>
     * 
     * @param key A key.
     * @return A first matched value.
     */
    public Variable<CSSValue> get(String key) {
        int index = key(key);

        return index == -1 ? Variable.empty() : Variable.of(values.get(index));
    }

    /**
     * <p>
     * Retrieve all matched values by the specified key.
     * </p>
     * 
     * @param key A key.
     * @return A list of all matched values.
     */
    public List<CSSValue> getAll(String key) {
        List<CSSValue> matched = new ArrayList();

        for (int i = 0, length = keys.size(); i < length; i++) {
            if (keys.get(i).match(key)) {
                matched.add(values.get(i));
            }
        }
        return matched;
    }

    /**
     * <p>
     * Append the specified value by the specified key.
     * </p>
     * 
     * @param key A key.
     * @param value A value.
     * @return An updated {@link Properties}.
     */
    public Properties add(String key, String value) {
        return add(key, CSSValue.of(value));
    }

    /**
     * <p>
     * Append the specified value by the specified key.
     * </p>
     * 
     * @param key A key.
     * @param value A value.
     * @return An updated {@link Properties}.
     */
    public Properties add(String key, CSSValue value) {
        keys.add(CSSValue.of(key));
        values.add(value);

        return this;
    }

    /**
     * <p>
     * Update the specified value by the specified key.
     * </p>
     * 
     * @param key A key.
     * @param value A value to update.
     * @return An updated {@link Properties}.
     */
    public Properties set(String key, CSSValue value) {
        int index = key(key);

        if (index == -1) {
            keys.add(CSSValue.of(key));
            values.add(value);
        } else {
            values.set(index, value);
        }
        return this;
    }

    /**
     * <p>
     * Remove the first matched item by the specified key.
     * </p>
     * 
     * @param key A key to remove.
     * @return An updated {@link Properties}.
     */
    public Variable<CSSValue> remove(String key) {
        int index = key(key);

        if (index != -1) {
            keys.remove(index);
            return Variable.of(values.remove(index));
        } else {
            return Variable.empty();
        }
    }

    /**
     * <p>
     * Remove the first matched item by the specified keys.
     * </p>
     * 
     * @param key A key to remove.
     * @return An updated {@link Properties}.
     */
    public Variable<CSSValue>[] remove(String... keys) {
        Variable<CSSValue>[] values = new Variable[keys.length];

        for (int i = 0; i < keys.length; i++) {
            values[i] = remove(keys[i]);
        }
        return values;
    }

    /**
     * <p>
     * Remove the first matched item by the specified key.
     * </p>
     * 
     * @param key A key to remove.
     * @return An updated {@link Properties}.
     */
    public Properties removeAll(String key) {
        for (int i = keys.size() - 1; 0 <= i; i--) {
            if (keys.get(i).match(key)) {
                keys.remove(i);
                values.remove(i);
            }
        }
        return this;
    }

    /**
     * <p>
     * Remove all items.
     * </p>
     */
    public void clear() {
        keys.clear();
        values.clear();
    }

    /**
     * <p>
     * Compute the current item size.
     * </p>
     * 
     * @return A current item size.
     */
    public int size() {
        return keys.size();
    }

    /**
     * <p>
     * Retrieve the key by the specified index.
     * </p>
     * 
     * @param index A index to find.
     * @return A indexed key.
     */
    public CSSValue key(int index) {
        return keys.get(index);
    }

    /**
     * <p>
     * Retrieve the index by the specified key.
     * </p>
     * 
     * @param key A key to find.
     * @return A index for the specified key.
     */
    public int key(String key) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).match(key)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * <p>
     * Retrieve key list.
     * </p>
     * 
     * @return
     */
    public List<CSSValue> keys() {
        return keys;
    }

    /**
     * <p>
     * Mapping key.
     * </p>
     * 
     * @param mapper A key mapper.
     * @return Chainable API
     */
    public Properties keys(Function<String, String> mapper) {
        if (mapper != null) {
            for (int i = 0; i < keys.size(); i++) {
                keys.set(i, CSSValue.of(mapper.apply(keys.get(i).toString())));
            }
        }
        return this;
    }

    /**
     * <p>
     * Retrieve the value by the specified index.
     * </p>
     * 
     * @param index A index to find.
     * @return A indexed value.
     */
    public CSSValue value(int index) {
        return values.get(index);
    }

    /**
     * <p>
     * Retrieve the index by the specified value.
     * </p>
     * 
     * @param value A value to find.
     * @return A index for the specified value.
     */
    public int value(CSSValue value) {
        return values.indexOf(value);
    }

    /**
     * <p>
     * Mapping values.
     * </p>
     * 
     * @param mapper A value mapper.
     * @return Chainable API
     */
    public Properties value(String key, Map<String, String> mapper) {
        return value(key, e -> CSSValue.of(mapper.getOrDefault(e.toString(), e.toString())));
    }

    /**
     * <p>
     * Mapping values.
     * </p>
     * 
     * @param mapper A value mapper.
     * @return Chainable API
     */
    public Properties value(String key, Function<CSSValue, CSSValue> mapper) {
        if (mapper != null) {
            Variable<CSSValue> value = get(key);

            if (value.isPresent()) {
                set(key, value.map(mapper).v);
            }
        }
        return this;
    }

    /**
     * <p>
     * Retrieve value list.
     * </p>
     * 
     * @return
     */
    public List<CSSValue> values() {
        return values;
    }

    /**
     * <p>
     * Test whether the specified key and value are stored or not
     * </p>
     * 
     * @param key
     * @param value
     * @return
     */
    public boolean contains(String key, String value) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).match(key)) {
                return values.get(i).match(value);
            }
        }
        return false;
    }

    /**
     * Compact properties.
     * 
     * @param compactName
     * @param defaultValue
     * @param removers
     */
    public void compactTo(String compactName, String defaultValue, String... removers) {
        compactTo(compactName, CSSValue.of(defaultValue), removers);
    }

    /**
     * Compact properties.
     * 
     * @param compactName
     * @param defaultValue
     * @param removers
     */
    public void compactTo(String compactName, CSSValue defaultValue, String... removers) {
        if (removers != null && removers.length != 0) {
            int[] count = new int[] {0};

            CSSValue compacting = I.signal(removers).map(e -> {
                Variable<CSSValue> removed = remove(e);

                if (removed.isAbsent()) {
                    return defaultValue;
                } else {
                    count[0] = count[0] + 1;
                    return removed.v;
                }
            }).scanWith(CSSValue.EMPTY, (p, n) -> p.join(" ", n)).to().v;

            if (0 < count[0]) {
                set(compactName, compacting);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Properties[");

        for (int i = 0; i < keys.size(); i++) {
            builder.append(keys.get(i)).append("=").append(values.get(i));

            if (i != keys.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");

        return builder.toString();
    }
}
