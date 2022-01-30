package cz.uhk.ppro.betoffice.model.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class User(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null,
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var role: String? = null,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var firstName: String? = null,
    var lastName: String? = null,
    var birthDate: Date? = null,
    var amount: Int? = null,

    @OneToMany
    var bet: List<Bet>? = null
) {
	companion object {
		const val ROLE_USER = "user"
		const val ROLE_ADMIN = "admin"
	}
}
