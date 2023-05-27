package engine.entity

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity(name = "quizzes")
data class Quiz(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    val id: Long = 0,
    val title: String,
    val text: String,
    @ElementCollection(fetch = FetchType.EAGER)
    val options: List<String>,
    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection(fetch = FetchType.EAGER)
    val answer: List<Int>? = emptyList(),
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    val createdBy: User,
)