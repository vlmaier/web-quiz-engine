package engine.repository

import engine.entity.Quiz
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizRepository : PagingAndSortingRepository<Quiz, Long>