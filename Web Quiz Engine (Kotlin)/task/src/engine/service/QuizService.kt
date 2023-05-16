package engine.service

import engine.model.CreateQuizRequest
import engine.model.QuizAnswer
import engine.model.QuizResponse
import engine.model.SolutionResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuizService {

    fun createQuiz(body: CreateQuizRequest): QuizResponse {
        val id = nextId++
        quizzes[id] = with(body) {
            QuizObject(
                title = title,
                text = text,
                options = options,
                answer = answer ?: emptyList()
            )
        }
        return getQuizResponse(id)
    }

    fun getQuizById(id: Int): QuizResponse {
        return getQuizResponse(id)
    }

    fun getQuizzes(): List<QuizResponse> {
        return quizzes.keys.map(::getQuizResponse)
    }

    fun solveQuiz(id: Int, body: QuizAnswer): SolutionResponse {
        val quizObject = quizzes[id] ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return if (quizObject.answer == body.answer) {
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

    private fun getQuizResponse(id: Int): QuizResponse {
        val quizObject = quizzes[id] ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return QuizResponse(
            id = id,
            title = quizObject.title,
            text = quizObject.text,
            options = quizObject.options,
        )
    }

    private data class QuizObject(
        val title: String?,
        val text: String?,
        val options: List<String>?,
        val answer: List<Int>?,
    )

    companion object {
        private var nextId = 0
        private val quizzes = mutableMapOf<Int, QuizObject>()
    }
}
