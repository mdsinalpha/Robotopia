package robotopia.models

sealed class Robot: Device{

    abstract val structure: Structure

    val finalCost: Long
        get() = structure.totalCost + model.cost

    override var damaged = false
        set(value) {
            field = !field
        }

    enum class Model: Device.Model{
        ServantRobot{
            override val cost: Long = 1000
        }, RescuerRobot{
            override val cost: Long = 1700
        }, CompetitorRobot{
            override val cost: Long = 1800
        }, PetRobot{
            override val cost: Long = 2200
        }, TerminatorRobot{
            override val cost: Long = 5000
        }
    }
}

open class ServantRobot(final override val id: Int, final override val structure: Structure) : Robot() {

    override val model = Model.ServantRobot

    init {
        when (structure) {
            is DogStructure, is BirdStructure, is BearStructure ->
                throw IllegalArgumentException("$structure is not compatible with a ServantRobot.")
        }
    }

    override fun toString() = "ServantRobot(id=$id, model=$model)"
}

open class RescuerRobot(final override val id: Int, final override val structure: Structure) : Robot() {

    override val model = Model.RescuerRobot

    init {
        when (structure) {
            is CatStructure, is SquirrelStructure ->
                throw IllegalArgumentException("$structure is not compatible with a RescuerRobot.")
        }
    }

    override fun toString() = "RescuerRobot(id=$id, model=$model)"

}

open class CompetitorRobot(final override val id: Int, final override val structure: Structure) : Robot() {

    override val model = Model.CompetitorRobot

    init {
        when (structure) {
            is HumanStructure ->
                throw IllegalArgumentException("$structure is not compatible with a CompetitorRobot.")
        }
    }

    override fun toString() = "CompetitorRobot(id=$id, model=$model)"

}

open class PetRobot(final override val id: Int, final override val structure: Structure) : Robot() {

    override val model = Model.PetRobot

    init {
        when (structure) {
            is HumanStructure, is BearStructure ->
                throw IllegalArgumentException("$structure is not compatible with a PetRobot.")
        }
    }

    override fun toString() = "PetRobot(id=$id, model=$model)"
}

open class TerminatorRobot(final override val id: Int, final override val structure: Structure) : Robot() {

    override val model = Model.TerminatorRobot

    init {
        when (structure) {
            !is BearStructure ->
                throw IllegalArgumentException("$structure is not compatible with a PetRobot.")
        }
    }
    override fun toString() = "TerminatorRobot(id=$id, model=$model)"

}

