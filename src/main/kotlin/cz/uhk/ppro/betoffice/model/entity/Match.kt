package cz.uhk.ppro.betoffice.model.entity

import java.util.*
import javax.persistence.*

@Entity
class Match(
    @Id @GeneratedValue var id: Long? = null,

    @OneToOne
    var team1: Team? = null,

    @OneToOne
    var team2: Team? = null,

    var date: Date? = null,
    var description: String? = null,
    var result: String? = null,

    @OneToOne
    var stream: Stream? = null,

    @ManyToMany
    var Bet: List<Bet>? = null

    )

