package cz.uhk.ppro.betoffice.model.entity

import java.util.*
import javax.persistence.*

@Entity
class Match(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @OneToOne
    @JoinColumn(name = "id")
    var team1: Team? = null,

    @OneToOne
    @JoinColumn(name = "id")
    var team2: Team? = null,

    var date: Date? = null,
    var description: String? = null,
    var result: String? = null,

    @OneToOne
    @JoinColumn(name = "id")
    var stream: Stream? = null,

    @ManyToMany
    @JoinTable(
        name = "bet_match",
        joinColumns = [JoinColumn(name = "match")],
        inverseJoinColumns = [JoinColumn(name = "bet")]
    )
    var Bet: List<Bet>? = null

    )

