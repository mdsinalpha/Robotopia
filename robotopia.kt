package robotopia

import robotopia.models.Robot
import java.io.File

object Robotopia {
    val purchasedRobots: Map<String, List<Robot>> = HashMap()
}

object Console {

    fun printDivider(char: Char = '*', count: Int = 25) {
        println(char.toString().repeat(count))
    }

    fun printBoxed(char: Char = '*', vararg messages: String) {
        val count = messages.maxBy { it.length }!!.length + 25
        printDivider(char = char, count = count)
        messages.forEach { message ->
            val leftSpace = (count - message.length - 2) / 2
            val rightSpace = count - message.length - 2 - leftSpace
            print(char)
            print(" ".repeat(leftSpace))
            print(message)
            print(" ".repeat(rightSpace))
            println(char)
        }
        printDivider(char = char, count = count)
    }

    fun printBoxed(message: String, char: Char = '*') {
        printBoxed(char, message)
    }
}

class IO(val address: String) {
    private val file = File(address)
    private val fileExists
        get() = file.exists()

    fun writeLog(log: String) {
        file.appendText(log)
    }

    init {
        if (!fileExists)
            file.createNewFile()
    }
}