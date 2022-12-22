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

import java.util.ArrayList;

public class AnimationFrames {

    /** The frame name. */
    public final String name;

    /** The list of animation frames. */
    final ArrayList<int[]> progressions = new ArrayList();

    final ArrayList<StyleRule> styles = new ArrayList();

    /**
     */
    public AnimationFrames() {
        this.name = "Anima" + hashCode();
    }

    /**
     * Add new keyframe to this {@link AnimationFrames}.
     * 
     * @param progress A point of the animation frame.
     * @param style A style state at the point.
     * @return Chainable API.
     */
    public AnimationFrames frame(int progress, Style style) {
        return frame(style, progress);
    }

    /**
     * Add new keyframe to this {@link AnimationFrames}.
     * 
     * @param progress1 A point of the animation frame.
     * @param progress2 A point of the animation frame.
     * @param style A style state at the point.
     * @return Chainable API.
     */
    public AnimationFrames frame(int progress1, int progress2, Style style) {
        return frame(style, progress1, progress2);
    }

    /**
     * Add new keyframe to this {@link AnimationFrames}.
     * 
     * @param progress1 A point of the animation frame.
     * @param progress2 A point of the animation frame.
     * @param progress3 A point of the animation frame.
     * @param style A style state at the point.
     * @return Chainable API.
     */
    public AnimationFrames frame(int progress1, int progress2, int progress3, Style style) {
        return frame(style, progress1, progress2, progress3);
    }

    private AnimationFrames frame(Style style, int... progress) {
        progressions.add(progress);
        styles.add(Stylist.create(style));

        return this;
    }
}