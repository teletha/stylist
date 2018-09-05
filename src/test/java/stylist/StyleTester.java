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

import java.lang.reflect.Field;
import java.util.function.Consumer;

import kiss.I;
import stylist.util.Properties;

/**
 * @version 2018/09/05 11:54:37
 */
public class StyleTester implements StyleDSL {

    /** The selector field. */
    private static final Field selectorField;

    static {
        I.load(StyleTester.class, false);

        try {
            selectorField = StyleRule.class.getField("selector");
            selectorField.setAccessible(true);
        } catch (Exception e) {
            throw I.quiet(e);
        }
    }

    /**
     * Helper method to wirte test style.
     * 
     * @param style A test style.
     * @return
     */
    protected final ValidatableStyle writeStyle(Style style, Consumer<Properties>... postProcessors) {
        // empty style sheet
        StyleRule rule = StyleRule.create("$", style, false);

        // search specified rule
        String name = "." + style.name();

        assert rule.selector.equals(name);

        for (Consumer<Properties> processor : postProcessors) {
            processor.accept(rule.properties);
        }

        return new ValidatableStyle(rule);
    }

    /**
     * Helper method to write test style with the specified selector.
     * 
     * @param selector
     * @param style
     * @return
     */
    protected final StyleRule writeStyle(String selector, Style style) {
        StyleRule root = StyleRule.create(style);

        try {
            update(root, (String) selectorField.get(root), selector);
        } catch (Exception e) {
            throw I.quiet(e);
        }
        return root;
    }

    /**
     * Force to set the selector.
     */
    private void update(StyleRule rule, String root, String replacer) {
        try {
            selectorField.set(rule, ((String) selectorField.get(rule)).replace(root, replacer));

            for (StyleRule child : rule.children) {
                update(child, root, replacer);
            }
        } catch (Exception e) {
            throw I.quiet(e);
        }
    }

    /**
     * @version 2018/08/30 22:22:37
     */
    public static class ValidatableStyle {

        /** The target to validate. */
        private final StyleRule rules;

        /**
         * @param rules
         */
        private ValidatableStyle(StyleRule rules) {
            this.rules = rules;
        }

        /**
         * Check the property which is specialized for {@link Vendor#Standard}.
         * 
         * @param name
         * @param value
         * @return
         */
        public boolean property(String name, String value) {
            return property(name, Vendor.Standard, name, value);
        }

        /**
         * Check the property which is specialized for {@link Vendor}.
         * 
         * @param name
         * @param vendor
         * @param vendoredValue
         * @return
         */
        public boolean property(String name, Vendor vendor, String vendoredValue) {
            return property(name, vendor, name, vendoredValue);
        }

        /**
         * Check the property which is specialized for {@link Vendor}.
         * 
         * @param name
         * @param vendor
         * @param vendoredName
         * @param vendoredValue
         * @return
         */
        public boolean property(String name, Vendor vendor, String vendoredName, String vendoredValue) {
            assert name != null;
            assert vendoredName != null;
            assert vendoredValue != null;

            int index = rules.properties.name(name);
            assert index != -1;

            CSSValue key = rules.properties.name(index);
            assert key.valueFor(vendor).equals(vendoredName);

            CSSValue value = rules.properties.value(index);
            assert value.valueFor(vendor).equals(vendoredValue);

            return true;
        }

        /**
         * <p>
         * Helper method to find {@link StyleRule} for the specified combinator or pseudo selector.
         * </p>
         * 
         * @param selector
         * @return
         */
        public ValidatableStyle sub(String selector) {
            String combinator = rules.selector + ":" + selector;
            String pseudo = rules.selector + "::" + selector;

            ValidatableStyle found = find(rules, combinator, pseudo);

            if (found != null) {
                return found;
            }
            throw new AssertionError("The rule[" + combinator + "] or [" + pseudo + "] is not found.");
        }

        /**
         * <p>
         * Helper method to find {@link StyleRule} for the specified combinator or pseudo selector.
         * </p>
         * 
         * @param rule A target {@link StyleRule}.
         * @param combinator A selector pattern.
         * @param pseudo A selector pattern.
         * @return A result.
         */
        private ValidatableStyle find(StyleRule rule, String combinator, String pseudo) {
            if (rule.selector.equals(combinator) || rule.selector.equals(pseudo)) {
                return new ValidatableStyle(rule);
            }

            for (int i = 0; i < rule.children.size(); i++) {
                ValidatableStyle found = find(rule.children.get(i), combinator, pseudo);

                if (found != null) {
                    return found;
                }
            }
            return null;
        }

        /**
         * <p>
         * Property has no prefix.
         * </p>
         */
        public boolean noPrefix() {
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return rules.toString();
        }
    }
}
