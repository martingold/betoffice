package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.model.entity.User
import cz.uhk.ppro.betoffice.model.repository.BetRepository
import cz.uhk.ppro.betoffice.model.repository.UserRepository
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BetController(
	private val betRepository: BetRepository,
	private val userRepository: UserRepository,
) {

    @GetMapping("/user/bet")
    fun bet(model: Model): String {
        return "bet"
    }

	@GetMapping("/my-bets")
	fun list(model: Model): String {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		return "bet"
		val principal = auth.getPrincipal();
		if (principal !is UserDetails) {
			throw Exception("User not logged in");
		}
		val user = userRepository.findByUsername(principal.username).orElseThrow { UsernameNotFoundException("") }

		model.addAttribute("bets", betRepository.findAllByUser(user));
		return "bet"

	}

}
