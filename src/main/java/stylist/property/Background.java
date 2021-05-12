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

import static stylist.value.Color.*;
import static stylist.value.Unit.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.function.BiPredicate;

import javax.imageio.ImageIO;

import kiss.I;
import stylist.CSSValue;
import stylist.PropertyDefinition;
import stylist.property.helper.ColorHelper;
import stylist.value.Color;
import stylist.value.LinearGradient;
import stylist.value.Numeric;
import stylist.value.Unit;

/**
 * @version 2018/08/30 18:23:37
 */
public class Background extends PropertyDefinition<Background> implements ColorHelper<Background> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Background color(CSSValue color) {
        return value("background-color", color);
    }

    /**
     * <p>
     * The CSS background-image property sets the background images for an element. The images are
     * drawn on successive stacking context layers, with the first specified being drawn as if it is
     * the closest to the user. The borders of the element are then drawn on top of them, and the
     * background-color is drawn beneath them.
     * </p>
     * 
     * @return
     */
    public Background none() {
        return value("background-image", "none");
    }

    /**
     * <p>
     * The CSS background-image property sets the background images for an element. The images are
     * drawn on successive stacking context layers, with the first specified being drawn as if it is
     * the closest to the user. The borders of the element are then drawn on top of them, and the
     * background-color is drawn beneath them.
     * </p>
     * 
     * @param imageURL
     * @return
     */
    public Background image(String imageURL) {
        return value("background-image", normalizeURL(imageURL));
    }

    /**
     * <p>
     * The CSS background-image property sets one or several background images for an element. The
     * images are drawn on stacking context layers on top of each other. The first layer specified
     * is drawn as if it is closest to the user. The borders of the element are then drawn on top of
     * them, and the background-color is drawn beneath them.
     * </p>
     * <p>
     * If a specified image cannot be drawn (for example, when the file denoted by the specified URI
     * cannot be loaded), browsers handle it as they would a none value.
     * </p>
     * 
     * @param image
     */
    public Background image(BackgroundImage image) {
        if (!image.properties[0].match("none")) value("background-image", image.properties[0]);
        if (!image.properties[1].match("scroll")) value("background-attachment", image.properties[1]);
        if (!image.properties[2].match("0%") || !image.properties[3].match("0%"))
            value("background-position", image.properties[2] + " " + image.properties[3]);
        if (!image.properties[4].match("repeat")) value("background-repeat", image.properties[4]);
        if (!image.properties[5].match("auto")) value("background-size", image.properties[5]);
        if (!image.properties[6].match("padding-box")) value("background-origin", image.properties[6]);

        return this;
    }

    /**
     * <p>
     * The CSS background-image property sets one or several background images for an element. The
     * images are drawn on stacking context layers on top of each other. The first layer specified
     * is drawn as if it is closest to the user. The borders of the element are then drawn on top of
     * them, and the background-color is drawn beneath them.
     * </p>
     * <p>
     * If a specified image cannot be drawn (for example, when the file denoted by the specified URI
     * cannot be loaded), browsers handle it as they would a none value.
     * </p>
     * 
     * @param images
     */
    public Background image(BackgroundImage... images) {
        value("background-image", collect(images, 0), ",");
        value("background-attachment", collect(images, 1), ",");
        value("background-position", join(images, image -> image.properties[2] + " " + image.properties[3]));
        value("background-repeat", collect(images, 4), ",");
        value("background-size", collect(images, 5), ",");
        value("background-origin", collect(images, 6), ",");

        return this;
    }

    /**
     * <p>
     * Join all values.
     * </p>
     */
    private List collect(BackgroundImage[] images, int index) {
        List<CSSValue> values = new ArrayList();

        for (int i = 0; i < images.length; i++) {
            values.add(images[i].properties[index]);
        }
        return values;
    }

    /**
     * <p>
     * The background-origin CSS property determines the background positioning area, that is the
     * position of the origin of an image specified using the background-image CSS property.
     * </p>
     * <p>
     * No background is drawn below the border (background extends to the outside edge of the
     * padding).
     * </p>
     * 
     * @return
     */
    public Background paddingBox() {
        return value("background-origin", "padding-box");
    }

    /**
     * <p>
     * The background-origin CSS property determines the background positioning area, that is the
     * position of the origin of an image specified using the background-image CSS property.
     * </p>
     * <p>
     * The background is painted within (clipped to) the content box.
     * </p>
     * 
     * @return
     */
    public Background contentBox() {
        return value("background-origin", "content-box");
    }

    /**
     * <p>
     * The background-origin CSS property determines the background positioning area, that is the
     * position of the origin of an image specified using the background-image CSS property.
     * </p>
     * <p>
     * The background extends to the outside edge of the border (but underneath the border in
     * z-ordering).
     * </p>
     * 
     * @return
     */
    public Background borderBox() {
        return value("background-origin", "border-box");
    }

    /**
     * <p>
     * If a background-image is specified, the background-attachment CSS property determines whether
     * that image's position is fixed within the viewport, or scrolls along with its containing
     * block.
     * </p>
     * <p>
     * The background image will scroll within the viewport along with the block the image is
     * contained within.
     * </p>
     */
    public Background scroll() {
        return value("background-attachment", "scroll");
    }

    /**
     * <p>
     * If a background-image is specified, the background-attachment CSS property determines whether
     * that image's position is fixed within the viewport, or scrolls along with its containing
     * block.
     * </p>
     * <p>
     * The background image will not scroll with its containing element, instead remaining
     * stationary within the viewport.
     * </p>
     */
    public Background fixed() {
        return value("background-attachment", "fixed");
    }

    /**
     * <p>
     * If a background-image is specified, the background-attachment CSS property determines whether
     * that image's position is fixed within the viewport, or scrolls along with its containing
     * block.
     * </p>
     * <p>
     * The background image will not scroll with its containing element, but will scroll when the
     * element's content scrolls: it is fixed regarding the element's content.
     * </p>
     */
    public Background local() {
        return value("background-attachment", "local");
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * A value that scales the background image to the specified length in the corresponding
     * dimension. Negative lengths are not allowed.
     * </p>
     * 
     * @return
     */
    public Background size(double size, Unit unit) {
        return size(new Numeric(size, unit));
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * A value that scales the background image to the specified length in the corresponding
     * dimension. Negative lengths are not allowed.
     * </p>
     * 
     * @return
     */
    public Background size(Numeric size) {
        return value("background-size", size);
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * A value that scales the background image to the specified length in the corresponding
     * dimension. Negative lengths are not allowed.
     * </p>
     * 
     * @return
     */
    public Background size(double horizontalSize, Unit horizontalUnit, double verticalSize, Unit verticalUnit) {
        return size(new Numeric(horizontalSize, horizontalUnit), new Numeric(verticalSize, verticalUnit));
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * A value that scales the background image to the specified length in the corresponding
     * dimension. Negative lengths are not allowed.
     * </p>
     * 
     * @return
     */
    public Background size(Numeric horizontal, Numeric vertical) {
        return value("background-size", horizontal + " " + vertical);
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * This keyword specifies that the background image should be scaled to be as small as possible
     * while ensuring both its dimensions are greater than or equal to the corresponding dimensions
     * of the background positioning area.
     * </p>
     * 
     * @return
     */
    public Background cover() {
        return value("background-size", "cover");
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * This keyword specifies that the background image should be scaled to be as large as possible
     * while ensuring both its dimensions are less than or equal to the corresponding dimensions of
     * the background positioning area.
     * </p>
     * 
     * @return
     */
    public Background contain() {
        return value("background-size", "contain");
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     */
    public Background position(double horizontalSize, Unit horizontalUnit, double verticalSize, Unit verticalUnit) {
        return position(new Numeric(horizontalSize, horizontalUnit), new Numeric(verticalSize, verticalUnit));
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     */
    public Background position(Numeric horizontalSize, Numeric verticalSize) {
        return value("background-position", horizontalSize + " " + verticalSize);
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     */
    public Background horizontal(double horizontalSize, Unit horizontalUnit) {
        return horizontal(new Numeric(horizontalSize, horizontalUnit));
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     */
    public Background horizontal(Numeric horizontalSize) {
        return value("background-position", horizontalSize + " 0%");
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     */
    public Background vertical(double verticalSize, Unit verticalUnit) {
        return vertical(new Numeric(verticalSize, verticalUnit));
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     */
    public Background vertical(Numeric verticalSize) {
        return value("background-position", "0% " + verticalSize);
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     */
    public Background repeatX() {
        return value("background-repeat", "repeat-x");
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     */
    public Background repeatY() {
        return value("background-repeat", "repeat-y");
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     */
    public Background repeat() {
        return value("background-repeat", "repeat");
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     */
    public Background space() {
        return value("background-repeat", "space");
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     */
    public Background round() {
        return value("background-repeat", "round");
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     */
    public Background noRepeat() {
        return value("background-repeat", "no-repeat");
    }

    /**
     * <p>
     * Normalize the specified image URL.
     * </p>
     * 
     * @param imageURL A target URL to normalize.
     * @return A normalized URL.
     */
    private static String normalizeURL(String imageURL) {
        if (!imageURL.startsWith("url(")) {
            imageURL = "url(" + imageURL + ")";
        }
        return imageURL;
    }

    /**
     * @version 2018/09/02 7:06:52
     */
    public static class BackgroundImage {

        public static final BackgroundImage WhiteCarbon = url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAATElEQVQYV2OYNmGaOggvmb8kCoTR+QwwgXmz5tmDMDqfYfn85bEglVP7pqog64TxGXDphPEZ5k2ap0WSG+ZOmIvqBnQ7YXyYQoJuAABKJ4Hmp8ET1QAAAABJRU5ErkJggg==");

        /**
         * The background related properties.
         * <ul>
         * <li>[0] Image</li>
         * <li>[1] Attachment</li>
         * <li>[2] Horizontal Position</li>
         * <li>[3] Vertical Position</li>
         * <li>[4] Repeat</li>
         * <li>[5] Size</li> *
         * <li>[6] Origin</li>
         * </ul>
         */
        private CSSValue[] properties = {CSSValue.of("none"), CSSValue.of("scroll"), CSSValue.of("0%"), CSSValue.of("0%"),
                CSSValue.of("repeat"), CSSValue.of("auto"), CSSValue.of("padding-box")};

        /**
         * @return
         */
        public static BackgroundImage none() {
            BackgroundImage created = new BackgroundImage();
            created.properties[0] = CSSValue.of("none");

            return created;
        }

        public static BackgroundImage inherit() {
            BackgroundImage created = new BackgroundImage();
            created.properties[0] = CSSValue.of("inherit");

            return created;
        }

        /**
         * <pre>
        background-image:
        radial-gradient(circle at 29% 55%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 4%, transparent 4%, transparent 44%, transparent 44%, transparent 100%),
        radial-gradient(circle at 85% 89%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 51%, transparent 51%, transparent 52%, transparent 52%, transparent 100%),
        radial-gradient(circle at 6% 90%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 53%, transparent 53%, transparent 64%, transparent 64%, transparent 100%),
        radial-gradient(circle at 35% 75%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 6%, transparent 6%, transparent 98%, transparent 98%, transparent 100%),
        radial-gradient(circle at 56% 75%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 16%, transparent 16%, transparent 23%, transparent 23%, transparent 100%),
        radial-gradient(circle at 42% 0%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 3%, transparent 3%, transparent 26%, transparent 26%, transparent 100%),
        radial-gradient(circle at 29% 28%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 51%, transparent 51%, transparent 75%, transparent 75%, transparent 100%),
        radial-gradient(circle at 77% 21%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 35%, transparent 35%, transparent 55%, transparent 55%, transparent 100%),
        radial-gradient(circle at 65% 91%, hsla(329, 0%, 99%, 0.05) 0%, hsla(329, 0%, 99%, 0.05) 46%, transparent 46%, transparent 76%, transparent 76%, transparent 100%),
        linear-gradient(45deg, rgb(83, 91, 235), rgb(76, 11, 174));
        </pre>
         * 
         * @param one
         * @param two
         * @param three
         * @return
         */
        public static BackgroundImage[] drawCircleGradients(Color one, Color two, Color three) {
        }

        public static BackgroundImage[] drawSubtleTexture(Color base, Color line) {
            return BackgroundImage.of(new LinearGradient().angle(45, deg)
                    .color(line, 0, px)
                    .color(line, 2, px)
                    .color(Transparent, 2, px)
                    .color(Transparent, 4, px)
                    .repeat(), new LinearGradient().angle(90, deg).color(base, base));
        }

        /**
         * Draw image.
         * 
         * @param color Dot color.
         * @param size Image size (width and height).
         * @param drawer Draw function.
         * @return
         */
        public static BackgroundImage draw(Color color, int size, BiPredicate<Integer, Integer> drawer) {
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
                return url("data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray()));
            } catch (IOException e) {
                throw I.quiet(e);
            }
        }

        /**
         * Draw line image.
         * 
         * @param color Line color.
         * @param size Image size.
         * @return
         */
        public static BackgroundImage drawHorizontalLine(Color color, int size) {
            return draw(color, size, (x, y) -> y == 0);
        }

        /**
         * Draw line image.
         * 
         * @param color Line color.
         * @param size Image size.
         * @return
         */
        public static BackgroundImage drawVerticalLine(Color color, int size) {
            return draw(color, size, (x, y) -> x == 0);
        }

        /**
         * Draw slash image.
         * 
         * @param color Line color.
         * @param size Image size.
         * @return
         */
        public static BackgroundImage drawSlash(Color color, int size) {
            return draw(color, size, (x, y) -> x + y == size - 1);
        }

        /**
         * Draw tiling image.
         * 
         * @param color Line color.
         * @param size Image size.
         * @return
         */
        public static BackgroundImage drawTile(Color color, int size) {
            return draw(color, size * 2, (x, y) -> x / size % 2 == 0 ^ y / size % 2 == 1);
        }

        /**
         * Draw tiling image.
         * 
         * @param color Line color.
         * @param size Image size.
         * @return
         */
        public static BackgroundImage drawSquare(Color color, int size) {
            return draw(color, size * 2, (x, y) -> x / size % 2 == 0 && y / size % 2 == 0);
        }

        /**
         * <p>
         * The CSS background-image property sets one or several background images for an element.
         * The images are drawn on stacking context layers on top of each other. The first layer
         * specified is drawn as if it is closest to the user. The borders of the element are then
         * drawn on top of them, and the background-color is drawn beneath them.
         * </p>
         * <p>
         * If a specified image cannot be drawn (for example, when the file denoted by the specified
         * URI cannot be loaded), browsers handle it as they would a none value.
         * </p>
         */
        public static BackgroundImage of(LinearGradient image) {
            BackgroundImage created = new BackgroundImage();
            created.properties[0] = image;

            return created;
        }

        /**
         * <p>
         * The CSS background-image property sets one or several background images for an element.
         * The images are drawn on stacking context layers on top of each other. The first layer
         * specified is drawn as if it is closest to the user. The borders of the element are then
         * drawn on top of them, and the background-color is drawn beneath them.
         * </p>
         * <p>
         * If a specified image cannot be drawn (for example, when the file denoted by the specified
         * URI cannot be loaded), browsers handle it as they would a none value.
         * </p>
         */
        public static BackgroundImage[] of(LinearGradient... images) {
            BackgroundImage[] created = new BackgroundImage[images.length];
            for (int i = 0; i < created.length; i++) {
                created[i] = BackgroundImage.of(images[i]);
            }
            return created;
        }

        /**
         * <p>
         * The CSS background-image property sets one or several background images for an element.
         * The images are drawn on stacking context layers on top of each other. The first layer
         * specified is drawn as if it is closest to the user. The borders of the element are then
         * drawn on top of them, and the background-color is drawn beneath them.
         * </p>
         * <p>
         * If a specified image cannot be drawn (for example, when the file denoted by the specified
         * URI cannot be loaded), browsers handle it as they would a none value.
         * </p>
         * 
         * @param image
         */
        public static final BackgroundImage url(String image) {
            BackgroundImage created = new BackgroundImage();
            created.properties[0] = CSSValue.of(normalizeURL(image));

            return created;
        }

        /**
         * <p>
         * The background-position CSS property sets the initial position, relative to the
         * background position layer defined by background-origin for each defined background image.
         * </p>
         */
        public BackgroundImage top() {
            properties[3] = CSSValue.of("0%");

            return this;
        }

        /**
         * <p>
         * The background-position CSS property sets the initial position, relative to the
         * background position layer defined by background-origin for each defined background image.
         * </p>
         */
        public BackgroundImage bottom() {
            properties[3] = CSSValue.of("100%");

            return this;
        }

        /**
         * <p>
         * The background-position CSS property sets the initial position, relative to the
         * background position layer defined by background-origin for each defined background image.
         * </p>
         */
        public BackgroundImage vertical(double size, Unit unit) {
            return vertical(new Numeric(size, unit));
        }

        /**
         * <p>
         * The background-position CSS property sets the initial position, relative to the
         * background position layer defined by background-origin for each defined background image.
         * </p>
         */
        public BackgroundImage vertical(Numeric size) {
            properties[3] = size;

            return this;
        }

        /**
         * <p>
         * The background-position CSS property sets the initial position, relative to the
         * background position layer defined by background-origin for each defined background image.
         * </p>
         */
        public BackgroundImage left() {
            properties[2] = CSSValue.of("0%");

            return this;
        }

        /**
         * <p>
         * The background-position CSS property sets the initial position, relative to the
         * background position layer defined by background-origin for each defined background image.
         * </p>
         */
        public BackgroundImage right() {
            properties[2] = CSSValue.of("100%");

            return this;
        }

        /**
         * <p>
         * The background-position CSS property sets the initial position, relative to the
         * background position layer defined by background-origin for each defined background image.
         * </p>
         */
        public BackgroundImage horizontal(double size, Unit unit) {
            return horizontal(new Numeric(size, unit));
        }

        /**
         * <p>
         * The background-position CSS property sets the initial position, relative to the
         * background position layer defined by background-origin for each defined background image.
         * </p>
         */
        public BackgroundImage horizontal(Numeric size) {
            properties[2] = size;

            return this;
        }

        /**
         * <p>
         * The background-size CSS property specifies the size of the background images. The size of
         * the image can be fully constrained or only partially in order to preserve its intrinsic
         * ratio.
         * </p>
         * <p>
         * A value that scales the background image to the specified length in the corresponding
         * dimension. Negative lengths are not allowed.
         * </p>
         * 
         * @return
         */
        public BackgroundImage size(double size, Unit unit) {
            return size(new Numeric(size, unit));
        }

        /**
         * <p>
         * The background-size CSS property specifies the size of the background images. The size of
         * the image can be fully constrained or only partially in order to preserve its intrinsic
         * ratio.
         * </p>
         * <p>
         * A value that scales the background image to the specified length in the corresponding
         * dimension. Negative lengths are not allowed.
         * </p>
         * 
         * @return
         */
        public BackgroundImage size(Numeric size) {
            properties[5] = size;

            return this;
        }

        /**
         * <p>
         * The background-size CSS property specifies the size of the background images. The size of
         * the image can be fully constrained or only partially in order to preserve its intrinsic
         * ratio.
         * </p>
         * <p>
         * This keyword specifies that the background image should be scaled to be as small as
         * possible while ensuring both its dimensions are greater than or equal to the
         * corresponding dimensions of the background positioning area.
         * </p>
         * 
         * @return
         */
        public BackgroundImage cover() {
            properties[5] = CSSValue.of("cover");

            return this;
        }

        /**
         * <p>
         * The background-size CSS property specifies the size of the background images. The size of
         * the image can be fully constrained or only partially in order to preserve its intrinsic
         * ratio.
         * </p>
         * <p>
         * This keyword specifies that the background image should be scaled to be as large as
         * possible while ensuring both its dimensions are less than or equal to the corresponding
         * dimensions of the background positioning area.
         * </p>
         * 
         * @return
         */
        public BackgroundImage contain() {
            properties[5] = CSSValue.of("contain");

            return this;
        }

        /**
         * <p>
         * If a background-image is specified, the background-attachment CSS property determines
         * whether that image's position is fixed within the viewport, or scrolls along with its
         * containing block.
         * </p>
         * <p>
         * The background image will scroll within the viewport along with the block the image is
         * contained within.
         * </p>
         */
        public BackgroundImage scroll() {
            properties[1] = CSSValue.of("scroll");

            return this;
        }

        /**
         * <p>
         * If a background-image is specified, the background-attachment CSS property determines
         * whether that image's position is fixed within the viewport, or scrolls along with its
         * containing block.
         * </p>
         * <p>
         * The background image will not scroll with its containing element, instead remaining
         * stationary within the viewport.
         * </p>
         */
        public BackgroundImage fixed() {
            properties[1] = CSSValue.of("fixed");

            return this;
        }

        /**
         * <p>
         * If a background-image is specified, the background-attachment CSS property determines
         * whether that image's position is fixed within the viewport, or scrolls along with its
         * containing block.
         * </p>
         * <p>
         * The background image will not scroll with its containing element, but will scroll when
         * the element's content scrolls: it is fixed regarding the element's content.
         * </p>
         */
        public BackgroundImage local() {
            properties[1] = CSSValue.of("local");

            return this;
        }

        /**
         * <p>
         * The background-repeat CSS property defines how background images are repeated. A
         * background image can be repeated along the horizontal axis, the vertical axis, both, or
         * not repeated at all. When the repetition of the image tiles doesn't let them exactly
         * cover the background, the way adjustments are done can be controlled by the author: by
         * default, the last image is clipped, but the different tiles can instead be re-sized, or
         * space can be inserted between the tiles.
         * </p>
         */
        public BackgroundImage repeatX() {
            properties[4] = CSSValue.of("repeat-x");

            return this;
        }

        /**
         * <p>
         * The background-repeat CSS property defines how background images are repeated. A
         * background image can be repeated along the horizontal axis, the vertical axis, both, or
         * not repeated at all. When the repetition of the image tiles doesn't let them exactly
         * cover the background, the way adjustments are done can be controlled by the author: by
         * default, the last image is clipped, but the different tiles can instead be re-sized, or
         * space can be inserted between the tiles.
         * </p>
         */
        public BackgroundImage repeatY() {
            properties[4] = CSSValue.of("repeat-y");

            return this;
        }

        /**
         * <p>
         * The background-repeat CSS property defines how background images are repeated. A
         * background image can be repeated along the horizontal axis, the vertical axis, both, or
         * not repeated at all. When the repetition of the image tiles doesn't let them exactly
         * cover the background, the way adjustments are done can be controlled by the author: by
         * default, the last image is clipped, but the different tiles can instead be re-sized, or
         * space can be inserted between the tiles.
         * </p>
         */
        public BackgroundImage repeat() {
            properties[4] = CSSValue.of("repeat");

            return this;
        }

        /**
         * <p>
         * The background-repeat CSS property defines how background images are repeated. A
         * background image can be repeated along the horizontal axis, the vertical axis, both, or
         * not repeated at all. When the repetition of the image tiles doesn't let them exactly
         * cover the background, the way adjustments are done can be controlled by the author: by
         * default, the last image is clipped, but the different tiles can instead be re-sized, or
         * space can be inserted between the tiles.
         * </p>
         */
        public BackgroundImage space() {
            properties[4] = CSSValue.of("space");

            return this;
        }

        /**
         * <p>
         * The background-repeat CSS property defines how background images are repeated. A
         * background image can be repeated along the horizontal axis, the vertical axis, both, or
         * not repeated at all. When the repetition of the image tiles doesn't let them exactly
         * cover the background, the way adjustments are done can be controlled by the author: by
         * default, the last image is clipped, but the different tiles can instead be re-sized, or
         * space can be inserted between the tiles.
         * </p>
         */
        public BackgroundImage round() {
            properties[4] = CSSValue.of("round");

            return this;
        }

        /**
         * <p>
         * The background-repeat CSS property defines how background images are repeated. A
         * background image can be repeated along the horizontal axis, the vertical axis, both, or
         * not repeated at all. When the repetition of the image tiles doesn't let them exactly
         * cover the background, the way adjustments are done can be controlled by the author: by
         * default, the last image is clipped, but the different tiles can instead be re-sized, or
         * space can be inserted between the tiles.
         * </p>
         */
        public BackgroundImage noRepeat() {
            properties[4] = CSSValue.of("no-repeat");

            return this;
        }

        /**
         * <p>
         * The background extends to the outside edge of the border (but underneath the border in
         * z-ordering).
         * </p>
         */
        public BackgroundImage borderBox() {
            properties[6] = CSSValue.of("border-box");

            return this;
        }

        /**
         * <p>
         * No background is drawn below the border (background extends to the outside edge of the
         * padding).
         * </p>
         */
        public BackgroundImage paddingBox() {
            properties[6] = CSSValue.of("padding-box");

            return this;
        }

        /**
         * <p>
         * The background is painted within (clipped to) the content box.
         * </p>
         */
        public BackgroundImage contentBox() {
            properties[6] = CSSValue.of("content-box");

            return this;
        }
    }
}