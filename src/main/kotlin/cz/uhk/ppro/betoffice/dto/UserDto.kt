package cz.uhk.ppro.betoffice.dto

import java.util.*

data class UserDto(
	var id: Long,
	val username: String,
	val email: String,
	val password: String,
	val firstName: String,
	val lastName: String,
	val birthDate: Date,
	val amount: Int
)
