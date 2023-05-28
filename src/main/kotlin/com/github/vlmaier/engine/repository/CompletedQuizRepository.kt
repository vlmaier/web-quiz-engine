package com.github.vlmaier.engine.repository

import com.github.vlmaier.engine.entity.CompletedQuiz
import com.github.vlmaier.engine.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CompletedQuizRepository : JpaRepository<CompletedQuiz, Long> {

    @Query(
        "SELECT c FROM completions c WHERE c.completedBy.email = :#{#user.email} ORDER BY c.completedAt DESC"
    )
    fun findByUser(user: User, pageable: Pageable): Page<CompletedQuiz>
}
