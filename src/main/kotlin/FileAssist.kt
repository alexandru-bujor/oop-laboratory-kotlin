// File: Assistant.kt
class Assistant(private val assistantName: String) {
    private val assignedDisplays = mutableListOf<Display>()

    fun assignDisplay(display: Display) {
        assignedDisplays.add(display)
    }

    fun assist() {
        for (i in 0 until assignedDisplays.size - 1) {
            assignedDisplays[i].compareWithMonitor(assignedDisplays[i + 1])
        }
    }

    fun buyDisplay(display: Display): Display? {
        return if (assignedDisplays.remove(display)) display else null
    }
}

// Main for Task 3
fun main() {
    val assistant = Assistant("Display Assistant")
    val display1 = Display(1920, 1080, 401f, "Display1")
    val display2 = Display(2560, 1440, 530f, "Display2")

    assistant.assignDisplay(display1)
    assistant.assignDisplay(display2)
    assistant.assist()
}
