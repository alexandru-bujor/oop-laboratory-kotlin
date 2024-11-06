package lab1.task2

import java.io.File

class FileReader {
    fun readFileIntoString(path: String): String = File(path).readText()

    companion object {
        fun main() {
            val filePath = "C:\\dev\\OOP-University-Solutions\\lab1-kotlin\\src\\main\\resources\\file1.txt"
            if (filePath != null) {
                val fileReader = FileReader()
                val text = fileReader.readFileIntoString(filePath)
                val textData = TextData(filePath, text)
                println(textData)
            } else {
                println("Please provide a file path as an argument.")
            }
        }
    }
}

class TextData(val fileName: String, val text: String) {
    val numberOfVowels = text.count { it.lowercaseChar() in "aeiou" }
    val numberOfConsonants = text.count { it.lowercaseChar() in "bcdfghjklmnpqrstvwxyz" }
    val numberOfLetters = text.count { it.isLetter() }
    val numberOfSentences = text.count { it in ".?!" }
    val longestWord = text.split(Regex("\\W+")).maxByOrNull { it.length } ?: ""

    override fun toString(): String {
        return """
            File: $fileName
            Text: $text
            Vowels: $numberOfVowels
            Consonants: $numberOfConsonants
            Letters: $numberOfLetters
            Sentences: $numberOfSentences
            Longest Word: $longestWord
        """.trimIndent()
    }
}

fun main(args: Array<String>) {
    val filePath = "C:\\dev\\OOP-University-Solutions\\lab1-kotlin\\src\\main\\resources\\file1.txt"
    if (filePath != null) {
        val fileReader = FileReader()
        val text = fileReader.readFileIntoString(filePath)
        val textData = TextData(filePath, text)
        println(textData)
    } else {
        println("Please provide a file path as an argument.")
    }
}

fun runTask(filePath: String = "C:\\dev\\OOP-University-Solutions\\lab1-kotlin\\src\\main\\resources\\file1.txt") {
    val fileReader = FileReader()
    val text = fileReader.readFileIntoString(filePath)
    val textData = TextData(filePath, text)
    println(textData)
}