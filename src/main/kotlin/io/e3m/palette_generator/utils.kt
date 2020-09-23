package io.e3m.palette_generator

import io.e3m.palette_generator.easings.EasingFnType
import org.hsluv.HUSLColorConverter
import kotlin.math.absoluteValue

typealias CssProperty = Pair<String, String>;
typealias CssProperties = List<CssProperty>;

/**
 * Convert a hex string color representation to HSLuv
 */
fun hexToHsluv(hex: String): DoubleArray {
    return HUSLColorConverter.hexToHsluv(hex)
}

/**
 * Convert a HSLuv color to a hex string color representation
 */
fun hsluvToHex(hsluv: DoubleArray): String {
    return HUSLColorConverter.hsluvToHex(hsluv)
}

/**
 * Converts a `CssProperty` to a `String` representation
 */
fun cssPairToString(cssPair: CssProperty): String {
    val (property, value) = cssPair
    return "$property:$value;"
}

/**
 * Reduce a `CssProperty` variable to a string
 */
fun reduceCssPropsToString(accum: String, property: CssProperty): String {
    return accum + "--" + cssPairToString(property);
}

/**
 * Wrap a string of CSS properties with a CSS selector block
 */
fun wrapWithCssSelector(selector: String, css: String): String {
    return "$selector{$css}";
}

/**
 * Convert a list of `CssProperties` to a CSS `String` and place in a block scoped to the provided `selector`
 */
fun paletteToCss(palette: CssProperties, selector: String = ":root"): String {
    return wrapWithCssSelector(selector, palette.fold("", ::reduceCssPropsToString));
}

/**
 * Find a start hue that fits the hue curve to the reference color
 */
fun getStartHue(referenceHue: Double, referenceLightness: Double, hueShift: Double, hueEasingFn: EasingFnType): Double {
    return referenceHue - (hueShift * hueEasingFn(referenceLightness / LIGHTNESS_RANGE));
}

/**
 * For a given lightness, return the hue value at that point on the easing function curve
 */
fun getHueAtLightness(lightness: Double, startHue: Double, hueShift: Double, hueEasingFn: EasingFnType): Double {
    if (hueShift == 0.0) {
        return startHue;
    }
    return hueEasingFn(lightness / LIGHTNESS_RANGE) * hueShift + startHue;
}

/**
 * From a list of `shades`, find the closest shade lightness to the reference color's lightness
 */
fun findNearestShadeLightness(lightness: Double, shades: Shades): Double {
    var smallest = 100.0;
    var minDistance = 100.0;

    for ((_, value) in shades) {
        val distance = value - lightness;
        if (distance.absoluteValue < minDistance) {
            minDistance = distance.absoluteValue;
            smallest = value;
        }
    }

    return smallest;
}