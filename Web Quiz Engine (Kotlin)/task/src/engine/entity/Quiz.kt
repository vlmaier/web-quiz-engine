package engine.entity

import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity(name = "quiz")
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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val createdBy: User,
)