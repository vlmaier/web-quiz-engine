class Employee(val clothesSize: Int) {
    inner class Uniform {
        val uniformType = "suit"
        val uniformColor = "blue"
    }

    private val uniform = Uniform()

    fun printUniformInfo() {
        println("The employee wears a ${uniform.uniformColor} ${uniform.uniformType} in size $clothesSize")
    }
}