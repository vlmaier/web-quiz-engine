package com.github.vlmaier.engine.service

import com.github.vlmaier.engine.dto.*
import com.github.vlmaier.engine.entity.CompletedQuiz
import com.github.vlmaier.engine.entity.Quiz
import com.github.vlmaier.engine.entity.User
import com.github.vlmaier.engine.repository.CompletedQuizRepository
import com.github.vlmaier.engine.repository.QuizRepository
import com.github.vlmaier.engine.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val completedQuizRepository: CompletedQuizRepository,
    private val userRepository: UserRepository,
) {

    fun createQuiz(context: UserDetails, body: CreateQuizRequest): QuizResponse {
        val user = findUserByEmail(context.username)
        val quiz = quizRepository.save(
            with(body) {
                Quiz(
                    title = title,
                    text = text,
                    options = options,
                    answer = answer ?: emptyList(),
                    createdBy = user,
                )
            }
        )
        return getQuizResponse(quiz.id)
    }

    fun getQuizById(context: UserDetails, id: Long): QuizResponse {
        return getQuizResponse(id)
    }

    fun getQuizzes(context: UserDetails, page: Int?): Page<QuizResponse> {
        val pageable = PageRequest.of(page ?: 0, 10)
        return quizRepository.findAll(pageable).map { it.toQuizResponse() }
    }

    fun getCompletedQuizzes(context: UserDetails, page: Int?): Page<CompletedQuizResponse> {
        val user = findUserByEmail(context.username)
        val pageable = PageRequest.of(page ?: 0, 10)
        return completedQuizRepository.findByUser(user, pageable).map { it.toCompletedQuizResponse() }
    }

    fun solveQuiz(context: UserDetails, id: Long, body: QuizAnswer): SolutionResponse {
        val user = findUserByEmail(context.username)
        val quiz = findQuizById(id)
        return if (quiz.answer?.toList() == body.answer) {
            completedQuizRepository.save(
                CompletedQuiz(
                    quiz = quiz,
                    completedAt = Date(),
                    completedBy = user
                )
            )
            SolutionResponse(
                success = true,
                feedback = "Congratulations, you're right!"
            )
        } else {
            SolutionResponse(
                success = false,
                feedback = "Wrong answer! Please, try again."
            )
        }
    }

    fun deleteQuiz(context: UserDetails, id: Long) {
        val quiz = findQuizById(id)
        if (context.username != quiz.createdBy.email) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN)
        }
        quizRepository.delete(quiz)
    }

    private fun getQuizResponse(id: Long): QuizResponse {
        return with(findQuizById(id)) {
            toQuizResponse()
        }
    }

    private fun findQuizById(id: Long): Quiz {
        return quizRepository.findById(id).orElseGet { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    private fun findUserByEmail(email: String): User {
        return userRepository.findByEmail(email).orElseGet { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    private fun Quiz.toQuizResponse(): QuizResponse {
        return QuizResponse(
            id = id,
            title = title,
            text = text,
            options = options,
        )
    }

    private fun CompletedQuiz.toCompletedQuizResponse(): CompletedQuizResponse {
        return CompletedQuizResponse(
            id = quiz.id,
            completedAt = completedAt
        )
    }
}
