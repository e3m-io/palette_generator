package io.e3m.palette_generator

import kotlin.math.pow

typealias EasingFnType = (lightness: Double) -> Double;

fun easeInQuart(x: Double): Double {
    return x.pow(4);
}

fun easeLinear(x: Double): Double {
    return x;
}

fun easeInQuad(x: Double): Double {
    return x * x;
}

fun easeOutQuad(x: Double): Double {
    return 1 - (1 - x) * (1 - x);
}