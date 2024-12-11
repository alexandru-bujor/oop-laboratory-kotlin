package task2

class GasStation : Refuelable {
    private var count = 0

    override fun refuel(carId: Int) {
        count++
        println("Refueling gas car $carId..")
    }

    fun getCount(): Int {
        return count
    }
}