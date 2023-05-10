fun solution(first: Set<Int>, second: Set<Int>): Set<Int> {
    return first.filter { it % second.size == 0 }.toSet()
}