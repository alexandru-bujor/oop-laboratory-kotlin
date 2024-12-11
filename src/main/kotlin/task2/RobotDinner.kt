package task2

class RobotDinner : Dineable {
    private var count = 0

    override fun serveDinner(carId: Int) {
        count++
        println("Serving dinner to robots in car $carId.")
    }

    fun getCount(): Int {
        return count
    }
}