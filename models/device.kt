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
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a head model!")
    }

    enum class Model : Device.Model
}

class Body(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a body model!")
    }

    enum class Model : Device.Model
}

class Hand(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a hand model!")
    }

    enum class Model : Device.Model
}

class Eye(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not an eye model!")
    }

    enum class Model : Device.Model {
        Elemental,
    }
}

class Ear(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a ear model!")
    }

    enum class Model : Device.Model
}

class Foot(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a foot model!")
    }

    enum class Model : Device.Model
}

class Nose(override val id: Int, override val model: Device.Model) : Device {
    override var damaged = false

    init {
        model.takeIf { it is Model } ?: throw IllegalArgumentException("model is not a nose model!")
    }

    enum class Model : Device.Model
}
