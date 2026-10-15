/*
 * Copyright (C) 2024 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import java.util.ArrayList;
import java.util.List;

import stylist.PropertyDefinition;
import stylist.value.CSSValue;
import stylist.value.Numeric;
import stylist.value.Shadow;
import stylist.value.Unit;

public class Filter extends PropertyDefinition<Filter> {

    private final String property;
    private final List<CSSValue> functions;

    public Filter() {
        this("filter");
    }

    protected Filter(String property) {
        this(property, new ArrayList());
    }

    private Filter(String property, List<CSSValue> functions) {
        super(property);
        this.property = property;
        this.functions = functions;
    }

    private Filter add(CSSValue function) {
        List<CSSValue> next = new ArrayList(functions);
        next.add(function);
        value(property, next, " ");

        return new Filter(property, next);
    }

    private Filter add(String name, CSSValue param) {
        return add(CSSValue.of(name + "(").join("", param).join("", CSSValue.of(")")));
    }

    private Filter add(String name, double param) {
        return add(name, CSSValue.of(param));
    }

    public Filter none() {
        value(property, "none");

        return new Filter(property);
    }

    public Filter blur(double size, Unit unit) {
        return blur(new Numeric(size, unit));
    }

    public Filter blur(Numeric size) {
        return add("blur", size);
    }

    public Filter brightness(double amount) {
        return add("brightness", amount);
    }

    public Filter contrast(double amount) {
        return add("contrast", amount);
    }

    public Filter dropShadow(Shadow shadow) {
        return add(CSSValue.of("drop-shadow(" + shadow + ")"));
    }

    public Filter grayscale(double amount) {
        return add("grayscale", amount);
    }

    public Filter hueRotate(double angle, Unit unit) {
        return hueRotate(new Numeric(angle, unit));
    }

    public Filter hueRotate(Numeric angle) {
        return add("hue-rotate", angle);
    }

    public Filter invert(double amount) {
        return add("invert", amount);
    }

    public Filter opacity(double amount) {
        return add("opacity", amount);
    }

    public Filter saturate(double amount) {
        return add("saturate", amount);
    }

    public Filter sepia(double amount) {
        return add("sepia", amount);
    }

    public static class Backdrop extends Filter {
        public Backdrop() {
            super("backdrop-filter");
        }
    }
}
