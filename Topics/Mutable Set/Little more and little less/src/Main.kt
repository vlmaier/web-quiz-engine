fun solution(newSet: MutableSet<String>, oldSet: Set<String>): MutableSet<String> {
    oldSet.filter { it.startsWith("a", ignoreCase = true) }.toCollection(newSet)
    return newSet
}