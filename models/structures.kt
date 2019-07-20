package robotopia.models

sealed class Structure

open class HumanStructure: Structure()

open class CatStructure: Structure()

open class DogStructure: Structure()

open class SquirrelStructure: Structure()

open class BirdStructure: Structure()

open class BearStructure: Structure()