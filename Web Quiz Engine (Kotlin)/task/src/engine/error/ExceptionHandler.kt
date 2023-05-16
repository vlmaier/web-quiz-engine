package engine.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(QuizNotFoundException::class)
    fun handleQuizNotFound(e: QuizNotFoundException, request: WebRequest): ResponseEntity<CustomErrorMessage> {
        val body = CustomErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            LocalDateTime.now(),
            e.message,
            request.getDescription(false)
        )
        return ResponseEntity<CustomErrorMessage>(body, HttpStatus.NOT_FOUND)
    }
}
