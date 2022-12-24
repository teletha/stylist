/*
 * Copyright (C) 2021 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist;

import static stylist.StyleDSL.*;

import stylist.property.Background.BackgroundImage;
import stylist.value.Color;
import stylist.value.Numeric;

/**
 * Common desing for the standard web browser environment.
 */
public final class Browsers {

    /**
     * Hide
     */
    private Browsers() {
    }

    /**
     * Builtin {@link Style} for tooltip design.
     * 
     * @param attributeName A target attribute to show as tooltip.
     * @param showOnTop
     * @return
     */
    public static Style tooltip(String attributeName, boolean showOnTop, Color front, Color back, BackgroundImage... images) {
        return Style.named("[" + attributeName + "]", () -> {
            position.relative();

            Numeric arrow = Numeric.of(10, px);
            Numeric gap = Numeric.of(5, px);

            $.before(() -> {
                position.absolute().left(50, percent);
                if (showOnTop) {
                    position.bottom(Numeric.of(100, percent).plus(gap).plus(arrow));
                } else {
                    position.top(Numeric.of(100, percent).subtract(gap).subtract(arrow));
                }

                pointerEvents.none();
                display.opacity(0).visibility.hidden().width.fitContent().zIndex(10);
                content.attr(attributeName);
                padding.horizontal(1.2, em).vertical(0.6, em);
                border.radius(4, px);
                background.color(back).image(images);
                font.color(front).size(0.9, em).letterSpacing(0.2, px).lineHeight(1.5);
                text.align.center().whiteSpace.nowrap().decoration.none();
                transform.translate(-50, percent, 0, px).scale(0.7);
                transition.duration(0.2, s).whenever();
            });

            $.hover().before(() -> {
                display.opacity(1).visibility.visible();
                transform.translate(-50, percent, 0, px).scale(1);
            });

            $.after(() -> {
                position.absolute().left(50, percent);
                if (showOnTop) {
                    position.bottom(Numeric.of(100, percent).plus(gap));
                } else {
                    position.top(Numeric.of(100, percent).subtract(gap));
                }

                pointerEvents.none();
                display.opacity(0).visibility.hidden().zIndex(10).height(arrow);
                content.text("");
                border.solid().width(5, px);
                border.top.color(back);
                border.bottom.width(0, px).transparent();
                transition.duration(0, s);
                transform.origin.top().translate(-50, percent, -5, px).scaleY(0);
                font.color(Color.Transparent);
            });

            $.hover().after(() -> {
                display.opacity(1).visibility.visible();
                transform.translateX(-50, percent).scaleY(1);
                transition.duration(0.2, s).delay(0.2, s).whenever();
            });
        });
    }

    /**
     * Desing the checkbox form.
     * 
     * @return
     */
    public static Style checkbox(Color pointedColor) {
        return Style.named("input[type=\"checkbox\"]", () -> {
            display.none();

            $.select("+ label", () -> {
                display.block();
                position.relative();
                padding.left(1.8, em);
                cursor.pointer();
                font.lineHeight(1, em);

                $.before(() -> {
                    content.text("");
                    display.block().width(1, em).height(1, em).opacity(0.6);
                    border.solid().width(1, px).color(Color.hsl(0, 0, 55));
                    position.absolute().top(0, px).left(3, px);
                    transition.duration(0.1, s).whenever();
                });

                $.hover().before(() -> {
                    border.color(pointedColor);
                    background.color(pointedColor.opacify(-0.25));
                });
            });

            $.checked().select("+ label", () -> {
                $.before(() -> {
                    display.width(0.5, em).opacity(1);
                    position.top(-0.2, em).left(0.5, em);
                    border.top.color(Color.Transparent);
                    border.left.color(Color.Transparent);
                    background.color(Color.Transparent);
                    transform.rotate(45, deg);
                });
            });
        });
    }

    /**
     * Desing the select form.
     * 
     * @return
     */
    public static Style selectbox(Color front, Color back) {
        return Style.named("select", () -> {
            display.width(100, percent).height(30, px);
            font.color(front);
            background.color(back);
            padding.vertical(4, px).horizontal(3, px);
            border.radius(3, px);
            transition.duration(0.5, s).whenever();
            outline.none();
            overflow.hidden().scrollbar.none();

            $.focus(() -> {
                display.height(120, px);
            });

            $.select("option", () -> {
                font.color(front);
                background.color(back);

                border.radius(3, px);
                margin.vertical(3, px);
                padding.vertical(4, px).horizontal(6, px);
            });
        });
    }
}
