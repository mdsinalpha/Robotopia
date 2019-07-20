package robotopia.models

interface Device {
    val id: Int
    var damaged: Boolean
    val model: Model

    interface Model{
        val cost: Long
    }

}

class Head(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a Head model!")
    }

    enum class Model : Device.Model {
        Elemental{
            override val cost: Long = 12
        }, Skeletonic{
            override val cost: Long = 5
        }, Metallic{
            override val cost: Long = 25
        }, Fiery{
            override val cost: Long = 48
        };
    }
}

class Body(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a Body model!")
    }

    enum class Model : Device.Model {
        Elemental{
            override val cost: Long = 36
        }, Skeletonic{
            override val cost: Long = 15
        }, Metallic{
            override val cost: Long = 75
        }, Aluminium{
            override val cost: Long = 60
        }
    }
}


class Eye(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not an Eye model!")
    }

    enum class Model : Device.Model {
        Simple{
            override val cost: Long = 20
        }, Elemental{
            override val cost: Long = 37
        }, Laseric{
            override val cost: Long = 100
        }, Camera{
            override val cost: Long = 72
        }
    }
}

class VoiceAssistant(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a VoiceAssistant model!")
    }

    enum class Model : Device.Model {
        Siri{
            override val cost: Long = 95
        }, Google{
            override val cost: Long = 85
        }, Cortana{
            override val cost: Long = 80
        }
    }
}

class Leg(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a Leg model!")
    }

    enum class Model : Device.Model {
        Simple{
            override val cost: Long = 50
        }, Elemental{
            override val cost: Long = 65
        }, Climber{
            override val cost: Long = 100
        }
    }
}

class Arm(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not an Arm model!")
    }

    enum class Model : Device.Model {
        Simple{
            override val cost: Long = 30
        }, Paw{
            override val cost: Long = 44
        }, Feather{
            override val cost: Long = 66
        }, Sword{
            override val cost: Long = 80
        }, Gun{
            override val cost: Long = 99
        }, RPG{
            override val cost: Long = 150
        }, Saw{
            override val cost: Long = 85
        }, Tablet{
            override val cost: Long = 20
        }, Versatile{
            override val cost: Long = 246
        }
    }
}
