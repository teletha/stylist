/*
 * Copyright (C) 2021 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package stylist.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.function.BiPredicate;

import javax.imageio.ImageIO;

import kiss.I;
import stylist.value.Color;

public class Image {

    /**
     * Draw slash image.
     * 
     * @param color Line color.
     * @param size Image size.
     * @return
     */
    public static String slash(Color color, int size) {
        return draw(color, size, (x, y) -> x + y == size - 1);
    }

    /**
     * Draw image.
     * 
     * @param color Dot color.
     * @param size Image size (width and height).
     * @param drawer Draw function.
     * @return
     */
    public static String draw(Color color, int size, BiPredicate<Integer, Integer> drawer) {
        int[] rgb = color.toRGBValue();
        int alpha = (int) (color.alpha * 255);
        int c = (alpha << 24) | (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            // write image
            BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    img.setRGB(x, y, drawer.test(x, y) ? c : 0);
                }
            }

            // write to byte array
            ImageIO.write(img, "png", os);

            // encode
            return "url(data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray()) + ")";
        } catch (IOException e) {
            throw I.quiet(e);
        }
    }
}
