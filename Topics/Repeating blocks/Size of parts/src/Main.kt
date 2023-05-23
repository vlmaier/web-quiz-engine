fun main() {
    val size = readln().toInt()
    val values = List(size) { readln().toInt() }
    println("${values.count { it == 0 }} ${values.count { it == 1 }} ${values.count { it == -1 }}")
}