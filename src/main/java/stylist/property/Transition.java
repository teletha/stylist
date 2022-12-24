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

import static stylist.value.Unit.*;

import java.util.List;

import stylist.CSSValue;
import stylist.PropertyDefinition;
import stylist.SelectorDSL;
import stylist.value.Numeric;
import stylist.value.Unit;

public class Transition extends PropertyDefinition<Transition> {

    /** The transition related property. */
    private Numeric duration;

    /** The transition related property. */
    private Numeric delay;

    /** The transition related property. */
    private String timing;

    /**
     * Reset user setting.
     */
    private void reset() {
        duration = new Numeric(0, s);
        delay = new Numeric(0, s);
        timing = "ease";
    }

    /**
     * 
     */
    public Transition() {
        reset();
    }

    /**
     * <p>
     * The transition-duration CSS property specifies the number of seconds or milliseconds a
     * transition animation should take to complete. By default, the value is 0s, meaning that no
     * animation will occur.
     * </p>
     * 
     * @param time
     * @param unit
     * @return
     */
    public Transition duration(double time, Unit unit) {
        duration = new Numeric(time, unit);

        return this;
    }

    /**
     * <p>
     * The transition-delay CSS property specifies the amount of time to wait between a change being
     * requested to a property that is to be transitioned and the start of the transition effect.
     * </p>
     * 
     * @param time
     * @param unit
     * @return
     */
    public Transition delay(double time, Unit unit) {
        delay = new Numeric(time, unit);

        return this;
    }

    /**
     * <p>
     * This keyword represents the timing function cubic-bezier(0.0, 0.0, 1.0, 1.0). Using this
     * timing function, the animation goes from its initial state to its final one regularly, with a
     * constant speed.
     * </p>
     * 
     * @return
     */
    public Transition linear() {
        timing = "liner";
        return this;
    }

    /**
     * <p>
     * This keyword represents the timing function cubic-bezier(0.25, 0.1, 0.25, 1.0). This function
     * is similar to ease-in-out, though it accelerates more sharply at the beginning and the
     * acceleration already starts to slow down near the middle of it.
     * </p>
     * 
     * @return
     */
    public Transition ease() {
        timing = "ease";
        return this;
    }

    /**
     * <p>
     * This keyword represents the timing function cubic-bezier(0.42, 0.0, 1.0, 1.0). The animation
     * begins slowly, then progressively accelerates until the final state is reached and the
     * animation stops abruptly.
     * </p>
     * 
     * @return
     */
    public Transition easeIn() {
        timing = "ease-in";
        return this;
    }

    /**
     * <p>
     * This keyword represents the timing function cubic-bezier(0.42, 0.0, 0.58, 1.0). With this
     * timing function, the animation starts slowly, accelerates than slows down when approaching to
     * its final state. At the begin, it behaves similarly to the ease-in function, at the end, it
     * is similar to the ease-out function.
     * </p>
     * 
     * @return
     */
    public Transition easeInOut() {
        timing = "ease-in-out";
        return this;
    }

    /**
     * This keyword represents the timing function cubic-bezier(0.0, 0.0, 0.58, 1.0). The animation
     * starts quickly then slow progressively down when approaching to its final state.
     * 
     * @return
     */
    public Transition easeOut() {
        timing = "ease-out";
        return this;
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeInSine() {
        return cubicBezier(0.12, 0, 0.39, 0);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeOutSine() {
        return cubicBezier(0.61, 1, 0.88, 1);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeInOutSine() {
        return cubicBezier(0.37, 0, 0.63, 1);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeInCubic() {
        return cubicBezier(0.32, 0, 0.67, 0);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeOutCubic() {
        return cubicBezier(0.33, 1, 0.68, 1);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeInOutCubic() {
        return cubicBezier(0.65, 0, 0.35, 1);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeInBack() {
        return cubicBezier(0.36, 0, 0.66, -0.56);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeOutBack() {
        return cubicBezier(0.34, 1.56, 0.64, 1);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeInOutBack() {
        return cubicBezier(0.68, -0.6, 0.32, 1.6);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeInQuint() {
        return cubicBezier(0.64, 0, 0.78, 0);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeOutQuint() {
        return cubicBezier(0.22, 1, 0.36, 1);
    }

    /**
     * Additional keyword.
     * 
     * @return
     */
    public Transition easeInOutQuint() {
        return cubicBezier(0.83, 0, 0.17, 1);
    }

    /**
     * <p>
     * This keyword represents the timing function steps(1, start). Using this timing function, the
     * animation jumps immediately to the end state and stay in that position until the end of the
     * animation.
     * </p>
     * 
     * @return
     */
    public Transition stepStart() {
        timing = "step-start";
        return this;
    }

    /**
     * <p>
     * This keyword represents the timing function steps(1, end). Using this timing function, the
     * animation stays in its initial state until the end, where it directly jumps to its final
     * position.
     * </p>
     * 
     * @return
     */
    public Transition stepEnd() {
        timing = "step-end";
        return this;
    }

    /**
     * <p>
     * This keyword represents the timing function steps(1, end). Using this timing function, the
     * animation stays in its initial state until the end, where it directly jumps to its final
     * position.
     * </p>
     * 
     * @return
     */
    public Transition cubicBezier(double a, double b, double c, double d) {
        timing = "cubic-bezier(" + a + "," + b + "," + c + "," + d + ")";
        return this;
    }

    /**
     * <p>
     * Declare change effect.
     * </p>
     */
    public SelectorDSL when() {
        return SelectorDSL.create(() -> {
            List<CSSValue> names = readPropertyNames();

            value("transition-property", join(names, v -> v));
            value("transition-duration", join(names, v -> duration));
            value("transition-delay", join(names, v -> delay));
            value("transition-timing-function", join(names, v -> timing));

            reset();
        });
    }

    /**
     * <p>
     * Declare change effect.
     * </p>
     */
    public void whenever() {
        value("transition-property", "all");
        value("transition-duration", duration);
        value("transition-delay", delay);
        value("transition-timing-function", timing);

        reset();
    }
}