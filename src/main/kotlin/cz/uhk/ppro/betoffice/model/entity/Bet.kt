package cz.uhk.ppro.betoffice.model.entity

import java.util.*
import javax.persistence.*

@Entity
class Bet(
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,

    @ManyToMany
    var match: List<Match>? = null,

    var startDate: Date? = null,
    var endDate: Date? = null,
    var description: String? = null,
)
