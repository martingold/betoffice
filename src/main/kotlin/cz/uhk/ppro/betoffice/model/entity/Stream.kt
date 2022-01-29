package cz.uhk.ppro.betoffice.model.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Stream {
    @Id @GeneratedValue var id: Long? = null
}