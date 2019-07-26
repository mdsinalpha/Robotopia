package robotopia
import robotopia.models.*
import java.lang.Exception
import kotlin.collections.HashMap
import kotlin.system.exitProcess

object Robotopia{

    private val users: MutableMap<Int, User> = HashMap()
    private val devices: MutableMap<Int, Device> = HashMap()
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
        "allDevices" to {
            if(devices.isEmpty())
                println("There are no devices.")
            else
                Console.printBoxed(*devices.values.map { it.toString() }.toTypedArray(), char = '#')
        },
        "createHead" to {
            println("Which model do you want? ${Head.Model.values().map { it.toString() }}")
            val id = IDGenerator.generate()
            devices[id] = Head(id, Head.Model.valueOf(readLine()?:"Elemental"))
            println("${devices[id]} created successfully.")
            Console.printDivider()
        },
        "createBody" to {
            println("Which model do you want? ${Body.Model.values().map { it.toString() }}")
            val id = IDGenerator.generate()
            devices[id] = Body(id, Body.Model.valueOf(readLine()?:"Elemental"))
            println("${devices[id]} created successfully.")
            Console.printDivider()
        },
        "createEye" to {
            println("Which model do you want? ${Eye.Model.values().map { it.toString() }}")
            val id = IDGenerator.generate()
            devices[id] = Eye(id, Eye.Model.valueOf(readLine()?:"Simple"))
            println("${devices[id]} created successfully.")
            Console.printDivider()
        },
        "createVoiceAssistant" to {
            println("Which model do you want? ${VoiceAssistant.Model.values().map { it.toString() }}")
            val id = IDGenerator.generate()
            devices[id] = VoiceAssistant(id, VoiceAssistant.Model.valueOf(readLine()?:"Google"))
            println("${devices[id]} created successfully.")
            Console.printDivider()
        },
        "createLeg" to {
            println("Which model do you want? ${Leg.Model.values().map { it.toString() }}")
            val id = IDGenerator.generate()
            devices[id] = Leg(id, Leg.Model.valueOf(readLine()?:"Simple"))
            println("${devices[id]} created successfully.")
            Console.printDivider()
        },
        "createArm" to {
            println("Which model do you want? ${Arm.Model.values().map { it.toString() }}")
            val id = IDGenerator.generate()
            devices[id] = Arm(id, Arm.Model.valueOf(readLine()?:"Simple"))
            println("${devices[id]} created successfully.")
            Console.printDivider()
        },
        "createHumanStructure" to {
            println("To assemble, enter component device ids as following order:")
            println("head,body,eye,assistant,leftArm,rightArm,leg")
            val params = readLine()!!.split(",").map { devices[it.toInt()] }
            val id = IDGenerator.generate()
            try{
                devices[id] = HumanStructure(id, params[0] as Head, params[1] as Body, params[2] as Eye,
                                            params[3] as VoiceAssistant, params[4] as Arm, params[5] as Arm, params[6] as Leg)
                println("${devices[id]} created successfully.")
                Console.printDivider()
            }catch (e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                println("Are you sure about components existence or compatibility?")
                Console.printDivider('?')
            }
        },
        "createCatStructure" to {
            println("To assemble, enter component device ids as following order:")
            println("head,body,eye,assistant,leg")
            val params = readLine()!!.split(",").map { devices[it.toInt()] }
            val id = IDGenerator.generate()
            try{
                devices[id] = CatStructure(id, params[0] as Head, params[1] as Body, params[2] as Eye,
                                        params[3] as VoiceAssistant, params[4] as Leg)
                println("${devices[id]} created successfully.")
                Console.printDivider()
            }catch (e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                println("Are you sure about components existence or compatibility?")
                Console.printDivider('?')
            }
        },
        "createDogStructure" to {
            println("To assemble, enter component device ids as following order:")
            println("head,body,eye,assistant,leg")
            val params = readLine()!!.split(",").map { devices[it.toInt()] }
            val id = IDGenerator.generate()
            try{
                devices[id] = DogStructure(id, params[0] as Head, params[1] as Body, params[2] as Eye,
                                        params[3] as VoiceAssistant, params[4] as Leg)
                println("${devices[id]} created successfully.")
                Console.printDivider()
            }catch (e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                println("Are you sure about components existence or compatibility?")
                Console.printDivider('?')
            }
        },
        "createSquirrelStructure" to {
            println("To assemble, enter component device ids as following order:")
            println("head,body,eye,assistant,leg")
            val params = readLine()!!.split(",").map { devices[it.toInt()] }
            val id = IDGenerator.generate()
            try{
                devices[id] = SquirrelStructure(id, params[0] as Head, params[1] as Body, params[2] as Eye,
                                                params[3] as VoiceAssistant, params[4] as Leg)
                println("${devices[id]} created successfully.")
                Console.printDivider()
            }catch (e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                println("Are you sure about components existence or compatibility?")
                Console.printDivider('?')
            }
        },
        "createBirdStructure" to {
            println("To assemble, enter component device ids as following order:")
            println("head,body,eye,assistant,leg,arm")
            val params = readLine()!!.split(",").map { devices[it.toInt()] }
            val id = IDGenerator.generate()
            try{
                devices[id] = BirdStructure(id, params[0] as Head, params[1] as Body, params[2] as Eye,
                                            params[3] as VoiceAssistant, params[4] as Leg, params[5] as Arm)
                println("${devices[id]} created successfully.")
                Console.printDivider()
            }catch (e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                println("Are you sure about components existence or compatibility?")
                Console.printDivider('?')
            }
        },"createBearStructure" to {
            println("To assemble, enter component device ids as following order:")
            println("head,body,assistant,eye,leg")
            val params = readLine()!!.split(",").map { devices[it.toInt()] }
            val id = IDGenerator.generate()
            try{
                devices[id] = BearStructure(id, params[0] as Head, params[1] as Body, params[2] as VoiceAssistant,
                    params[3] as Eye, params[4] as Leg)
                println("${devices[id]} created successfully.")
                Console.printDivider()
            }catch (e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                println("Are you sure about components existence or compatibility?")
                Console.printDivider('?')
            }
        },
        "allRobots" to {
            if(robots.isEmpty())
                println("There are no robots.")
            else
                Console.printBoxed(*robots.values.map { it.toString() }.toTypedArray(), char = '#')
        },
        "purchaseServantRobot" to {
            try {
                currentUser.balance
                println("Enter your structure id:")
                val structure = devices[readLine()!!.toInt()]
                val id = IDGenerator.generate()
                val robot = ServantRobot(id, structure as Structure, currentUser)
                currentUser.purchase(robot)
                robots[id] = robot
            }
            catch (e:UninitializedPropertyAccessException) {
                println("Please login first.")
                Console.printDivider('x')
            }
            catch(e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                if(e.message == "Not enough gold!"){
                    println(e.message)
                    Console.printDivider('$')
                }
                else{
                    println("Are you sure about structure existence?")
                    Console.printDivider('?')
                }
            }
        },
        "purchaseRescuerRobot" to {
            try {
                currentUser.balance
                println("Enter your structure id:")
                val structure = devices[readLine()!!.toInt()]
                val id = IDGenerator.generate()
                val robot = RescuerRobot(id, structure as Structure, currentUser)
                currentUser.purchase(robot)
                robots[id] = robot
            }
            catch (e:UninitializedPropertyAccessException) {
                println("Please login first.")
                Console.printDivider('x')
            }
            catch(e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                if(e.message == "Not enough gold!"){
                    println(e.message)
                    Console.printDivider('$')
                }
                else{
                    println("Are you sure about structure existence?")
                    Console.printDivider('?')
                }
            }
        },
        "purchaseCompetitorRobot" to {
            try {
                currentUser.balance
                println("Enter your structure id:")
                val structure = devices[readLine()!!.toInt()]
                val id = IDGenerator.generate()
                val robot = CompetitorRobot(id, structure as Structure, currentUser)
                currentUser.purchase(robot)
                robots[id] = robot
            }
            catch (e:UninitializedPropertyAccessException) {
                println("Please login first.")
                Console.printDivider('x')
            }
            catch(e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                if(e.message == "Not enough gold!"){
                    println(e.message)
                    Console.printDivider('$')
                }
                else{
                    println("Are you sure about structure existence?")
                    Console.printDivider('?')
                }
            }
        },
        "purchasePetRobot" to {
            try {
                currentUser.balance
                println("Enter your structure id:")
                val structure = devices[readLine()!!.toInt()]
                val id = IDGenerator.generate()
                val robot = PetRobot(id, structure as Structure, currentUser)
                currentUser.purchase(robot)
                robots[id] = robot
            }
            catch (e:UninitializedPropertyAccessException) {
                println("Please login first.")
                Console.printDivider('x')
            }
            catch(e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                if(e.message == "Not enough gold!"){
                    println(e.message)
                    Console.printDivider('$')
                }
                else{
                    println("Are you sure about structure existence?")
                    Console.printDivider('?')
                }
            }
        },
        "purchaseTerminatorRobot" to {
            try {
                currentUser.balance
                println("Enter your structure id:")
                val structure = devices[readLine()!!.toInt()]
                val id = IDGenerator.generate()
                val robot = TerminatorRobot(id, structure as Structure, currentUser)
                currentUser.purchase(robot)
                robots[id] = robot
            }
            catch (e:UninitializedPropertyAccessException) {
                println("Please login first.")
                Console.printDivider('x')
            }
            catch(e:IllegalArgumentException){
                println(e.message)
                Console.printDivider('^')
            }
            catch(e:Exception){
                if(e.message == "Not enough gold!"){
                    println(e.message)
                    Console.printDivider('$')
                }
                else{
                    println("Are you sure about structure existence?")
                    Console.printDivider('?')
                }
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