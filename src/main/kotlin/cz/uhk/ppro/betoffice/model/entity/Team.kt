package cz.uhk.ppro.betoffice.model.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Team(
    @Id @GeneratedValue var id: Long? = null,
    var name: String? = null,
    var wins: Int? = null,
    var loses: Int? = null
)