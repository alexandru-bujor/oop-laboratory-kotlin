package lab1.task1

class Display(val width: Int, val height: Int, val ppi: Float, val model: String) {
    fun compareSize(other: Display) {
        val areaThis = width * height
        val areaOther = other.width * other.height
        when {
            areaThis > areaOther -> println("$model has a larger screen size than ${other.model}.")
            areaThis < areaOther -> println("${other.model} has a larger screen size than $model.")
            else -> println("$model and ${other.model} have the same screen size.")
        }
    }

    fun compareSharpness(other: Display) {
        when {
            ppi > other.ppi -> println("$model has a sharper display than ${other.model}.")
            ppi < other.ppi -> println("${other.model} has a sharper display than $model.")
            else -> println("$model and ${other.model} have the same display sharpness.")
        }
    }

    fun compareWithMonitor(other: Display) {
        compareSize(other)
        compareSharpness(other)
    }


}

fun main() {
    val display1 = Display(1920, 1080, 401f, "Display1")
    val display2 = Display(2560, 1440, 530f, "Display2")
    val display3 = Display(3840, 2160, 300f, "Display3")

    display1.compareWithMonitor(display2)
    display1.compareWithMonitor(display3)
    display2.compareWithMonitor(display3)
}

fun runTask() {
   main()
}
