package task2

class ElectricStation : Refuelable {
    private var count = 0

    override fun refuel(carId: Int) {
        count++
        println("Refueling electric car $carId..")
    }

    fun getCount(): Int {
        return count
    }
}