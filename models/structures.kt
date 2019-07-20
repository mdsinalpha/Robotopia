package robotopia.models

sealed class Structure {

    abstract val cost: Long
}

open class HumanStructure(
    val head: Head, val body: Body,
    val eye: Eye, val assistant: VoiceAssistant,
    val leftArm: Arm, val rightArm: Arm,
    val leg: Leg
) : Structure() {
    init {
        head.takeIf { it.model in listOf(Head.Model.Skeletonic, Head.Model.Elemental) }
            ?: throw IllegalArgumentException("$head is not compatible with a HumanStructure.")
    }

    override val cost: Long
        get() = 1
}

open class CatStructure : Structure()

open class DogStructure : Structure()

open class SquirrelStructure : Structure()

open class BirdStructure : Structure()

open class BearStructure(
    val head: Head, val body: Body, val eye: List<Eye>, val assistant: VoiceAssistant
    val arms: List<Arm>, val legs: List<Leg>
) : Structure()