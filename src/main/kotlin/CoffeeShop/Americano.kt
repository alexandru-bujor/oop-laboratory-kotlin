package CoffeeShop

class Americano(
    coffeeIntensity: Intensity,
    private val mlOfWater: Int
) : Coffee(coffeeIntensity, "Americano") {
    override fun printCoffeeDetails() {
        super.printCoffeeDetails()
        println("Water: $mlOfWater ml")
    }
}
