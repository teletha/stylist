/*
 * Copyright (C) 2021 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import org.junit.jupiter.api.Test;

import stylist.StyleTester;
import stylist.Vendor;
import stylist.value.Numeric;

/**
 * @version 2018/09/05 11:28:40
 */
class TransformTest extends StyleTester {

    @Test
    void rotate() {
        ValidatableStyle style = writeStyle(() -> {
            transform.rotate(10, deg);
        });

        assert style.property("transform", "rotate(10deg)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "rotate(10deg)");
    }

    @Test
    void rotateX() {
        ValidatableStyle style = writeStyle(() -> {
            transform.rotateX(10, deg);
        });

        assert style.property("transform", "rotateX(10deg)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "rotateX(10deg)");
    }

    @Test
    void rotateY() {
        ValidatableStyle style = writeStyle(() -> {
            transform.rotateY(10, deg);
        });

        assert style.property("transform", "rotateY(10deg)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "rotateY(10deg)");
    }

    @Test
    void rotateZ() {
        ValidatableStyle style = writeStyle(() -> {
            transform.rotateZ(10, deg);
        });

        assert style.property("transform", "rotateZ(10deg)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "rotateZ(10deg)");
    }

    @Test
    void translate() {
        ValidatableStyle style = writeStyle(() -> {
            transform.translate(10, px);
        });

        assert style.property("transform", "translate(10px,10px)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "translate(10px,10px)");
    }

    @Test
    void translateX() {
        ValidatableStyle style = writeStyle(() -> {
            transform.translateX(10, px);
        });

        assert style.property("transform", "translateX(10px)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "translateX(10px)");
    }

    @Test
    void translateY() {
        ValidatableStyle style = writeStyle(() -> {
            transform.translateY(10, px);
        });

        assert style.property("transform", "translateY(10px)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "translateY(10px)");
    }

    @Test
    void translateZ() {
        ValidatableStyle style = writeStyle(() -> {
            transform.translateZ(10, px);
        });

        assert style.property("transform", "translateZ(10px)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "translateZ(10px)");
    }

    @Test
    void scale1() {
        ValidatableStyle style = writeStyle(() -> {
            transform.scale(10);
        });

        assert style.property("transform", "scale(10)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "scale(10)");
    }

    @Test
    void scale2() {
        ValidatableStyle style = writeStyle(() -> {
            transform.scale(10, 5);
        });

        assert style.property("transform", "scale(10,5)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "scale(10,5)");
    }

    @Test
    void scaleX() {
        ValidatableStyle style = writeStyle(() -> {
            transform.scaleX(10);
        });

        assert style.property("transform", "scaleX(10)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "scaleX(10)");
    }

    @Test
    void scaleY() {
        ValidatableStyle style = writeStyle(() -> {
            transform.scaleY(10);
        });

        assert style.property("transform", "scaleY(10)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "scaleY(10)");
    }

    @Test
    void scaleZ() {
        ValidatableStyle style = writeStyle(() -> {
            transform.scaleZ(10);
        });

        assert style.property("transform", "scaleZ(10)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "scaleZ(10)");
    }

    @Test
    void skew() {
        ValidatableStyle style = writeStyle(() -> {
            transform.skew(10, deg);
        });

        assert style.property("transform", "skew(10deg)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "skew(10deg)");
    }

    @Test
    void skewX() {
        ValidatableStyle style = writeStyle(() -> {
            transform.skewX(10, deg);
        });

        assert style.property("transform", "skewX(10deg)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "skewX(10deg)");
    }

    @Test
    void skewY() {
        ValidatableStyle style = writeStyle(() -> {
            transform.skewY(10, deg);
        });

        assert style.property("transform", "skewY(10deg)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "skewY(10deg)");
    }

    @Test
    void multi() {
        ValidatableStyle style = writeStyle(() -> {
            transform.scale(1).skew(2, px);
        });
        assert style.property("transform", "scale(1) skew(2px)");
        assert style.property("transform", Vendor.Webkit, "-webkit-transform", "scale(1) skew(2px)");
    }

    @Test
    void calc() {
        ValidatableStyle style = writeStyle(() -> {
            transform.translate(new Numeric(1, px).add(new Numeric(2, em)));
        });

        assert style.property("transform", "translate(calc(1px + 2em),calc(1px + 2em))");
        assert style
                .property("transform", Vendor.Webkit, "-webkit-transform", "translate(-webkit-calc(1px + 2em),-webkit-calc(1px + 2em))");
    }

    @Test
    void originBottom() {
        ValidatableStyle style = writeStyle(() -> {
            transform.origin.bottom();
        });

        assert style.property("transform-origin", "bottom");
    }

    @Test
    void originCenter() {
        ValidatableStyle style = writeStyle(() -> {
            transform.origin.center();
        });

        assert style.property("transform-origin", "center");
    }

    @Test
    void originLeft() {
        ValidatableStyle style = writeStyle(() -> {
            transform.origin.left();
        });

        assert style.property("transform-origin", "left");
    }

    @Test
    void originRight() {
        ValidatableStyle style = writeStyle(() -> {
            transform.origin.right();
        });

        assert style.property("transform-origin", "right");
    }

    @Test
    void originTop() {
        ValidatableStyle style = writeStyle(() -> {
            transform.origin.top();
        });

        assert style.property("transform-origin", "top");
    }

    @Test
    void origin() {
        ValidatableStyle style = writeStyle(() -> {
            transform.origin.position(50, percent, 2, rem);
        });

        assert style.property("transform-origin", "50% 2rem");
    }
}