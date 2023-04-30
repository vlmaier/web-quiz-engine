fun main(args: Array<String>) {
    if (args.size < 3) {
        main(arrayOf("first", "second", "third"))
    } else {
        for (arg in args) {
            println(arg)
        }
    }
}