import CoffeeShop.*

class Barista {
    private val coffeeList = mutableListOf<Coffee>()

    fun orderCoffee(coffee: Coffee) {
        coffeeList.add(coffee)
        println("Order placed for: ${coffee.name}")
    }

    fun brewAll() {
        println("\nBrewing all coffees...")
        coffeeList.forEach {
            println("\nBrewing ${it.name}:")
            it.printCoffeeDetails()
        }
    }

    fun makeCappuccino(): Cappuccino {
        println("Making a Cappuccino...")
        return Cappuccino(Intensity.NORMAL, 200)
    }

    fun makePumpkinSpiceLatte(): PumpkinSpiceLatte {
        println("Making a Pumpkin Spice Latte...")
        return PumpkinSpiceLatte(Intensity.STRONG, 150, 50)
    }

    fun makeAmericano(): Americano {
        println("Making an Americano...")
        return Americano(Intensity.LIGHT, 300)
    }

    fun makeSyrupCappuccino(): SyrupCappuccino {
        println("Making a Syrup Cappuccino...")
        return SyrupCappuccino(Intensity.NORMAL, 200, SyrupType.CARAMEL)
    }
}
