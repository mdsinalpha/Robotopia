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

    abstract val totalCost: Long
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
            ?: throw IllegalArgumentException("$head is not compatible with a $model.")
        body.takeIf { it.model in listOf(Body.Model.Skeletonic, Body.Model.Elemental) }
            ?: throw IllegalArgumentException("$body is not compatible with a $model.")
        eye.takeIf { it.model != Eye.Model.Laseric }
            ?: throw IllegalArgumentException("$eye is not compatible with a $model.")
        leftArm.takeIf { it.model in listOf(Arm.Model.Simple, Arm.Model.Sword, Arm.Model.Tablet) }
            ?: throw IllegalArgumentException("$leftArm is not compatible with a $model.")
        rightArm.takeIf { it.model in listOf(Arm.Model.Simple, Arm.Model.Sword) }
            ?: throw IllegalArgumentException("$rightArm is not compatible with a $model.")
    }

    override val totalCost: Long
        get() = head.model.cost + body.model.cost + 2 * eye.model.cost +
                assistant.model.cost + leftArm.model.cost + rightArm.model.cost +
                2 * leg.model.cost + model.cost

    override fun toString() = "HumanStructure(id=$id, totalCost=$totalCost)"
}

open class CatStructure(override val id: Int,
                        val head: Head, val body: Body,
                        val eye: Eye, val assistant: VoiceAssistant,
                        val leg: Leg): Structure(){

    override val model = Model.CatStructure
    override var damaged = false

    init {
        head.takeUnless { it.model in listOf(Head.Model.Metallic, Head.Model.Fiery) }
            ?: throw IllegalArgumentException("$head is not compatible with a $model.")
        body.takeUnless { it.model == Body.Model.Metallic }
            ?: throw IllegalArgumentException("$body is not compatible with a $model.")
        eye.takeUnless { it.model == Eye.Model.Elemental }
            ?: throw IllegalArgumentException("$eye is not compatible with a $model.")
        leg.takeUnless { it.model == Leg.Model.Elemental }
            ?: throw IllegalArgumentException("$leg is not compatible with a $model.")

    }

    override val totalCost: Long
        get() = head.model.cost + body.model.cost + 2 * eye.model.cost +
                assistant.model.cost +4 * leg.model.cost + model.cost

    override fun toString(): String = "CatStructure(id=$id, totalCost=$totalCost)"
}

open class DogStructure(override val id: Int,
                        val head: Head, val body: Body,
                        val eye: Eye, val assistant: VoiceAssistant,
                        val leg: Leg): Structure(){

    override val model = Model.DogStructure
    override var damaged = false

    init {
        head.takeUnless { it.model in listOf(Head.Model.Fiery) }
            ?: throw IllegalArgumentException("$head is not compatible with a $model.")
        eye.takeUnless { it.model == Eye.Model.Laseric }
            ?: throw IllegalArgumentException("$eye is not compatible with a $model.")
        leg.takeUnless { it.model == Leg.Model.Simple }
            ?: throw IllegalArgumentException("$leg is not compatible with a $model.")
    }

    override val totalCost: Long
        get() = head.model.cost + body.model.cost + 2 * eye.model.cost +
                assistant.model.cost + 4 * leg.model.cost + model.cost

    override fun toString(): String = "DogStructure(id=$id, totalCost=$totalCost)"
}

abstract class SquirrelStructure: Structure()

abstract class BirdStructure: Structure()

abstract class BearStructure: Structure()