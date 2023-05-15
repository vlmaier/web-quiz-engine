class Container<T>(t: T) {
    var value = t
}

fun main() {
    val container = Container<String>("Good job!")
    println(container.value)
}