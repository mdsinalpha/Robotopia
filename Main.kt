package robotopia

import java.lang.Exception

fun main() {
    println("Welcome to Robotopia.")
    val helpFunc = Robotopia.functions["help"]!!
    helpFunc()
    var cmd = readLine()
    while (cmd != null){
        try {
            Robotopia.functions[cmd]?.invoke()?:helpFunc()
        } catch (e:Exception){
            Console.printDivider('x')
            println(e.message)
            Console.printDivider('x')
        }
        cmd = readLine()
    }
}
