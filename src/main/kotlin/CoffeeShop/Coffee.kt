package CoffeeShop

open class Coffee(
    val coffeeIntensity: Intensity,
    val name: String = "Coffee"
) {
    open fun printCoffeeDetails() {
        println("Coffee Name: $name")
        println("Intensity: $coffeeIntensity")
    }
}
