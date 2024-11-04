// File: Display.kt
class Display(
    private val width: Int,
    private val height: Int,
    private val ppi: Float,
    private val model: String
) {

    fun compareSize(other: Display) {
        val thisSize = width * height
        val otherSize = other.width * other.height
        when {
            thisSize > otherSize -> println("$model has a larger size than ${other.model}")
            thisSize < otherSize -> println("${other.model} has a larger size than $model")
            else -> println("$model and ${other.model} have the same size.")
        }
    }

    fun compareSharpness(other: Display) {
        when {
            ppi > other.ppi -> println("$model is sharper than ${other.model}")
            ppi < other.ppi -> println("${other.model} is sharper than $model")
            else -> println("$model and ${other.model} have the same sharpness.")
        }
    }

    fun compareWithMonitor(other: Display) {
        compareSize(other)
        compareSharpness(other)
    }
}

// Main for Task 1
fun main() {
    val display1 = Display(1920, 1080, 401f, "Display1")
    val display2 = Display(2560, 1440, 530f, "Display2")
    val display3 = Display(3840, 2160, 300f, "Display3")

    display1.compareWithMonitor(display2)
    display2.compareWithMonitor(display3)
    display1.compareWithMonitor(display3)
}
