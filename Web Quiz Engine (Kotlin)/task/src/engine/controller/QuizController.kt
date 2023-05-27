package engine.controller

import engine.dto.CreateQuizRequest
import engine.dto.QuizAnswer
import engine.dto.QuizResponse
import engine.dto.SolutionResponse
import engine.service.QuizService
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/quizzes")
class QuizController(
    private val quizService: QuizService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createQuiz(
        @RequestBody @Valid body: CreateQuizRequest,
        @AuthenticationPrincipal context: UserDetails,
    ): QuizResponse {
        return quizService.createQuiz(context, body)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getQuizById(
        @PathVariable id: Long
    ): QuizResponse {
        return quizService.getQuizById(id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getQuizzes(): List<QuizResponse> {
        return quizService.getQuizzes()
    }

    @PostMapping("/{id}/solve")
    @ResponseStatus(HttpStatus.OK)
    fun solveQuiz(
        @PathVariable id: Long,
        @RequestBody body: QuizAnswer,
    ): SolutionResponse {
        return quizService.solveQuiz(id, body)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteQuiz(
        @PathVariable id: Long,
        @AuthenticationPrincipal context: UserDetails,
    ) {
        quizService.deleteQuiz(context, id)
    }
}