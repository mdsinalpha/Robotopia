package robotopia.models

private inline infix fun Structure.Model.isNotCompatibleWith(device: Device): Nothing {
    throw IllegalArgumentException("$device is not compatible with a $this")
}

sealed class Structure : Device {

    enum class Model : Device.Model {
        HumanStructure {
            override val cost: Long = 100
        },
        CatStructure {
            override val cost: Long = 150
        },
        DogStructure {
            override val cost: Long = 200
        },
        SquirrelStructure {
            override val cost: Long = 150
        },
        BirdStructure {
            override val cost: Long = 250
        },
        BearStructure {
            override val cost: Long = 350
        }
    }

    abstract val totalCost: Long
}

open class HumanStructure(
    override val id: Int,
    val head: Head, val body: Body,
    val eye: Eye, val assistant: VoiceAssistant,
    val leftArm: Arm, val rightArm: Arm,
    val leg: Leg
) : Structure() {

    final override val model = Model.HumanStructure
    override var damaged = false

    init {
        head.takeIf { it.model in listOf(Head.Model.Skeletonic, Head.Model.Elemental) }
            ?: model isNotCompatibleWith head
        body.takeIf { it.model in listOf(Body.Model.Skeletonic, Body.Model.Elemental) }
            ?: model isNotCompatibleWith body
        eye.takeIf { it.model != Eye.Model.Laseric }
            ?: model isNotCompatibleWith eye
        leftArm.takeIf { it.model in listOf(Arm.Model.Simple, Arm.Model.Sword, Arm.Model.Tablet) }
            ?: model isNotCompatibleWith leftArm
        rightArm.takeIf { it.model in listOf(Arm.Model.Simple, Arm.Model.Sword) }
            ?: model isNotCompatibleWith rightArm
    }

    override val totalCost: Long
        get() = head.model.cost +
                body.model.cost +
                eye.model.cost * 2 +
                assistant.model.cost +
                leftArm.model.cost +
                rightArm.model.cost +
                leg.model.cost * 2

    override fun toString() = "HumanStructure(id=$id, totalCost=$totalCost)"
}

open class CatStructure : Structure()

open class DogStructure : Structure()

open class SquirrelStructure(
    override val id: Int,
    val head: Head,
    val body: Body,
    val eye: Eye,
    val assistant: VoiceAssistant,
    val leg: Leg
) : Structure() {

    override var damaged = false
    final override val model = Model.SquirrelStructure
    override val totalCost
        get() = model.cost +
                head.model.cost +
                body.model.cost +
                eye.model.cost * 2 +
                assistant.model.cost +
                leg.model.cost * 4

    override fun toString(): String = "SquirrelStructure(id=$id, totalCost=$totalCost)"

    init {
        head.takeIf { it.model == Head.Model.Elemental }
            ?: model isNotCompatibleWith head
        body.takeIf { it.model == Body.Model.Elemental }
            ?: model isNotCompatibleWith body
        eye.takeIf { it.model in listOf(Eye.Model.Laseric, Eye.Model.Simple) }
            ?: model isNotCompatibleWith eye
        leg.takeIf { it.model == Leg.Model.Climber }
            ?: model isNotCompatibleWith leg
    }
}

open class BirdStructure(
    override val id: Int,
    val head: Head,
    val body: Body,
    val eye: Eye,
    val assistant: VoiceAssistant,
    val leg: Leg,
    val arm: Arm
) : Structure() {


    override val totalCost
        get() = model.cost +
                head.model.cost +
                body.model.cost +
                eye.model.cost * 2 +
                assistant.model.cost +
                leg.model.cost * 2 +
                arm.model.cost * 2

    override var damaged = false

    final override val model = Model.BirdStructure

    init {
        head.takeIf { it.model == Head.Model.Elemental }
            ?: model isNotCompatibleWith head
        body.takeIf { it.model == Body.Model.Elemental }
            ?: model isNotCompatibleWith body
        eye.takeIf { it.model in listOf(Eye.Model.Camera, Eye.Model.Simple) }
            ?: model isNotCompatibleWith eye
        leg.takeIf { it.model == Leg.Model.Simple }
            ?: model isNotCompatibleWith leg
        arm.takeIf { it.model == Arm.Model.Feather }
            ?: model isNotCompatibleWith arm
    }

    override fun toString(): String = "BirdStructure(id=$id, totalCost=$totalCost)"
}

open class BearStructure(
    override val id: Int,
    val head: Head,
    val body: Body,
    val assistant: VoiceAssistant,
    val eye: Eye,
    val leg: Leg
) : Structure() {
    override val totalCost
        get() = model.cost +
                head.model.cost +
                body.model.cost +
                assistant.model.cost +
                eye.model.cost * 2 +
                leg.model.cost * 4
    override var damaged = false
    final override val model = Model.BearStructure

    init {
        eye.takeUnless { it.model != Eye.Model.Simple }
            ?: model isNotCompatibleWith head
        leg.takeUnless { it.model != Leg.Model.Elemental }
            ?: model isNotCompatibleWith leg
    }

    override fun toString(): String = "BearStructure(id=$id, totalCost=$totalCost)"
}