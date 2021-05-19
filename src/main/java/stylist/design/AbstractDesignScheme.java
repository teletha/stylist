/*
 * Copyright (C) 2021 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.design;

import stylist.value.Color;
import stylist.value.Font;
import stylist.value.Numeric;

public abstract class AbstractDesignScheme extends DesignScheme {

    public Color primary;

    public Color secondary;

    public Color accent;

    public Color front;

    public Color link;

    public Color back;

    public Color surface;

    public Font[] base = new Font[] {
            // Generic
            Font.SystemUI,
            // For Mac
            Font.of("-apple-system"), Font.of("BlinkMacSystemFont"), Font.of("Helvetica Neue"),
            // For Windows
            Font.of("Yu Gothic UI"), Font.of("Verdana"), Font.of("Meiryo"),
            // fallback
            Font.SansSerif};

    public Font title;

    public Font condensed;

    public Font[] mono = new Font[] {
            // For Mac
            Font.of("Menlo"),
            // For Windows
            Font.of("Consolas"),
            // fallback
            Font.Monospace};

    public Font icon;

    public Numeric font;

    public Numeric line;

    public Numeric border;

    public Numeric radius;
}
