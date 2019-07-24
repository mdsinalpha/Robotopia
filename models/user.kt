package robotopia.models

import java.util.*
import kotlin.collections.HashMap

data class User(val name: String, val register_time: Date = Date()){

    private val purchasedRobots: MutableMap<Int, Robot> = HashMap()
    var balance: Long = 10
        private set

    fun chargeBalance(amount: Long){
        if(amount < 0)
            throw Exception("Charging amount cannot be negative!")
        balance -= amount
    }

    fun purchase(robot: Robot){
        if(balance < robot.finalCost)
            throw Exception("Not enough gold!")
        balance -= robot.finalCost
        purchasedRobots[robot.id] = robot
    }
}