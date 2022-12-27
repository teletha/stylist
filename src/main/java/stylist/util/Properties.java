/*
 * Copyright (C) 2021 stylist Development Team
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

import kiss.Variable;
import stylist.value.CSSValue;

public final class Properties {

    /** The key list. */
    private final ArrayList<CSSValue> names = new ArrayList();

    /** The value list. */
    private final ArrayList<CSSValue> values = new ArrayList();

    /**
     * Test property value literally.
     * 
     * @param name A property name.
     * @param value A property value.
     * @return A result.
     */
    public boolean is(String name, String value) {
        return get(name).is(v -> v.match(value));
    }

    /**
     * Get the propety value of the specified name.
     * 
     * @param name A property name.
     * @return The matched value.
     */
    public Variable<CSSValue> get(String name) {
        int index = name(name);

        return index == -1 ? Variable.empty() : Variable.of(values.get(index));
    }

    /**
     * Set the property.
     * 
     * @param name A property name.
     * @param value A property value..
     * @return An updated {@link Properties}.
     */
    public Properties set(String name, String value) {
        return set(CSSValue.of(name), CSSValue.of(value));
    }

    /**
     * Set the property.
     * 
     * @param name A property name.
     * @param value A property value..
     * @return An updated {@link Properties}.
     */
    public Properties set(String name, CSSValue value) {
        return set(CSSValue.of(name), value);
    }

    /**
     * Set the property.
     * 
     * @param name A property name.
     * @param value A property value..
     * @return An updated {@link Properties}.
     */
    public Properties set(CSSValue name, CSSValue value) {
        int index = name(name);

        if (index == -1) {
            names.add(CSSValue.of(name));
            values.add(value);
        } else {
            values.set(index, value);
        }
        return this;
    }

    /**
     * <p>
     * Remove the matched property by the specified name.
     * </p>
     * 
     * @param name A property name to remove.
     * @return An updated {@link Properties}.
     */
    public Variable<CSSValue> remove(String name) {
        int index = name(name);

        if (index != -1) {
            names.remove(index);
            return Variable.of(values.remove(index));
        } else {
            return Variable.empty();
        }
    }

    /**
     * <p>
     * Get the property size.
     * </p>
     * 
     * @return A number of properties.
     */
    public int size() {
        return names.size();
    }

    /**
     * Get the property name by index.
     * 
     * @param index A property name index.
     * @return A property name.
     */
    public CSSValue name(int index) {
        return names.get(index);
    }

    /**
     * Get the index of property name.
     * 
     * @param key A property name to find.
     * @return A index for the specified property name.
     */
    public int name(String key) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).match(key)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get the index of property name.
     * 
     * @param name A property name to find.
     * @return A index for the specified property name.
     */
    public int name(CSSValue name) {
        return name(name.toString());
    }

    /**
     * Get all property names.
     * 
     * @return A list of property names.
     */
    public List<CSSValue> names() {
        return names;
    }

    /**
     * Rename property names.
     * 
     * @param renamer A processor to rename property names.
     * @return Chainable API
     */
    public Properties rename(Function<CSSValue, CSSValue> renamer) {
        if (renamer != null) {
            for (int i = 0; i < names.size(); i++) {
                names.set(i, renamer.apply(names.get(i)));
            }
        }
        return this;
    }

    /**
     * <p>
     * Get the value by the specified index.
     * </p>
     * 
     * @param index A index to find.
     * @return A indexed value.
     */
    public CSSValue value(int index) {
        return values.get(index);
    }

    /**
     * Revalue property value of the specified proeprty name.
     * 
     * @param name A target property name to revalue.
     * @param mapper A processor to revalue property values.
     * @return Chainable API
     */
    public Properties revalue(String name, Map<String, String> mapper) {
        return revalue(name, propertyValue -> {
            String value = propertyValue.toString();
            value = mapper.getOrDefault(value, value);

            return CSSValue.of(value, propertyValue.vendors());
        });
    }

    /**
     * Revalue property value of the specified proeprty name.
     * 
     * @param name A target property name to revalue.
     * @param mapper A processor to revalue property values.
     * @return Chainable API
     */
    public Properties revalue(String name, Function<CSSValue, CSSValue> mapper) {
        if (mapper != null) {
            Variable<CSSValue> value = get(name);

            if (value.isPresent()) {
                set(name, mapper.apply(value.get()));
            }
        }
        return this;
    }

    /**
     * Get all property values.
     * 
     * @return A list of property values.
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
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).match(key)) {
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
            CSSValue compacting = CSSValue.EMPTY;

            for (String remover : removers) {
                Variable<CSSValue> removed = remove(remover);

                if (removed.isPresent() == false) {
                    compacting = compacting.join(defaultValue);
                } else {
                    count[0] = count[0] + 1;
                    compacting = compacting.join(removed.get());
                }
            }

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

        for (int i = 0; i < names.size(); i++) {
            builder.append(names.get(i)).append("=").append(values.get(i));

            if (i != names.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");

        return builder.toString();
    }
}