package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.model.entity.User
import cz.uhk.ppro.betoffice.model.repository.UserRepository
import cz.uhk.ppro.betoffice.util.DateUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class IndexController(private val userRepository: UserRepository) {

	@GetMapping("/")
	fun homepage(model: Model): String {
		return "homepage"
	}

	@GetMapping("/user")
	fun index(model: Model): String {
		return "index"
	}

	@GetMapping("/login")
	fun login(model: Model): String {
		return "login"
	}

	@GetMapping("/user/logout")
	fun logout(model: Model): String {
		return "logout"
	}

	@GetMapping("/signup")
	fun showSignupForm(model: Model): String {
		model.addAttribute("user", User())
		return "signup"
	}

	@PostMapping("/signup")
	fun processSignup(@RequestParam("birthDate") date: String, user: User, bindingResult: BindingResult): String? {
		val passwordEncoder = BCryptPasswordEncoder()
		val encodedPassword = passwordEncoder.encode(user.password)
		user.password = encodedPassword
		user.role = User.ROLE_USER
		user.birthDate = DateUtils.parseDate(date)
		user.amount = 200
		System.out.println(user.birthDate)
		//todo kontrola existujícího emailu
		userRepository.save(user)
		return "homepage"
	}
}
