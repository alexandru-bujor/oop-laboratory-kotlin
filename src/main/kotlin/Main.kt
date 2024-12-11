import com.google.gson.Gson
import task1.Car
import task1.SimpleQueue
import task2.ElectricStation
import task2.GasStation
import task2.PeopleDinner
import task2.RobotDinner
import task3.CarStation
import task4.Semaphore
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.ArrayList

private const val QUEUE_DIR = "C:\\Users\\sandu\\IdeaProjects\\oop-laboratory-kotlin\\queue"

fun main(args: Array<String>) {
    clearQueueDirectory()
    startPythonGenerator()

    val initialCapacity = 30
    val stations = createCarStations(initialCapacity)
    val semaphore = Semaphore(stations)

    val startTime = System.currentTimeMillis()
    val duration = 60000 // 1 minute
    while (System.currentTimeMillis() - startTime < duration) {
        processNewCars(semaphore)
        try {
            Thread.sleep(4000) // Check for new cars every 4 seconds
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


    }


private fun clearQueueDirectory() {
    val dir = File(QUEUE_DIR)
    if (dir.exists() && dir.isDirectory) {
        val files = dir.listFiles()
        if (files != null) {
            for (file in files) {
                try {
                    Files.delete(file.toPath())
                    println("Deleted: ${file.name}")
                } catch (e: IOException) {
                    System.err.println("Failed to delete: ${file.name}")
                }
            }
        }
    }
    println("\nQueue directory cleared.")
}

private fun startPythonGenerator() {
    val pythonScript = "C:\\Users\\sandu\\IdeaProjects\\oop-laboratory-kotlin\\generator.py"
    try {
        val process = Runtime.getRuntime().exec("python $pythonScript")
        println("Python generator started.")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

private fun createCarStations(capacity: Int): List<CarStation> {
    val stations = ArrayList<CarStation>()
    stations.add(CarStation(PeopleDinner(), ElectricStation(), SimpleQueue(capacity), "Electric Human Station"))
    stations.add(CarStation(RobotDinner(), ElectricStation(), SimpleQueue(capacity), "ERobots Station"))
    stations.add(CarStation(PeopleDinner(), GasStation(), SimpleQueue(capacity), "GPeople Station"))
    stations.add(CarStation(RobotDinner(), GasStation(), SimpleQueue(capacity), "GRobots Station"))
    return stations
}

private fun processNewCars(semaphore: Semaphore) {
    val dir = File(QUEUE_DIR)
    if (!dir.exists() || !dir.isDirectory) {
        System.err.println("Queue directory does not exist.")
        return
    }

    // Correct usage of the listFiles with the lambda having two parameters
    val files = dir.listFiles { file, name -> name.endsWith(".json") } // Lambda with (File, String)

    if (files == null || files.isEmpty()) {
        println("\nNo new cars to process.")
        return
    }

    val gson = Gson()
    for (file in files) {
        try {
            // Read and parse the car JSON file
            val content = String(Files.readAllBytes(Paths.get(file.path)))
            val car = gson.fromJson(content, Car::class.java)

            // Route and serve the car
            semaphore.routeCar(car)

            // Delete the processed file
            Files.delete(file.toPath())
            println("Processed and deleted: ${file.name}")
        } catch (e: IOException) {
            System.err.println("Error processing file: ${file.name}")
        }
    }
}
