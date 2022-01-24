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
		model.addAttribute("user", userRepository.findByUsername("týna"))
		return "index"
	}

}
