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

import static stylist.Vendor.*;
import static stylist.value.Unit.px;

import stylist.CSSValue;
import stylist.PropertyDefinition;
import stylist.property.helper.ColorHelper;
import stylist.value.Color;
import stylist.value.Numeric;
import stylist.value.Shadow;
import stylist.value.Unit;

public class Text extends PropertyDefinition<Text> {

    /**
     * <p>
     * The text-align CSS property describes how inline content like text is aligned in its parent
     * block element. text-align does not control the alignment of block elements itself, only their
     * inline content.
     * </p>
     */
    public final Align align = new Align();

    /**
     * <p>
     * The text-decoration CSS property is used to set the text formatting to underline, overline,
     * line-through or blink.
     * </p>
     */
    public final Decoration decoration = new Decoration();

    /**
     * <p>
     * The text-decoration-color CSS property sets the color of decorations added to text by
     * text-decoration-line.
     * </p>
     * <p>
     * The color applies to decorations, such as underlines, overlines, strikethroughs, and wavy
     * lines like those used to mark misspellings, in the scope of the property's value.
     * </p>
     * <p>
     * CSS does not provide a direct mechanism for specifying a unique color for each line type.
     * This effect can nevertheless be achieved by nesting elements, applying a different line type
     * to each element (with the text-decoration-line property), and specifying the line color (with
     * text-decoration-color) on a per‐element basis.
     * </p>
     * <p>
     * When setting multiple line-decoration properties at once, it may be more convenient to use
     * the text-decoration shorthand property instead.
     * </p>
     */
    public final DecorationColor decorationColor = new DecorationColor();

    /**
     * The text-decoration-style CSS property sets the style of the lines specified by
     * text-decoration-line. The style applies to all lines that are set with text-decoration-line.
     * <p>
     * If the specified decoration has a specific semantic meaning, like a line-through line meaning
     * that some text has been deleted, authors are encouraged to denote this meaning using an HTML
     * tag, like <del> or <s>. As browsers can disable styling in some cases, the semantic meaning
     * won't disappear in such a situation.
     * </p>
     * <p>
     * When setting multiple line-decoration properties at once, it may be more convenient to use
     * the text-decoration shorthand property instead.
     * </p>
     */
    public final DecorationStyle decorationStyle = new DecorationStyle();

    /**
     * <p>
     * The text-overflow CSS property determines how overflowed content that is not displayed is
     * signaled to the users. It can be clipped, display an ellipsis ('…', U+2026 Horizontal
     * Ellipsis) or a Web author-defined string.
     * </p>
     */
    public final Overflow overflow = new Overflow();

    /**
     * <p>
     * The vertical-align CSS property specifies the vertical alignment of an inline or table-cell
     * element.
     * </p>
     */
    public final VerticalAlign verticalAlign = new VerticalAlign();

    /**
     * <p>
     * The text-transform CSS property specifies how to capitalize an element's text. It can be used
     * to make text appear in all-uppercase or all-lowercase, or with each word capitalized. It also
     * can help improve legibility for ruby.
     * </p>
     */
    public final Transform transform = new Transform();

    /**
     * <p>
     * The text-underline-offset CSS property sets the offset distance of an underline text
     * decoration line (applied using text-decoration) from its original position.
     * </p>
     * <p>
     * text-underline-offset is not part of the text-decoration shorthand. While an element can have
     * multiple text-decoration lines, text-underline-offset only impacts underlining, and not other
     * possible line decoration options such as overline or line-through.
     * </p>
     */
    public final UnderlineOffset underlineOffset = new UnderlineOffset();

    /**
     * <p>
     * The text-underline-position CSS property specifies the position of the underline which is set
     * using the text-decoration property's underline value.
     * </p>
     */
    public final UnderlinePosition underlinePosition = new UnderlinePosition();

    /**
     * <p>
     * The white-space CSS property sets how white space inside an element is handled.
     * </p>
     */
    public final WhiteSpace whiteSpace = new WhiteSpace();

    /**
     * <p>
     * The word-break CSS property sets whether line breaks appear wherever the text would otherwise
     * overflow its content box.
     * </p>
     */
    public final WordBreak wordBreak = new WordBreak();

    /**
     * <p>
     * The text-indent CSS property specifies how much horizontal space should be left before the
     * beginning of the first line of the text content of an element. Horizontal spacing is with
     * respect to the left (or right, for right-to-left layout) edge of the containing block
     * element's box.
     * </p>
     */
    public Text indent(double size, Unit unit) {
        return indent(new Numeric(size, unit));
    }

    /**
     * <p>
     * The text-indent CSS property specifies how much horizontal space should be left before the
     * beginning of the first line of the text content of an element. Horizontal spacing is with
     * respect to the left (or right, for right-to-left layout) edge of the containing block
     * element's box.
     * </p>
     */
    public Text indent(Numeric size) {
        return value("text-indent", size);
    }

    /**
     * <p>
     * The text-shadow CSS property adds shadows to text. It accepts a comma-separated list of
     * shadows to be applied to the text and text-decorations of the element.
     * </p>
     * 
     * @return
     */
    public Text shadow(Shadow... shadows) {
        value("text-shadow", join(shadows, v -> v.toString()));

        return this;
    }

    /**
     * <p>
     * Helper method to write one-point text shadow. This method is equivalent to the following
     * code:
     * </p>
     * <pre>
     * text.shadow(color, 1);
     * </pre>
     *
     * @return
     */
    public Text shadow(Color color) {
        return shadow(color, 1);
    }

    /**
     * <p>
     * Helper method to write one-point text shadow.
     * </p>
     *
     * @param transparency A transparency of shadow.
     * @return
     */
    public Text shadow(Color color, float transparency) {
        color = Color.hsl(0, 0, color.lightness < 50 ? 100 : 0, transparency);
        Shadow shadow1 = new Shadow().offset(1, 0, px).color(color);
        Shadow shadow2 = new Shadow().offset(0, 1, px).color(color);

        return shadow(shadow1, shadow2);
    }

    /**
     * <p>
     * Helper method to write outlined text.
     * </p>
     *
     * @param transparency A transparency of shadow.
     * @return
     */
    public Text outline(Color color, float transparency) {
        color = Color.hsl(0, 0, color.lightness < 50 ? 100 : 0, transparency);
        Shadow shadow1 = new Shadow().offset(1, 0, px).color(color);
        Shadow shadow2 = new Shadow().offset(0, 1, px).color(color);
        Shadow shadow3 = new Shadow().offset(-1, 0, px).color(color);
        Shadow shadow4 = new Shadow().offset(0, -1, px).color(color);

        return shadow(shadow1, shadow2, shadow3, shadow4);
    }

    /**
     * <p>
     * Helper method to make text unselectable.
     * </p>
     */
    public Text unselectable() {
        value(CSSValue.of("user-select", Mozilla, MS, Webkit), "none");

        BuiltinStyle.unselectable.style();

        return this;
    }

    /**
     * @version 2014/10/28 17:56:37
     */
    public class Align extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private Align() {
            super("text-align", Text.this);
        }

        /**
         * <p>
         * The inline contents are aligned to the left edge of the line box.
         * </p>
         * 
         * @return
         */
        public Text left() {
            return value("left");
        }

        /**
         * <p>
         * The inline contents are aligned to the right edge of the line box.
         * </p>
         * 
         * @return
         */
        public Text right() {
            return value("right");
        }

        /**
         * <p>
         * The inline contents are centered within the line box.
         * </p>
         * 
         * @return
         */
        public Text center() {
            return value("center");
        }

        /**
         * <p>
         * The text is justified. Text should line up their left and right edges to the left and
         * right content edges of the paragraph.
         * </p>
         * 
         * @return
         */
        public Text justify() {
            return value("justify");
        }

        /**
         * <p>
         * The same as left if direction is left-to-right and right if direction is right-to-left.
         * </p>
         * 
         * @return
         */
        public Text start() {
            return value("start");
        }

        /**
         * <p>
         * The same as right if direction is left-to-right and left if direction is right-to-left.
         * </p>
         * 
         * @return
         */
        public Text end() {
            return value("end");
        }
    }

    /**
     * @version 2014/10/28 17:56:46
     */
    public class VerticalAlign extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private VerticalAlign() {
            super("vertical-align", Text.this);
        }

        /**
         * <p>
         * Aligns the baseline of the element with the baseline of its parent. The baseline of some
         * replaced elements, like <textarea> is not specified by the HTML specification, meaning
         * that their behavior with this keyword may change from one browser to the other.
         * </p>
         * 
         * @return
         */
        public Text baseline() {
            return value("baseline");
        }

        /**
         * <p>
         * Aligns the baseline of the element with the subscript-baseline of its parent.
         * </p>
         * 
         * @return
         */
        public Text sub() {
            return value("sub");
        }

        /**
         * <p>
         * Aligns the baseline of the element with the superscript-baseline of its parent.
         * </p>
         * 
         * @return
         */
        public Text sup() {
            return value("super");
        }

        /**
         * <p>
         * Aligns the top of the element with the top of the parent element's font.
         * </p>
         * 
         * @return
         */
        public Text textTop() {
            return value("text-top");
        }

        /**
         * <p>
         * Aligns the bottom of the element with the bottom of the parent element's font.
         * </p>
         * 
         * @return
         */
        public Text textBottom() {
            return value("text-bottom");
        }

        /**
         * <p>
         * Aligns the middle of the element with the middle of lowercase letters in the parent.
         * </p>
         * 
         * @return
         */
        public Text middle() {
            return value("middle");
        }

        /**
         * <p>
         * Align the top of the element and its descendants with the top of the entire line.
         * </p>
         * 
         * @return
         */
        public Text top() {
            return value("top");
        }

        /**
         * <p>
         * Align the bottom of the element and its descendants with the bottom of the entire line.
         * </p>
         * 
         * @return
         */
        public Text bottom() {
            return value("bottom");
        }
    }

    public class Decoration extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private Decoration() {
            super("text-decoration", Text.this);
        }

        /**
         * <p>
         * The text-decoration CSS property is used to set the text formatting to underline,
         * overline, line-through or blink.
         * </p>
         * <p>
         * Produces no text decoration.
         * </p>
         * 
         * @return
         */
        public Text none() {
            return value("none");
        }

        /**
         * <p>
         * The text-decoration CSS property is used to set the text formatting to underline,
         * overline, line-through or blink.
         * </p>
         * <p>
         * Each line of text is underlined.
         * </p>
         * 
         * @return
         */
        public Text underline() {
            return value("underline");
        }

        /**
         * <p>
         * The text-decoration CSS property is used to set the text formatting to underline,
         * overline, line-through or blink.
         * </p>
         * <p>
         * Each line of text has a line above it.
         * </p>
         * 
         * @return
         */
        public Text overline() {
            return value("overline");
        }

        /**
         * <p>
         * The text-decoration CSS property is used to set the text formatting to underline,
         * overline, line-through or blink.
         * </p>
         * <p>
         * Each line of text has a line through the middle.
         * </p>
         * 
         * @return
         */
        public Text lineThrough() {
            return value("line-through");
        }

        /**
         * <p>
         * Text blinks. Browsers may ignore this value (without making the declaration invalid), as
         * Internet Explorer and Safari does. Supported by Firefox (Gecko) and Opera. Note that not
         * blinking the text is one technique to satisfy checkpoint 3.3 of WAI-UAAG
         * </p>
         * 
         * @return
         */
        public Text blink() {
            return value("blink");
        }
    }

    public class DecorationColor extends PropertyDefinition<Text> implements ColorHelper<Text> {

        /**
         * Hide.
         */
        private DecorationColor() {
            super("text-decoration-color", Text.this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Text color(CSSValue color) {
            return value(color);
        }
    }

    public class DecorationStyle extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private DecorationStyle() {
            super("text-decoration-style", Text.this);
        }

        /**
         * Draws a single line.
         * 
         * @return
         */
        public Text solid() {
            return value("solid");
        }

        /**
         * Draws a double line.
         */
        public Text doubles() {
            return value("double");
        }

        /**
         * Draws a dotted line.
         */
        public Text dotted() {
            return value("dotted");
        }

        /**
         * Draws a dashed line.
         */
        public Text dashed() {
            return value("dashed");
        }

        /**
         * Draws a wavy line.
         */
        public Text wavy() {
            return value("wavy");
        }
    }

    public class Overflow extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private Overflow() {
            super("text-overflow", Text.this);
        }

        /**
         * <p>
         * This keyword value indicates to truncate the text at the limit of the content area,
         * therefore the truncation can happen in the middle of a character. To truncate at the
         * transition between two characters, the empty string value ('') must be used. The value
         * clip is the default for this property.
         * </p>
         * 
         * @return
         */
        public Text clip() {
            return value("clip");
        }

        /**
         * <p>
         * This keyword value indicates to display ellipses ('…', U+2026 Horizontal Ellipsis) to
         * represent clipped text. The ellipsis is displayed inside the content area, shortening
         * more the size of the displayed text. If there is not enough place to display ellipsis,
         * they are clipped.
         * 
         * @return
         */
        public Text ellipsis() {
            return value("ellipsis");
        }
    }

    /**
     * <p>
     * The text-transform CSS property specifies how to capitalize an element's text. It can be used
     * to make text appear in all-uppercase or all-lowercase, or with each word capitalized. It also
     * can help improve legibility for ruby.
     * </p>
     */
    public class Transform extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private Transform() {
            super("text-transform", Text.this);
        }

        /**
         * <p>
         * Converts the first letter of each word to uppercase. Other characters remain unchanged
         * (they retain their original case as written in the element's text). A letter is defined
         * as a character that is part of Unicode's Letter or Number general categories ; thus, any
         * punctuation marks or symbols at the beginning of a word are ignored.
         * </p>
         * 
         * @return
         */
        public Text capitalize() {
            return value("capitalize");
        }

        /**
         * <p>
         * Converts all characters to uppercase.
         * </p>
         * 
         * @return
         */
        public Text upper() {
            return value("uppercase");
        }

        /**
         * <p>
         * Converts all characters to lowercase.
         * </p>
         * 
         * @return
         */
        public Text lower() {
            return value("lowercase");
        }

        /**
         * <p>
         * Prevents the case of all characters from being changed.
         * </p>
         * 
         * @return
         */
        public Text none() {
            return value("none");
        }

        /**
         * <p>
         * Forces the writing of a character — mainly ideograms and Latin scripts — inside a square,
         * allowing them to be aligned in the usual East Asian scripts (like Chinese or Japanese).
         * </p>
         * 
         * @return
         */
        public Text fullWidth() {
            return value("full-width");
        }

        /**
         * <p>
         * Generally used for <ruby> annotation text, the keyword converts all small Kana characters
         * to the equivalent full-size Kana, to compensate for legibility issues at the small font
         * sizes typically used in ruby.
         * </p>
         * 
         * @return
         */
        public Text fullSizeKana() {
            return value("full-size-kana");
        }
    }

    public class UnderlineOffset extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private UnderlineOffset() {
            super("text-underline-offset", Text.this);
        }

        /**
         * The browser chooses the appropriate offset for underlines.
         * 
         * @return
         */
        public Text auto() {
            return value("auto");
        }

        /**
         * If the font file includes information about a preferred offset, use that value. If the
         * font file doesn't include this information, behave as if auto was set, with the browser
         * choosing an appropriate offset.
         * 
         * @return
         */
        public Text fromFont() {
            return value("from-font");
        }

        /**
         * Specifies the offset of underlines as a <length>, overriding the font file suggestion and
         * the browser default. It is recommended to use em units so the offset scales with the font
         * size.
         * 
         * @return
         */
        public Text length(int length, Unit unit) {
            return length(Numeric.of(length, unit));
        }

        /**
         * Specifies the offset of underlines as a <length>, overriding the font file suggestion and
         * the browser default. It is recommended to use em units so the offset scales with the font
         * size.
         * 
         * @return
         */
        public Text length(Numeric length) {
            return value(length);
        }
    }

    public class UnderlinePosition extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private UnderlinePosition() {
            super("text-underline-position", Text.this);
        }

        /**
         * The user agent will use its own algorithm to place the line at or under the alphabetic
         * baseline.
         * 
         * @return
         */
        public Text auto() {
            return value("auto");
        }

        /**
         * Forces the line to be set below the alphabetic baseline, at a position where it won't
         * cross any descenders. This is useful for ensuring legibility with chemical and
         * mathematical formulas, which make a large use of subscripts.
         * 
         * @return
         */
        public Text under() {
            return value("under");
        }

        /**
         * In vertical writing-modes, this keyword forces the line to be placed on the left side of
         * the text. In horizontal writing-modes, it is a synonym of under.
         * 
         * @return
         */
        public Text left() {
            return value("left");
        }

        /**
         * In vertical writing-modes, this keyword forces the line to be placed on the right side of
         * the text. In horizontal writing-modes, it is a synonym of under.
         * 
         * @return
         */
        public Text right() {
            return value("right");
        }
    }

    public class WhiteSpace extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private WhiteSpace() {
            super("white-space", Text.this);
        }

        /**
         * Sequences of white space are collapsed. Newline characters in the source are handled the
         * same as other white space. Lines are broken as necessary to fill line boxes.
         * 
         * @return
         */
        public Text normal() {
            return value("normal");
        }

        /**
         * Collapses white space as for normal, but suppresses line breaks (text wrapping) within
         * the source.
         * 
         * @return
         */
        public Text nowrap() {
            return value("nowrap");
        }

        /**
         * Sequences of white space are preserved. Lines are only broken at newline characters in
         * the source and at <br>
         * elements.
         * 
         * @return
         */
        public Text pre() {
            return value("pre");
        }

        /**
         * Sequences of white space are preserved. Lines are broken at newline characters, at <br>
         * , and as necessary to fill line boxes.
         * 
         * @return
         */
        public Text preWrap() {
            return value("pre-wrap");
        }

        /**
         * Sequences of white space are collapsed. Lines are broken at newline characters, at <br>
         * , and as necessary to fill line boxes.
         * 
         * @return
         */
        public Text preLine() {
            return value("pre-line");
        }

        /**
         * The behavior is identical to that of pre-wrap, except that:
         * <ul>
         * <li>Any sequence of preserved white space always takes up space, including at the end of
         * the line.</li>
         * <li>A line breaking opportunity exists after every preserved white space character,
         * including between white space characters.</li>
         * <li>Such preserved spaces take up space and do not hang, and thus affect the box’s
         * intrinsic sizes (min-content size and max-content size).</li>
         * </ul>
         * 
         * @return
         */
        public Text breakSpaces() {
            return value("break-spaces");
        }
    }

    public class WordBreak extends PropertyDefinition<Text> {

        /**
         * Hide.
         */
        private WordBreak() {
            super("word-break", Text.this);
        }

        /**
         * Use the default line break rule.
         * 
         * @return
         */
        public Text normal() {
            return value("normal");
        }

        /**
         * To prevent overflow, word breaks should be inserted between any two characters (excluding
         * Chinese/Japanese/Korean text).
         * 
         * @return
         */
        public Text breakAll() {
            return value("break-all");
        }

        /**
         * Word breaks should not be used for Chinese/Japanese/Korean (CJK) text. Non-CJK text
         * behavior is the same as for normal.
         * 
         * @return
         */
        public Text keepAll() {
            return value("keep-all");
        }
    }
}