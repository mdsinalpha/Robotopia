package robotopia.models

interface Device {
    val id: Int
    var damaged: Boolean
    val model: Model

    interface Model

}

class Head(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a Head model!")
    }

    enum class Model : Device.Model {
        Elemental, Skeletonic, Metallic, Fiery
    }
}

class Body(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a Body model!")
    }

    enum class Model : Device.Model {
        Elemental, Skeletonic, Metallic, Aluminium
    }
}


class Eye(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not an Eye model!")
    }

    enum class Model : Device.Model {
        Simple, Elemental, Laseric, Camera
    }
}

class VoiceAssistant(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a VoiceAssistant model!")
    }

    enum class Model : Device.Model {
        Siri, Google, Cortana
    }
}

class Leg(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a Leg model!")
    }

    enum class Model : Device.Model {
        Simple, Elemental, Climber
    }
}

class Arm(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not an Arm model!")
    }

    enum class Model : Device.Model {
        Simple, Paw, Feather, Sword, Gun, RPG, Saw, Tablet, Versatile
    }
}
