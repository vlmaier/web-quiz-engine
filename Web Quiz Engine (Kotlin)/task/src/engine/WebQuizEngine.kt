package engine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class WebQuizEngine {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<WebQuizEngine>(*args)
        }
    }
}
