package cz.uhk.ppro.betoffice.model.entity

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
class Bet(
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

    @ManyToMany
    @JoinTable(
        name = "bet_match",
        joinColumns = [JoinColumn(name = "bet")],
        inverseJoinColumns = [JoinColumn(name = "match")]
    )
    var match: List<Match>? = null,

    var startDate: Date? = Calendar.getInstance().time,
    var endDate: Date? = null,
    var description: String? = null,
    @ManyToOne
    @JoinColumn(name = "user")
    var user: User? = null
)
