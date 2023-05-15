class Holder<T>(val value: T) {

    fun get(): T {
        return value
    }
}
fun main() {
    // write your code here
    val holder: Holder<String> = Holder<String>("This is an instance of String")

    // Do not change the code below
    val holderValue = holder.get()
    print(holderValue)
}