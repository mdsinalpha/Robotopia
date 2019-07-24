package robotopia.models

interface Device {
    val id: Int
    var damaged: Boolean
    val model: Model

    interface Model{
        val cost: Long
    }

}

class Head(override val id: Int, override val model: Model) : Device {
    override var damaged = false

    enum class Model : Device.Model {
        Elemental{
            override val cost = 12L
        }, Skeletonic{
            override val cost = 5L
        }, Metallic{
            override val cost = 25L
        }, Fiery{
            override val cost = 48L
        };
    }

    override fun toString() = "Head(id=$id, model=$model)"
}

class Body(override val id: Int, override val model: Model) : Device {
    override var damaged = false

    enum class Model : Device.Model {
        Elemental{
            override val cost = 36L
        }, Skeletonic{
            override val cost = 15L
        }, Metallic{
            override val cost = 75L
        }, Aluminium{
            override val cost = 60L
        }
    }

    override fun toString() = "Body(id=$id, model=$model)"
}


class Eye(override val id: Int, override val model: Model) : Device {
    override var damaged = false

    enum class Model : Device.Model {
        Simple{
            override val cost = 20L
        }, Elemental{
            override val cost = 37L
        }, Laseric{
            override val cost = 100L
        }, Camera{
            override val cost = 72L
        }
    }

    override fun toString() = "Eye(id=$id, model=$model)"
}

class VoiceAssistant(override val id: Int, override val model: Model) : Device {
    override var damaged = false

    enum class Model : Device.Model {
        Siri{
            override val cost = 95L
        }, Google{
            override val cost = 85L
        }, Cortana{
            override val cost = 80L
        }
    }

    override fun toString() = "VoiceAssistant(id=$id, model=$model)"
}

class Leg(override val id: Int, override val model: Model) : Device {
    override var damaged = false

    enum class Model : Device.Model {
        Simple{
            override val cost = 50L
        }, Elemental{
            override val cost = 65L
        }, Climber{
            override val cost = 100L
        }
    }

    override fun toString() = "Leg(id=$id, model=$model)"
}

class Arm(override val id: Int, override val model: Model) : Device {
    override var damaged = false

    enum class Model : Device.Model {
        Simple{
            override val cost = 30L
        }, Paw{
            override val cost = 44L
        }, Feather{
            override val cost = 66L
        }, Sword{
            override val cost = 80L
        }, Gun{
            override val cost = 99L
        }, RPG{
            override val cost = 150L
        }, Saw{
            override val cost = 85L
        }, Tablet{
            override val cost = 20L
        }, Versatile{
            override val cost = 246L
        }
    }

    override fun toString() = "Arm(id=$id, model=$model)"
}
