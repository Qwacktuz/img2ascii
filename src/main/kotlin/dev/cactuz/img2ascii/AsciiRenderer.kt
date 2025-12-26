package dev.cactuz.img2ascii

import java.awt.Color
import java.awt.image.BufferedImage

class AsciiRenderer(
    private val inverted: Boolean = false,
) {
    // Ordered from Darkest (@) to Lightest (space)
    private val density = "@%#*+=-:. "

    // Create the ascii image
    fun render(image: BufferedImage): String {
        val sb = StringBuilder()

        // until excludes the upper bound value
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val pixelColor = Color(image.getRGB(x, y))
                val brightness = calculateBrightness(pixelColor)
                val char = mapBrightnessToChar(brightness)
                sb.append(char)
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    // Rec. 601 - old, meant for CRTs
    // private fun calculateBrightness(color: Color): Double = (color.red * 0.299 + color.green * 0.587 + color.blue * 0.114)

    // Take the sum of the RGB of a pixel and calculate its "perceived brightness"
    // Rec. 701 https://en.wikipedia.org/wiki/Rec._709
    private fun calculateBrightness(color: Color): Double = (color.red * 0.2126 + color.green * 0.7152 + color.blue * 0.0722)

    private fun mapBrightnessToChar(brightness: Double): Char {
        val maxIndex = density.length - 1

        // Normalize brightness between 0-1.0 and use it as a percentage for which character's density to choose
        val index = (brightness / 255.0 * maxIndex).toInt()

        // If inverted (light background terminal), use index directly.
        // Otherwise (dark background), reverse it.
        val finalIndex = if (inverted) index else maxIndex - index

        // return the lowest or highest possible luminance value
        return density[finalIndex.coerceIn(0, maxIndex)]
    }
}
