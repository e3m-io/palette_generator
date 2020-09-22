package io.e3m.palette_generator

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {
    @Test
    fun testHexToHsluv() {
        assertEquals("#3399ff", hsluvToHex(hexToHsluv("#3399ff")))
    }

    @Test
    fun cssGeneratorTest() {
        val samplePalette = mapOf("primary-color" to "#3399ff", "accent-color" to "#ff4d00").toList()
        val selector = ":root"
        assertEquals(":root{--primary-color:#3399ff;--accent-color:#ff4d00;}", paletteToCss(samplePalette, selector))
    }
}
