package com.github.jinahya.persistence.more.color;

import java.util.regex.Pattern;

final class RgbConstants {

    static final int SHIFT_HALF = 4;

    static final int SHIFT_ALPHA = 24;

    static final int SHIFT_RED = SHIFT_ALPHA - Byte.SIZE;

    static final int SHIFT_GREEN = SHIFT_RED - Byte.SIZE;

    static final int FULLY_OPAQUE_HALF = 0xF;

    static final int FULLY_OPAQUE = (FULLY_OPAQUE_HALF << SHIFT_HALF) + FULLY_OPAQUE_HALF;

    static final int MASK_ALPHA = FULLY_OPAQUE << SHIFT_ALPHA;

    static final int MASK_RED = FULLY_OPAQUE << SHIFT_RED;

    static final int MASK_GREEN = FULLY_OPAQUE << Byte.SIZE;

    static final int MASK_BLUE = FULLY_OPAQUE;

    static final String REGEX_HEX_NOTATION_HALF = "[0-9a-f]";

    static final String REGEX_HEX_NOTATION_SINGLE = '(' + REGEX_HEX_NOTATION_HALF + "{2})";

    static final String REGEXP_CSS_COLOR_HEX_NOTATION_3 = "(" + REGEX_HEX_NOTATION_HALF + "){3}";

    static final Pattern PATTERN_CSS_COLOR_HEX_NOTATION_3
            = Pattern.compile(REGEXP_CSS_COLOR_HEX_NOTATION_3, Pattern.CASE_INSENSITIVE);

    static final String REGEXP_CSS_COLOR_HEX_NOTATION_6 = REGEX_HEX_NOTATION_SINGLE + "{3}";

    static final Pattern PATTERN_CSS_COLOR_HEX_NOTATION_6
            = Pattern.compile(REGEXP_CSS_COLOR_HEX_NOTATION_6, Pattern.CASE_INSENSITIVE);

    static final String REGEXP_CSS_COLOR_HEX_NOTATION_4 = "(" + REGEX_HEX_NOTATION_HALF + "){4}";

    static final Pattern PATTERN_CSS_COLOR_HEX_NOTATION_4
            = Pattern.compile(REGEXP_CSS_COLOR_HEX_NOTATION_4, Pattern.CASE_INSENSITIVE);

    static final String REGEXP_CSS_COLOR_HEX_NOTATION_8 = REGEX_HEX_NOTATION_SINGLE + "{4}";

    static final Pattern PATTERN_CSS_COLOR_HEX_NOTATION_8
            = Pattern.compile(REGEXP_CSS_COLOR_HEX_NOTATION_8, Pattern.CASE_INSENSITIVE);

    private RgbConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
