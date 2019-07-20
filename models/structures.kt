package robotopia.models

sealed class Structure: Device{

    enum class Model: Device.Model{
        HumanStructure{
            override val cost: Long = 100
        }, CatStructure{
            override val cost: Long = 150
        }, DogStructure{
            override val cost: Long = 200
        }, SquirrelStructure{
            override val cost: Long = 150
        }, BirdStructure{
            override val cost: Long = 250
        }, BearStructure{
            override val cost: Long = 350
        }
    }

    abstract val cost: Long
}

open class HumanStructure(override val id:Int,
                          val head: Head, val body: Body,
                          val eye: Eye, val assistant: VoiceAssistant,
                          val leftArm: Arm, val rightArm: Arm,
                          val leg: Leg): Structure(){

    override val model = Model.HumanStructure
    override var damaged = false

    init {
        head.takeIf { it.model in listOf(Head.Model.Skeletonic, Head.Model.Elemental) }
            ?: throw IllegalArgumentException("$head is not compatible with a HumanStructure.")
        body.takeIf { it.model in listOf(Body.Model.Skeletonic, Body.Model.Elemental) }
            ?: throw IllegalArgumentException("$body is not compatible with a HumanStructure.")
        eye.takeIf { it.model != Eye.Model.Laseric }
            ?: throw IllegalArgumentException("$eye is not compatible with a HumanStructure.")
        leftArm.takeIf { it.model in listOf(Arm.Model.Simple, Arm.Model.Sword, Arm.Model.Tablet) }
            ?: throw IllegalArgumentException("$leftArm is not compatible with a HumanStructure.")
        rightArm.takeIf { it.model in listOf(Arm.Model.Simple, Arm.Model.Sword) }
            ?: throw IllegalArgumentException("$rightArm is not compatible with a HumanStructure.")
    }

    override val cost: Long
        get() = head.model.cost + body.model.cost + 2 * eye.model.cost +
                assistant.model.cost + leftArm.model.cost + rightArm.model.cost +
                2 * leg.model.cost

    override fun toString() = "HumanStructure(id=$id, cost=$cost)"
}

open class CatStructure: Structure()

open class DogStructure: Structure()

open class SquirrelStructure: Structure()

open class BirdStructure: Structure()

open class BearStructure: Structure()