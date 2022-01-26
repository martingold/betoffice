package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.model.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController(private val userRepository: UserRepository) {

	@GetMapping("/")
	fun index(model: Model): String {
		model.addAttribute("user", userRepository.findByUsername("tyna"))
		return "index"
	}

	@GetMapping("/matches")
	fun matchesList(model: Model): String {
		return "matchesList"
	}

	@GetMapping("/login")
	fun login(model: Model): String {
		return "login"
	}

	@GetMapping("/logout")
	fun logout(model: Model): String {
		return "logout"
	}

	@GetMapping("/bet")
	fun bet(model: Model): String {
		return "bet"
	}

	@GetMapping("/profile")
	fun profile(model: Model): String {
		return "profile"
	}
}
