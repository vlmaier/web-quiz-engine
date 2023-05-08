open class Car(val model: String, val speed: Int)

class Bus(private val typeOfBus: String, model: String, speed: Int) : Car(model, speed) {
    fun printInfo() = println("Type of bus: $typeOfBus, model: $model, speed: $speed")
}

fun main() {
    val bus = Bus("Personal", "N4", 130)
    bus.printInfo()
}