import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

val coffeeProfilesFilePath = "src/main/resources/coffeeprofiles.json"  // Path to JSON file

fun main() {
    val scanner = Scanner(System.`in`)
    var barista = User(1, "John Doe", "johndoe", "john@example.com", "1234567890", "Barista", "2024-11-18") // Example barista

    println("Select an action:")
    println("1. Log in")
    println("2. Exit")

    var themenu = true
    while (themenu) {
        when (scanner.nextInt()) {
            1 -> {
                scanner.nextLine()  // Consume newline
                println("Enter the username:")
                val username = scanner.nextLine()
                barista = User(1, "John Doe", username, "john@example.com", "1234567890", "Barista", "2024-11-18")
                baristaMenu(scanner, barista)
            }
            2 -> themenu = false
            else -> println("Invalid choice.")
        }
    }
}

data class User(val id: Int, val name: String, val username: String, val email: String, val phone_number: String, val role: String, val created_at: String)

fun doesUsernameExist(username: String, users: List<User>): Boolean {
    return users.any { it.username == username }
}

fun baristaMenu(scanner: Scanner, barista: User) {
    val coffeeProfiles = loadCoffeeProfiles()
    var coffeeProfile = coffeeProfiles.find { it.baristaId == barista.id } ?: CoffeeProfile(barista.id)
    var continueMenu = true

    while (continueMenu) {
        println("Barista Menu:")
        println("1. Set coffee preferences")
        println("2. Edit coffee preferences")
        println("3. Make coffee")
        println("4. Exit")

        when (scanner.nextInt()) {
            1 -> {
                coffeeProfile = setCoffeePreferences(scanner, coffeeProfile)
                saveCoffeeProfiles(coffeeProfiles, coffeeProfile)
            }
            2 -> {
                coffeeProfile = editCoffeePreferences(scanner, coffeeProfile)
                saveCoffeeProfiles(coffeeProfiles, coffeeProfile)
            }
            3 -> makeCoffee(coffeeProfile, barista)
            4 -> continueMenu = false
            else -> println("Invalid choice.")
        }
    }
}

fun setCoffeePreferences(scanner: Scanner, coffeeProfile: CoffeeProfile): CoffeeProfile {
    println("Set your coffee preferences:")

    println("Cappuccino - Intensity (1: LIGHT, 2: NORMAL, 3: STRONG):")
    coffeeProfile.cappuccino.intensity = Intensity.values()[scanner.nextInt() - 1]

    print("Cappuccino - Milk (ml): ")
    coffeeProfile.cappuccino.mlOfMilk = scanner.nextInt()

    println("Americano - Intensity (1: LIGHT, 2: NORMAL, 3: STRONG):")
    coffeeProfile.americano.intensityOfCoffee = Intensity.values()[scanner.nextInt() - 1]

    print("Americano - Water (ml): ")
    coffeeProfile.americano.mlOfWater = scanner.nextInt()

    println("Pumpkin Spice Latte - Coffee Intensity (1: LIGHT, 2: NORMAL, 3: STRONG):")
    coffeeProfile.pumpkinSpiceLatte.intensityOfCoffee = Intensity.values()[scanner.nextInt() - 1]

    print("Pumpkin Spice Latte - Milk (ml): ")
    coffeeProfile.pumpkinSpiceLatte.mlOfMilk = scanner.nextInt()

    print("Pumpkin Spice Latte - Pumpkin Spice (mg): ")
    coffeeProfile.pumpkinSpiceLatte.mgOfPumpkinSpice = scanner.nextInt()

    println("Syrup Cappuccino - Coffee Intensity (1: LIGHT, 2: NORMAL, 3: STRONG):")
    coffeeProfile.syrupCappuccino.intensityCoffee = Intensity.values()[scanner.nextInt() - 1]

    print("Syrup Cappuccino - Milk (ml): ")
    coffeeProfile.syrupCappuccino.mltrOfMilk = scanner.nextInt()

    scanner.nextLine()  // Consume newline
    println("Syrup Cappuccino - Syrup Type (1: MACADAMIA, 2: VANILLA, 3: COCONUT, 4: CARAMEL, 5: CHOCOLATE, 6: POPCORN):")
    coffeeProfile.syrupCappuccino.syrup = SyrupType.values()[scanner.nextInt() - 1]

    println("Preferences saved!")
    return coffeeProfile
}

fun editCoffeePreferences(scanner: Scanner, coffeeProfile: CoffeeProfile): CoffeeProfile {
    println("Edit your coffee preferences:")

    println("Cappuccino - Intensity [${coffeeProfile.cappuccino.intensity}]:")
    coffeeProfile.cappuccino.intensity = Intensity.values()[scanner.nextInt() - 1]

    print("Cappuccino - Milk (ml) [${coffeeProfile.cappuccino.mlOfMilk}]: ")
    coffeeProfile.cappuccino.mlOfMilk = scanner.nextInt()

    println("Americano - Intensity [${coffeeProfile.americano.intensityOfCoffee}]:")
    coffeeProfile.americano.intensityOfCoffee = Intensity.values()[scanner.nextInt() - 1]

    print("Americano - Water (ml) [${coffeeProfile.americano.mlOfWater}]: ")
    coffeeProfile.americano.mlOfWater = scanner.nextInt()

    println("Pumpkin Spice Latte - Coffee Intensity [${coffeeProfile.pumpkinSpiceLatte.intensityOfCoffee}]:")
    coffeeProfile.pumpkinSpiceLatte.intensityOfCoffee = Intensity.values()[scanner.nextInt() - 1]

    print("Pumpkin Spice Latte - Milk (ml) [${coffeeProfile.pumpkinSpiceLatte.mlOfMilk}]: ")
    coffeeProfile.pumpkinSpiceLatte.mlOfMilk = scanner.nextInt()

    print("Pumpkin Spice Latte - Pumpkin Spice (mg) [${coffeeProfile.pumpkinSpiceLatte.mgOfPumpkinSpice}]: ")
    coffeeProfile.pumpkinSpiceLatte.mgOfPumpkinSpice = scanner.nextInt()

    println("Syrup Cappuccino - Coffee Intensity [${coffeeProfile.syrupCappuccino.intensityCoffee}]:")
    coffeeProfile.syrupCappuccino.intensityCoffee = Intensity.values()[scanner.nextInt() - 1]

    print("Syrup Cappuccino - Milk (ml) [${coffeeProfile.syrupCappuccino.mltrOfMilk}]: ")
    coffeeProfile.syrupCappuccino.mltrOfMilk = scanner.nextInt()

    scanner.nextLine()  // Consume newline
    println("Syrup Cappuccino - Syrup Type [${coffeeProfile.syrupCappuccino.syrup}]:")
    coffeeProfile.syrupCappuccino.syrup = SyrupType.values()[scanner.nextInt() - 1]

    println("Preferences updated!")
    return coffeeProfile
}

fun makeCoffee(coffeeProfile: CoffeeProfile, barista: User) {
    println("Making coffee based on your saved preferences:")

    println("\nCappuccino by ${barista.name}:")
    println("Intensity: ${coffeeProfile.cappuccino.intensity}, Milk: ${coffeeProfile.cappuccino.mlOfMilk} ml")

    println("\nAmericano by ${barista.name}:")
    println("Intensity: ${coffeeProfile.americano.intensityOfCoffee}, Water: ${coffeeProfile.americano.mlOfWater} ml")

    println("\nPumpkin Spice Latte by ${barista.name}:")
    println("Intensity: ${coffeeProfile.pumpkinSpiceLatte.intensityOfCoffee}, Milk: ${coffeeProfile.pumpkinSpiceLatte.mlOfMilk} ml, Pumpkin Spice: ${coffeeProfile.pumpkinSpiceLatte.mgOfPumpkinSpice} mg")

    println("\nSyrup Cappuccino by ${barista.name}:")
    println("Intensity: ${coffeeProfile.syrupCappuccino.intensityCoffee}, Milk: ${coffeeProfile.syrupCappuccino.mltrOfMilk} ml, Syrup: ${coffeeProfile.syrupCappuccino.syrup}")
}

fun saveCoffeeProfiles(coffeeProfiles: MutableList<CoffeeProfile>, updatedProfile: CoffeeProfile) {
    coffeeProfiles.removeIf { it.baristaId == updatedProfile.baristaId }
    coffeeProfiles.add(updatedProfile)
    val jsonString = Json.encodeToString(coffeeProfiles)
    File(coffeeProfilesFilePath).writeText(jsonString)
    println("Coffee preferences saved to coffee_profiles.json.")
}

fun loadCoffeeProfiles(): MutableList<CoffeeProfile> {
    val file = File(coffeeProfilesFilePath)
    if (!file.exists()) {
        file.createNewFile()
        file.writeText("[]")  // Writing an empty JSON array
    }
    val jsonString = file.readText()
    return if (jsonString.isNotBlank()) Json.decodeFromString(jsonString) else mutableListOf()
}

