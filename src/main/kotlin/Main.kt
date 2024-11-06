package lab1

import lab1.task1.runTask as runTask1
import lab1.task2.runTask as runTask2
import lab1.task3.runTask as runTask3
import lab1.task4.runTask as runTask4
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("=== OOP Laboratory Tasks Menu ===")
        println("1. Task 1: Objects & Object Interaction")
        println("2. Task 2: Program Arguments and Text Parsing")
        println("3. Task 3: Class Composition")
        println("4. Task 4: Bonus Task - Process Multiple Files")
        println("0. Exit")
        print("Select a task to run (0 to exit): ")

        when (scanner.nextInt()) {
            1 -> runTask1()
            2 -> runTask2()
            3 -> runTask3()
            4 -> runTask4()
            0 -> {
                println("Exiting the program.")
                return
            }
            else -> println("Invalid option, please try again.")
        }
    }
}
