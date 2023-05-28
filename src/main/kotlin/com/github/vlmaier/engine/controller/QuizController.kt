package com.github.vlmaier.engine.controller

import com.github.vlmaier.engine.dto.*
import com.github.vlmaier.engine.service.QuizService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
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
        @RequestBody @Valid body: CreateQuizRequest,
        @AuthenticationPrincipal context: UserDetails,
    ): QuizResponse {
        return quizService.createQuiz(context, body)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getQuizById(
        @PathVariable id: Long,
        @AuthenticationPrincipal context: UserDetails,
    ): QuizResponse {
        return quizService.getQuizById(context, id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getQuizzes(
        @RequestParam page: Int?,
        @AuthenticationPrincipal context: UserDetails,
    ): Page<QuizResponse> {
        return quizService.getQuizzes(context, page)
    }

    @GetMapping("/completed")
    @ResponseStatus(HttpStatus.OK)
    fun getCompletedQuizzes(
        @RequestParam page: Int?,
        @AuthenticationPrincipal context: UserDetails,
    ): Page<CompletedQuizResponse> {
        return quizService.getCompletedQuizzes(context, page)
    }

    @PostMapping("/{id}/solve")
    @ResponseStatus(HttpStatus.OK)
    fun solveQuiz(
        @PathVariable id: Long,
        @RequestBody body: QuizAnswer,
        @AuthenticationPrincipal context: UserDetails,
    ): SolutionResponse {
        return quizService.solveQuiz(context, id, body)
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