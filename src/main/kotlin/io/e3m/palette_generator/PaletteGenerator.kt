package io.e3m.palette_generator

typealias Shades = List<Pair<String, Double>>
typealias Palette = List<Pair<String, String>>

const val LIGHTNESS_RANGE = 100;

class PaletteGenerator(referenceColor: String, val shades: Shades, val hueShift: Double = 0.0, val hueEasingFn: EasingFnType = ::easeLinear, includeOriginalColor: Boolean = true) {
    private val referenceHue: Double;
    private val referenceSaturation: Double;
    private val referenceLightness: Double;

    private val startHue: Double;
    private val nearestShadeLightness: Double;

    init {
        val (referenceHue, referenceSaturation, baseLightness) = hexToHsluv(referenceColor)
        this.referenceHue = referenceHue
        this.referenceSaturation = referenceSaturation
        this.referenceLightness = baseLightness

        this.startHue = getStartHue(referenceHue, baseLightness, hueShift, hueEasingFn);
        this.nearestShadeLightness = if (includeOriginalColor) findNearestShadeLightness(baseLightness, shades) else baseLightness;
    }

    private fun getHueAtLightness(lightness: Double): Double {
        return getHueAtLightness(lightness, startHue, hueShift, hueEasingFn);
    }

    fun palette(): Palette {
        return shades.map {
            val (name, originalLightness) = it;

            val saturation = referenceSaturation;
            val lightness = if (nearestShadeLightness == originalLightness) referenceLightness else originalLightness;
            val hue = getHueAtLightness(lightness);

            Pair(name, hsluvToHex(doubleArrayOf(hue, saturation, lightness)))
        }
    }

    fun css(): String {
        return paletteToCss(palette())
    }
}