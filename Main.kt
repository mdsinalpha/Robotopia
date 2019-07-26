package robotopia

import kotlin.reflect.full.memberFunctions

fun main() {
    Console.printBoxed("Welcome to Robotopia.")
    val cmd = readLine()!!
    Robotopia::class.memberFunctions.find { it.toString().contains(cmd) }?.call(Robotopia)

}
