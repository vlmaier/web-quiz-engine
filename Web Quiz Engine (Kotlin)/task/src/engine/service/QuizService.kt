package engine.service

import engine.dto.CreateQuizRequest
import engine.dto.QuizAnswer
import engine.dto.QuizResponse
import engine.dto.SolutionResponse
import engine.entity.Quiz
import engine.repository.QuizRepository
import engine.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuizService(
    private val quizRepository: QuizRepository,
    private val userRepository: UserRepository,
) {

    fun createQuiz(context: UserDetails, body: CreateQuizRequest): QuizResponse {
        val user = userRepository.findByEmail(context.username)
            .orElseGet { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
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

    fun getQuizById(id: Long): QuizResponse {
        return getQuizResponse(id)
    }

    fun getQuizzes(): List<QuizResponse> {
        return quizRepository.findAll().map { getQuizResponse(it.id) }
    }

    fun solveQuiz(id: Long, body: QuizAnswer): SolutionResponse {
        return if (findQuizById(id).answer?.toList() == body.answer) {
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
            QuizResponse(
                id = id,
                title = title,
                text = text,
                options = options,
            )
        }
    }

    private fun findQuizById(id: Long): Quiz {
        return quizRepository.findById(id).orElseGet { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
    }
}
