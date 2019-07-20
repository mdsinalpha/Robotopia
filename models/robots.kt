package robotopia.models

sealed class Robot{

    abstract val body: Structure

}

open class ServantRobot(final override val body: Structure):Robot(){

    init{
        when(body){
            is DogStructure, is BirdStructure, is BearStructure ->
                throw IllegalArgumentException("$body structure is not compatible with a ServantRobot.")
        }
    }

}

open class RescuerRobot(final override val body: Structure):Robot(){

    init{
        when(body){
            is CatStructure, is SquirrelStructure ->
                throw IllegalArgumentException("$body structure is not compatible with a RescuerRobot.")
        }
    }
}

open class CompetitorRobot(final override val body: Structure):Robot(){

    init{
        when(body){
            is HumanStructure ->
                throw IllegalArgumentException("$body structure is not compatible with a CompetitorRobot.")
        }
    }
}

open class PetRobot(final override val body: Structure):Robot(){

    init{
        when(body){
            is HumanStructure, is BearStructure ->
                throw IllegalArgumentException("$body structure is not compatible with a PetRobot.")
        }
    }
}

open class TerminatorRobot(final override val body: Structure):Robot(){

    init{
        when(body){
            !is BearStructure ->
                throw IllegalArgumentException("$body structure is not compatible with a PetRobot.")
        }
    }
}

