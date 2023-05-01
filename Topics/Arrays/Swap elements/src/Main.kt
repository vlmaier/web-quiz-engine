import java.util.*

fun main() {
    val numbers = readln().split(' ').map { it.toInt() }.toIntArray()
    // Do not touch lines above
    // Write only exchange actions here.
    val first = numbers.first()
    val last = numbers.last()
    numbers[0] = last
    numbers[numbers.lastIndex] = first
    // Do not touch lines below
    println(numbers.joinToString(separator = " "))
}