package img2ascii

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object ImageUtils {
    // Standard terminal character height correction
    private const val FONT_ASPECT_RATIO = 0.55

    fun loadImage(path: String): BufferedImage {
        val file = File(path)
        if (!file.exists()) throw IllegalArgumentException("File not found: $path")
        return ImageIO.read(file) ?: throw IllegalArgumentException("Invalid image file")
    }

    fun resizeForTerminal(
        image: BufferedImage,
        targetWidth: Int,
    ): BufferedImage {
        val aspectRatio = image.height.toDouble() / image.width.toDouble()
        val targetHeight = (targetWidth * aspectRatio * FONT_ASPECT_RATIO).toInt()

        val resized = BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB)
        val g = resized.createGraphics()
        g.drawImage(image, 0, 0, targetWidth, targetHeight, null)
        g.dispose()

        return resized
    }
}
