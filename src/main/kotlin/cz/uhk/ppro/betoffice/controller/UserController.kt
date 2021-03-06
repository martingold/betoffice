package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.dto.UserDto
import cz.uhk.ppro.betoffice.model.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(
	private val userRepository: UserRepository
) {
	@GetMapping("/user/detail")
	fun detail(model: Model): String {
		val auth = SecurityContextHolder.getContext().authentication
		val username = auth.name
		model.addAttribute("user", userRepository.findByEmail(username).get())
		return "profile"
	}

	@GetMapping("/user/profile")
    fun profile(model: Model): String {
		val auth = SecurityContextHolder.getContext().authentication
		val username = auth.name
		val user = userRepository.findByEmail(username).orElseThrow{ UsernameNotFoundException("") }
		model.addAttribute("user", user)
        return "profileForm"
    }

	@PostMapping("/user/profile")
	fun saveProfile(@ModelAttribute userDto: UserDto, bindingResult: BindingResult): String {
		val auth = SecurityContextHolder.getContext().authentication
		val username = auth.name
		val user = userRepository.findByEmail(username).get()
		user.username = userDto.username
		user.email = userDto.email
		user.amount = userDto.amount
		return "redirect:/"
	}

}
