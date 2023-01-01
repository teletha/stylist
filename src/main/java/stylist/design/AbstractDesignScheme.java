/*
 * Copyright (C) 2023 The STYLIST Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.design;

import stylist.value.Color;
import stylist.value.FontSet;
import stylist.value.Numeric;

public abstract class AbstractDesignScheme extends DesignScheme {

    public Color primary;

    public Color secondary;

    public Color accent;

    public Color front;

    public Color link;

    public Color back;

    public Color surface;

    public FontSet base;

    public FontSet title;

    public FontSet condensed;

    public FontSet mono;

    public FontSet icon;

    public Numeric font;

    public Numeric line;

    public Numeric border;

    public Numeric radius;
}