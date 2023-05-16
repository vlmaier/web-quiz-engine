package engine.controller

import engine.model.CreateQuizRequest
import engine.model.QuizResponse
import engine.model.SolutionResponse
import engine.service.QuizService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/quizzes")
class QuizController(
    private val quizService: QuizService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createQuiz(
        @RequestBody body: CreateQuizRequest
    ): QuizResponse {
        return quizService.createQuiz(body)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getQuizById(
        @PathVariable id: Int
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
        @PathVariable id: Int,
        @RequestParam answer: Int,
    ): SolutionResponse {
        return quizService.solveQuiz(id, answer)
    }
}