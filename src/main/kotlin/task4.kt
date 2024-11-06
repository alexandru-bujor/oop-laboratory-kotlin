package lab1.task4

import java.io.File
import lab1.task2.FileReader
import lab1.task2.TextData

fun main() {
    // Define the path to the resources folder
    val resourceFolderPath = "C:\\dev\\OOP-University-Solutions\\lab1-kotlin\\src\\main\\resources"

    val folder = File(resourceFolderPath)
    if (!folder.isDirectory) {
        println("The 'resources' folder was not found.")
        return
    }

    val fileReader = FileReader()
    val textFiles = folder.listFiles { file -> file.extension == "txt" } ?: emptyArray()

    if (textFiles.isEmpty()) {
        println("No .txt files found in the 'resources' folder.")
        return
    }

    textFiles.forEach { file ->
        val text = fileReader.readFileIntoString(file.path)
        val textData = TextData(file.name, text)
        println(textData)
        println("=".repeat(50))  // Divider for better readability
    }
}



fun runTask() {
    main()
}
