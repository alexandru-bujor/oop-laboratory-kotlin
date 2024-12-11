package task3

import task2.GasStation
import task2.PeopleDinner
import task2.ElectricStation
import task2.Dineable
import task2.Refuelable
import task2.RobotDinner
import task1.Car
import task1.Queue

class CarStation(
    private val diningService: Dineable,
    private val refuelingService: Refuelable,
    private val queue: Queue<Car>,
    private val name: String
) {
    private val statistics = mutableMapOf(
        "CarsServed" to 0,
        "Dining" to 0,
        "NotDining" to 0,
        "TotalConsumption" to 0
    )

    fun addCar(car: Car) {
        queue.enqueue(car)
        println("\nAdded car to $name: $car")
    }

    fun serveCars() {
        while (!queue.isEmpty()) {
            val car = queue.dequeue()
            println("Processing car ${car.getId()} from $name.")

            // Update statistics
            statistics["CarsServed"] = statistics.getOrDefault("CarsServed", 0) + 1
            if (car.isDining()) {
                statistics["Dining"] = statistics.getOrDefault("Dining", 0) + 1
                diningService.serveDinner(car.getId())
            } else {
                statistics["NotDining"] = statistics.getOrDefault("NotDining", 0) + 1
            }
            statistics["TotalConsumption"] = statistics.getOrDefault("TotalConsumption", 0) + car.getConsumption()

            // Refuel the car
            refuelingService.refuel(car.getId())
            println("Refueled car ${car.getId()} with consumption ${car.getConsumption()}.")
        }
    }

    fun getStatistics(): Map<String, Int> {
        return statistics
    }

    fun acceptsCar(car: Car): Boolean {
        val matchesDining = (diningService is PeopleDinner && car.getPassengers() == "PEOPLE") ||
                (diningService is RobotDinner && car.getPassengers() == "ROBOTS")
        val matchesFuel = (refuelingService is ElectricStation && car.getType() == "ELECTRIC") ||
                (refuelingService is GasStation && car.getType() == "GAS")
        return matchesDining && matchesFuel
    }

    fun getName(): String {
        return name
    }
}
