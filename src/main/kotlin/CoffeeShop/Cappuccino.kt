package CoffeeShop

open class Cappuccino(
    coffeeIntensity: Intensity,
    private val mlOfMilk: Int
) : Coffee(coffeeIntensity, "Cappuccino") {
    override fun printCoffeeDetails() {
        super.printCoffeeDetails()
        println("Milk: $mlOfMilk ml")
    }
}
