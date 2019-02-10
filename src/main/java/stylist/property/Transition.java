/*
 * Copyright (C) 2019 stylist Development Team
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          https://opensource.org/licenses/MIT
 */
package stylist.property;

import static stylist.value.Unit.*;

import stylist.PropertyDefinition;
import stylist.SelectorDSL;
import stylist.value.Numeric;
import stylist.value.Unit;

/**
 * @version 2018/08/30 18:28:26
 */
public class Transition extends PropertyDefinition<Transition> {

    /** The transition related property. */
    private Numeric duration = new Numeric(0, s);

    /** The transition related property. */
    private Numeric delay = new Numeric(0, s);

    /** The transition related property. */
    private String timing = "ease";

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
     * <p>
     * This keyword represents the timing function cubic-bezier(0.0, 0.0, 0.58, 1.0). The animation
     * starts quickly then slow progressively down when approaching to its final state.
     * </p>
     * 
     * @return
     */
    public Transition easeOut() {
        timing = "ease-out";
        return this;
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
     * Declare change effect.
     * </p>
     */
    public SelectorDSL when() {
        return SelectorDSL.create(rule -> {
            value("transition-property", join(rule.properties.names(), v -> v));
            value("transition-duration", join(rule.properties.names(), v -> duration));
            value("transition-delay", join(rule.properties.names(), v -> delay));
            value("transition-timing-function", join(rule.properties.names(), v -> timing));
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
    }
}
