package cz.uhk.ppro.betoffice.model.entity

import javax.persistence.GeneratedValue
import javax.persistence.Id
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class User(
	@Id @GeneratedValue var id: Long? = null,
	var username: String? = null,
	var password: String? = null,
	var email: String? = null,
	var createdAt: LocalDateTime = LocalDateTime.now(),
)
