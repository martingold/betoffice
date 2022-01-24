package cz.uhk.ppro.betoffice.model.repository

import cz.uhk.ppro.betoffice.model.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
	fun findByUsername(username: String): User?
}
