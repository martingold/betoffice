package cz.uhk.ppro.betoffice.service

import cz.uhk.ppro.betoffice.dto.UserDto
import cz.uhk.ppro.betoffice.model.entity.User
import cz.uhk.ppro.betoffice.model.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository): UserDetailsService {

	@Autowired
	private val passwordEncoder: BCryptPasswordEncoder? = null

	fun save(userDto: UserDto): User? {
		val user = User(
			username = userDto.username,
			email = userDto.email,
			password = passwordEncoder!!.encode(userDto.password),
			role = User.ROLE_USER
		)
		return userRepository.save(user)
	}

	@Throws(UsernameNotFoundException::class)
	override fun loadUserByUsername(username: String): UserDetails {
		val user = userRepository.findByUsername(username)
			?: throw UsernameNotFoundException("Invalid username or password.")
		return org.springframework.security.core.userdetails.User(
			user.email,
			user.password,
			listOf(SimpleGrantedAuthority(user.role))
		)
	}

}

