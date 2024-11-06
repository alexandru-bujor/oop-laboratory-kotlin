import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

fun main() {
    val mapper = ObjectMapper()
    val inputFile = File("src/main/resources/input.json")
    val data = mapper.readTree(inputFile).get("data")

    val starWars = Universe("Star Wars")
    val hitchhikers = Universe("Hitchhikers")
    val marvel = Universe("Marvel")
    val rings = Universe("Lord of the Rings")

    val rules = listOf(
        ::starWarsRule,
        ::hitchhikersRule,
        ::marvelRule,
        ::lordOfTheRingsRule
    )

    for (entry in data) {
        classifyAlien(entry, rules, starWars, hitchhikers, marvel, rings)
    }

    println("Star Wars contains ${starWars.individuals.size} aliens")
    println("Hitchhikers contains ${hitchhikers.individuals.size} aliens")
    println("Marvel contains ${marvel.individuals.size} aliens")
    println("Lord of the Rings contains ${rings.individuals.size} aliens")

    mapper.writeValue(File("src/main/resources/output/starwars.json"), starWars)
    mapper.writeValue(File("src/main/resources/output/hitchhikers.json"), hitchhikers)
    mapper.writeValue(File("src/main/resources/output/marvel.json"), marvel)
    mapper.writeValue(File("src/main/resources/output/rings.json"), rings)
}

fun classifyAlien(
    entry: JsonNode,
    rules: List<(JsonNode) -> String?>,
    starWars: Universe,
    hitchhikers: Universe,
    marvel: Universe,
    rings: Universe
) {
    val classification = rules.asSequence()
        .mapNotNull { it(entry) }
        .firstOrNull() ?: "Unknown"

    when (classification) {
        "Star Wars" -> starWars.individuals.add(entry.toString())
        "Hitchhikers" -> hitchhikers.individuals.add(entry.toString())
        "Marvel" -> marvel.individuals.add(entry.toString())
        "Lord of the Rings" -> rings.individuals.add(entry.toString())
    }
}

fun starWarsRule(entry: JsonNode): String? {
    val isHumanoid = entry.get("isHumanoid")?.asBoolean() ?: false
    val traits = entry.get("traits")?.map { it.asText() } ?: emptyList()
    return if (!isHumanoid && traits.containsAll(listOf("HAIRY", "TALL"))) "Star Wars" else null
}

fun hitchhikersRule(entry: JsonNode): String? {
    val isHumanoid = entry.get("isHumanoid")?.asBoolean() ?: false
    val traits = entry.get("traits")?.map { it.asText() } ?: emptyList()
    return if (isHumanoid && traits.containsAll(listOf("EXTRA_ARMS", "EXTRA_HEAD"))) "Hitchhikers" else null
}

fun marvelRule(entry: JsonNode): String? {
    val isHumanoid = entry.get("isHumanoid")?.asBoolean() ?: false
    val planet = entry.get("planet")?.asText() ?: ""
    return if (isHumanoid && planet == "Earth") "Marvel" else null
}

fun lordOfTheRingsRule(entry: JsonNode): String? {
    val isHumanoid = entry.get("isHumanoid")?.asBoolean() ?: false
    val traits = entry.get("traits")?.map { it.asText() } ?: emptyList()
    return if (!isHumanoid && traits.contains("GREEN")) "Lord of the Rings" else null
}

class Universe(val name: String) {
    val individuals: MutableList<String> = mutableListOf()
}
