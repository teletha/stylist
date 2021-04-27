/*
 * Copyright (C) 2021 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist;

import static stylist.StyleDSL.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import stylist.property.Background.BackgroundImage;
import stylist.value.Color;
import stylist.value.Numeric;

public interface Style extends Consumer, Serializable {

    /** The empty {@link Style} for reuse. */
    Style Empty = () -> {
    };

    /**
     * <p>
     * Define the style declaration.
     * </p>
     */
    void style();

    /**
     * <p>
     * Combine this {@link Style} and the specified {@link Style}.
     * </p>
     * 
     * @param style A style to combine.
     * @return A combined {@link Style}.
     */
    default Style with(Style style) {
        return MultipleStyle.of(this, style);
    }

    /**
     * Returns all members of the group to which this {@link Style} belongs. Usually this
     * {@link Style} only.
     * 
     * @return All members of the group to which this {@link Style} belongs.
     */
    default Collection<Style> group() {
        return List.of(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default void accept(Object context) {
        // do nothing
    }

    /**
     * Describe detailed information for this {@link Style}.
     * 
     * @return
     */
    default String detail() {
        return ValuedStyle.locate(this);
    }

    /**
     * Gets the CSS selector expression associated with this {@link Style}. The default
     * implementation returns a class selector with the obfuscated name.
     * 
     * @return CSS selector expression (For example .name, #id or element).
     */
    default String selector() {
        return Stylist.id(this);
    }

    /**
     * Gets class names that uniquely identifies this {@link Style} (returns class name [A] instead
     * of class selector [.A]). If the style is assigned a selector other than the class name, a
     * zero-length array will be returned.
     * 
     * @return A list of class names.
     */
    default String[] className() {
        String name = selector();

        return name.charAt(0) == '.' ? new String[] {name.substring(1)} : new String[0];
    }

    /**
     * Create named {@link Style}.
     * 
     * @param name A style name.
     * @param style A style definition.
     * @return
     */
    @SuppressWarnings("serial")
    static Style named(String name, Runnable style) {
        return new Style() {

            @Override
            public String selector() {
                return name;
            }

            @Override
            public void style() {
                style.run();
            }
        };
    }

    /**
     * Builtin {@link Style} for tooltip design.
     * 
     * @param attributeName A target attribute to show as tooltip.
     * @param showOnTop
     * @param theme
     * @return
     */
    static Style tooltip(String attributeName, boolean showOnTop, Theme theme, BackgroundImage... images) {
        return Style.named("[" + attributeName + "]", () -> {
            position.relative();
            cursor.help();

            $.before(() -> {
                tooltipPositioning(showOnTop);

                content.attr(attributeName);
                padding.horizontal(0.9, em).vertical(0.4, em);
                display.width.fitContent();
                border.radius(4, px);
                background.color(theme.front).image(images);
                font.color(theme.back).size(0.9, em);
                text.align.center().whiteSpace.preWrap().decoration.none();
                transform.translate(-50, percent, -5, px).scale(0.6);
                transition.duration(0.2, s).whenever();
            });

            $.hover().before(() -> {
                display.opacity(1).visibility.visible();
                transform.translate(-50, percent, -5, px).scale(1);
            });

            $.after(() -> {
                tooltipPositioning(showOnTop);

                content.text("");
                border.solid().width(5, px);
                border.top.color(theme.front);
                border.bottom.width(0, px).transparent();
                transition.duration(0, s);
                transform.origin.top().translateX(-50, percent).scaleY(0);
                font.color(Color.Transparent);
            });

            $.hover().after(() -> {
                display.opacity(1).visibility.visible();
                transform.translateX(-50, percent).scaleY(1);
                transition.duration(0.2, s).delay(0.4, s).whenever();
            });
        });
    }

    private static void tooltipPositioning(boolean showOnTop) {
        Numeric verticalPotion = Numeric.of(100, percent).plus(1, px);

        position.absolute().left(50, percent);
        if (showOnTop) {
            position.bottom(verticalPotion);
        } else {
            position.top(verticalPotion);
        }
        display.opacity(0).visibility.hidden();
    }
}