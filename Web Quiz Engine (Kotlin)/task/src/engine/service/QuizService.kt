package engine.service

import engine.dto.CreateQuizRequest
import engine.dto.QuizAnswer
import engine.dto.QuizResponse
import engine.dto.SolutionResponse
import engine.entity.Quiz
import engine.repository.QuizRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuizService(
    private val quizRepository: QuizRepository
) {

    fun createQuiz(body: CreateQuizRequest): QuizResponse {
        val quiz = quizRepository.save(
            with(body) {
                Quiz(
                    title = title,
                    text = text,
                    options = options,
                    answer = answer ?: emptyList()
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
        val quiz = quizRepository.findById(id).orElseGet { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        return if (quiz.answer?.toList() == body.answer) {
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

    private fun getQuizResponse(id: Long): QuizResponse {
        val quiz = quizRepository.findById(id).orElseGet { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        return with(quiz) {
            QuizResponse(
                id = id,
                title = title,
                text = text,
                options = options,
            )
        }
    }
}
