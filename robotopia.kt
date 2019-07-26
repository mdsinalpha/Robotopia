package robotopia
import robotopia.models.Robot
import robotopia.models.User
import kotlin.collections.HashMap
import kotlin.system.exitProcess

object Robotopia{

    private val users: MutableMap<Int, User> = HashMap()
    private val robots: MutableMap<Int, Robot> = HashMap()

    private lateinit var currentUser: User

    val functions: Map<String, () -> Unit> = mapOf(
        "help" to {
            val messages = ArrayList<String>()
            messages.add("Type any command and press enter.")
            functionKeys.forEachIndexed { index, function -> messages.add("${index+1}- $function") }
            Console.printBoxed(*messages.toTypedArray())
        },
        "allUsers" to {
          if(users.isEmpty())
            println("There are no users.")
          else
              Console.printBoxed(*users.values.map { it.toString() }.toTypedArray(), char = '#')
        },
        "register" to {
            println("What's your name?")
            val id = IDGenerator.generate()
            users[id] = User(id, readLine()?:"")
            println("Registered successfully.")
            Console.printDivider()
        },
        "login" to {
            println("What's your id?")
            val id = readLine()?.toInt()?:-1
            if(id in users){
                currentUser = users[id]!!
                println("Hello ${currentUser.name}!")
                println("Logged in successfully.")
                Console.printDivider()
            }
            else{
                println("No such user registered with id=$id.")
                Console.printDivider('x')
            }
        },
        "myInformation" to {
            try {
                val messages = ArrayList<String>()
                messages.add(currentUser.toString())
                messages.add("balance=$${currentUser.balance}")
                messages.add("purchasedRobots:")
                messages.addAll(currentUser.purchasedRobots.values.map { it.toString() })
                Console.printBoxed(*messages.toTypedArray(), char = '~')
            } catch (e:UninitializedPropertyAccessException) {
                println("Please login first.")
                Console.printDivider('x')
            }
        },
        "charge" to {
            try{
                currentUser.balance
                println("How many dollars do you pay?")
                val chargeAmount = readLine()?.toLong()?:0L
                currentUser.chargeBalance(chargeAmount)
                println("Account charged successfully.")
                println("Current balance = ${currentUser.balance}")
                Console.printDivider()
            } catch (e:UninitializedPropertyAccessException) {
                println("Please login first.")
                Console.printDivider('x')
            }
        },
        "exit" to {
            exitProcess(0)
        }
    )
    private val functionKeys = functions.keys
}

object Console {

    fun printDivider(char: Char = '*', count: Int = 35) {
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

object IDGenerator{

    private var last_id = 100

    fun generate() = last_id++
}