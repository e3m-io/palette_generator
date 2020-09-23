package io.e3m.palette_generator.easings;

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

typealias EasingFnType = (x: Double) -> Double;

fun easeLinear(x: Double): Double {
    return x;
}

fun easeInSine(x: Double): Double {
    return 1 - cos((x * PI) / 2);
}

fun easeOutSine(x: Double): Double {
    return sin((x * PI) / 2);
}

fun easeInOutSine(x: Double): Double {
    return -(cos(PI * x) - 1) / 2;
}

fun easeInQuad(x: Double): Double {
    return x * x;
}

fun easeOutQuad(x: Double): Double {
    return 1 - (1 - x) * (1 - x);
}

fun easeInOutQuad(x: Double): Double {
    return if (x < 0.5) 2 * x * x else 1 - (-2 * x + 2).pow(2) / 2
}

fun easeInCubic(x: Double): Double {
    return x.pow(3);
}

fun easeOutCubic(x: Double): Double {
    return 1 - (1 - x).pow(3);
}

fun easeInOutCubic(x: Double): Double {
    return if (x < 0.5) 4 * x * x * x else 1 - (-2 * x + 2).pow(3) / 2
}

fun easeInQuart(x: Double): Double {
    return x.pow(4);
}

fun easeOutQuart(x: Double): Double {
    return 1 - (1 - x).pow(4);
}

fun easeInExpo(x: Double): Double {
    return if (x == 0.0) 0.0 else 2.0.pow(10 * x - 10);
}

fun easeOutExpo(x: Double): Double {
    return if (x == 1.0) 1.0 else 1 - 2.0.pow(-10 * x);
}

fun easeInOutExpo(x: Double): Double {
    return if (x == 0.0) {
        0.0
    } else {
        if (x == 1.0) {
            1.0
        } else {
            if (x < 0.5) {
                2.0.pow(20 * x - 10) / 2
            } else {
                (2 - 2.0.pow(-20 * x + 10)) / 2
            }
        }
    }
}