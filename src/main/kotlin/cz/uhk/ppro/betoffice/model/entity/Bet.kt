package cz.uhk.ppro.betoffice.model.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Bet(
    @Id @GeneratedValue var id: Long? = null,

    @OneToMany
    var match: List<Match>? = null,

    var startDate: Date? = null,
    var endDate: Date? = null,
    var description: String? = null,
)