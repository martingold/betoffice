package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.model.entity.User
import cz.uhk.ppro.betoffice.model.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


@Controller
class IndexController(private val userRepository: UserRepository) {

	@GetMapping("/")
	fun homepage(model: Model): String {
		return "homepage"
	}

	@GetMapping("/user/")
	fun index(model: Model): String {
		return "index"
	}

	@GetMapping("/user/matches")
	fun matchesList(model: Model): String {
		return "matchesList"
	}

	@GetMapping("/login")
	fun login(model: Model): String {
		model.addAttribute("user", userRepository.findByUsername("tyna"))
		return "login"
	}

	@GetMapping("/user/logout")
	fun logout(model: Model): String {
		return "logout"
	}

	@GetMapping("/user/bet")
	fun bet(model: Model): String {
		return "bet"
	}

	@GetMapping("/user/profile")
	fun profile(model: Model): String {
		return "profile"
	}

	@GetMapping("/registration")
	fun showRegistrationForm(model: Model): String {
		model.addAttribute("user", User())
		return "registration"
	}

	@PostMapping("/registration")
	fun processRegister(user: User, bindingResult: BindingResult): String? {
		val passwordEncoder = BCryptPasswordEncoder()
		val encodedPassword = passwordEncoder.encode(user.password)
		user.password = encodedPassword
		user.role = User.ROLE_USER
		//todo kontrola existujícího emailu
		userRepository.save(user)
		return "homepage"
	}
}
