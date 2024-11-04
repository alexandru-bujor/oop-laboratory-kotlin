// File: FileReader.kt
import java.io.File

object FileReader {
    fun readFileIntoString(path: String): String {
        return File(path).readText()
    }
}

// File: TextData.kt
class TextData(val fileName: String, private val text: String) {
    val numberOfVowels: Int
    val numberOfConsonants: Int
    val numberOfLetters: Int
    val numberOfSentences: Int
    val longestWord: String

    init {
        numberOfVowels = countVowels()
        numberOfConsonants = countConsonants()
        numberOfLetters = text.count { it.isLetter() }
        numberOfSentences = text.split(". ", "? ", "! ").size
        longestWord = findLongestWord()
    }

    private fun countVowels(): Int {
        return text.count { it.lowercaseChar() in "aeiou" }
    }

    private fun countConsonants(): Int {
        return text.count { it.isLetter() && it.lowercaseChar() !in "aeiou" }
    }

    private fun findLongestWord(): String {
        return text.split("\\s+".toRegex()).maxByOrNull { it.length } ?: ""
    }

    fun printInfo() {
        println("File Name: $fileName")
        println("Number of Vowels: $numberOfVowels")
        println("Number of Consonants: $numberOfConsonants")
        println("Number of Letters: $numberOfLetters")
        println("Number of Sentences: $numberOfSentences")
        println("Longest Word: $longestWord")
    }
}

// Main for Task 2
fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        val filePath = args[0]
        try {
            val fileContent = FileReader.readFileIntoString(filePath)
            val textData = TextData(filePath, fileContent)
            textData.printInfo()
        } catch (e: Exception) {
            println("Error reading file: ${e.message}")
        }
    } else {
        println("Please provide a file path as an argument.")
    }
}
