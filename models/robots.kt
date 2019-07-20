package robotopia.models

sealed class Robot: Device{

    abstract val body: Structure

    enum class Model: Device.Model{
        ServantRobot, RescuerRobot, CompetitorRobot, PetRobot, TerminatorRobot
    }
}

open class ServantRobot(final override val id: Int, final override val body: Structure) : Robot() {

    override val model = Model.ServantRobot
    override var damaged = false

    init {
        when (body) {
            is DogStructure, is BirdStructure, is BearStructure ->
                throw IllegalArgumentException("$body is not compatible with a ServantRobot.")
        }
    }
}

open class RescuerRobot(final override val id: Int, final override val body: Structure) : Robot() {

    override val model = Model.RescuerRobot
    override var damaged = false

    init {
        when (body) {
            is CatStructure, is SquirrelStructure ->
                throw IllegalArgumentException("$body is not compatible with a RescuerRobot.")
        }
    }
}

open class CompetitorRobot(final override val id: Int, final override val body: Structure) : Robot() {

    override val model = Model.CompetitorRobot
    override var damaged = false

    init {
        when (body) {
            is HumanStructure ->
                throw IllegalArgumentException("$body is not compatible with a CompetitorRobot.")
        }
    }
}

open class PetRobot(final override val id: Int, final override val body: Structure) : Robot() {

    override val model = Model.PetRobot
    override var damaged = false

    init {
        when (body) {
            is HumanStructure, is BearStructure ->
                throw IllegalArgumentException("$body is not compatible with a PetRobot.")
        }
    }
}

open class TerminatorRobot(final override val id: Int, final override val body: Structure) : Robot() {

    override val model = Model.TerminatorRobot
    override var damaged = false

    init {
        when (body) {
            !is BearStructure ->
                throw IllegalArgumentException("$body is not compatible with a PetRobot.")
        }
    }
}

