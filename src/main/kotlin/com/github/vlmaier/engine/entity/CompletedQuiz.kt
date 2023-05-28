package com.github.vlmaier.engine.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*

@Entity(name = "completions")
data class CompletedQuiz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    val id: Long = 0,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "quiz_id", nullable = false)
    val quiz: Quiz,
    @Temporal(TemporalType.TIMESTAMP)
    val completedAt: Date,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val completedBy: User,
)