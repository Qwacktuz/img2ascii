package dev.cactuz.img2ascii

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: png2ascii <image-path> [width]")
        return
    }

    val imagePath = args[0]
    // Default width is 100 if the user doesn't provide one
    // I love kotlin's elvis operator
    val width = args.getOrNull(1)?.toIntOrNull() ?: 100

    try {
        println("Converting $imagePath...")

        // 1. Load and Resize
        val original = ImageUtils.loadImage(imagePath)
        val resized = ImageUtils.resizeForTerminal(original, width)

        // 2. Render
        val renderer = AsciiRenderer(inverted = false) // Dark terminal is default
        val result = renderer.render(resized)

        // 3. Output
        println(result)
    } catch (e: Exception) {
        System.err.println("Error: ${e.message}")
    }
}
