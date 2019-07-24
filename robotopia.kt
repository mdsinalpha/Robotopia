package robotopia
import robotopia.models.Robot

object Robotopia{
    val purchased_robots: Map<String, List<Robot>> = HashMap()
}

object IO{

    fun printDivider(char:Char='*', count:Int=25){
        println(char.toString().repeat(count))
    }

    fun printBoxed(messages:List<String>, char:Char='*') {
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

    fun printBoxed(message:String, char:Char='*'){
        printBoxed(listOf(message), char=char)
    }
}