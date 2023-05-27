package engine.repository

import engine.entity.CompletedQuiz
import engine.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable

@Repository
interface CompletedQuizRepository : PagingAndSortingRepository<CompletedQuiz, Long> {

    @Query(
        "SELECT c FROM completion c WHERE c.completedBy.email = :#{#user.email} ORDER BY c.completedAt DESC"
    )
    fun findByUser(user: User, pageable: Pageable): Page<CompletedQuiz>
}
