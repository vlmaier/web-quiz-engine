package engine.service

import engine.model.Feedback
import engine.model.QuizObject
import org.springframework.stereotype.Service

@Service
class QuizService {

    fun findQuizObject(): QuizObject {
        return QuizObject(
            title = "The Java Logo",
            text = "What is depicted on the Java logo?",
            options = listOf(
                "Robot",
                "Tea leaf",
                "Cup of coffee",
                "Bug",
            ),
        )
    }

    fun answerQuestion(answer: Int): Feedback {
        val isCorrect = answer == 2
        val message = if (isCorrect) "Congratulations, you're right!" else "Wrong answer! Please, try again."
        return Feedback(
            success = isCorrect,
            feedback = message
        )
    }
}
