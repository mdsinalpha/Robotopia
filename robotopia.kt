package robotopia
import robotopia.models.Robot
import robotopia.models.User
import kotlin.collections.HashMap
import kotlin.system.exitProcess

object Robotopia{

    val users: MutableMap<Int, User> = HashMap()
    val robots: MutableMap<Int, Robot> = HashMap()

    fun help(){
        Console.printBoxed("1-help", "2-exit")
    }

    fun exit(){
        exitProcess(0)
    }
}

object Console{

    fun printDivider(char:Char='*', count:Int=25){
        println(char.toString().repeat(count))
    }

    fun printBoxed(vararg messages:String, char:Char='*') {
        val count = messages.maxBy { it.length }!!.length + 25
        printDivider(char=char, count=count)
        messages.forEach { message ->
            val leftSpace = (count - message.length - 2) / 2
            val rightSpace = count - message.length - 2 - leftSpace
            print(char)
            print(" ".repeat(leftSpace))
            print(message)
            print(" ".repeat(rightSpace))
            println(char)
        }
        printDivider(char=char, count=count)
    }
}