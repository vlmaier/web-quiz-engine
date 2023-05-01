package engine.controller

import engine.model.Feedback
import engine.model.QuizObject
import engine.service.QuizService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/quiz")
class QuizController(
    private val quizService: QuizService
) {

   @GetMapping
   fun getQuestion(): QuizObject {
       return quizService.findQuizObject()
   }

    @PostMapping
    fun answerQuestion(
        @RequestParam answer: Int
    ): Feedback {
        return quizService.answerQuestion(answer)
    }
}