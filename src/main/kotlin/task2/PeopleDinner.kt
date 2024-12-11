package task2;

class PeopleDinner : Dineable {
    private var count = 0

    override fun serveDinner(carId: Int) {
        count++
        println("Serving the dinner to people in car $carId.")
    }

    fun getCount(): Int {
        return count
    }
}