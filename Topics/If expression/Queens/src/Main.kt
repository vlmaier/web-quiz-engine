import kotlin.math.abs

fun main() {
    val coordinatesOfTheFirstQueen = readln().split(" ").map { it.toInt() }
    val x1 = coordinatesOfTheFirstQueen.first()
    val y1 = coordinatesOfTheFirstQueen.last()
    val coordinatesOfTheSecondQueen = readln().split(" ").map { it.toInt() }
    val x2 = coordinatesOfTheSecondQueen.first()
    val y2 = coordinatesOfTheSecondQueen.last()

    val horizontallyAligned = abs(x1 - x2) == 0
    val verticallyAligned = abs(y1 - y2) == 0
    val diagonallyAligned = if (horizontallyAligned || verticallyAligned) false else abs((y2 - y1).div(x2 - x1)) == 1

    if (horizontallyAligned || verticallyAligned || diagonallyAligned) {
        println("YES")
    } else {
        println("NO")
    }
}