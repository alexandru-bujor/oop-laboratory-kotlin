package task4
import task3.CarStation
import task1.Car

class Semaphore(private val stations: List<CarStation>) {

    fun routeCar(car: Car) {
        for (station in stations) {
            if (station.acceptsCar(car)) {
                station.addCar(car)
                station.serveCars()
                return
            }
        }
        throw IllegalArgumentException("No CarStation found for car: $car")
    }
}