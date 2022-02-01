package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.dto.BetDto
import cz.uhk.ppro.betoffice.dto.MatchDto
import cz.uhk.ppro.betoffice.model.entity.Bet
import cz.uhk.ppro.betoffice.model.entity.Match
import cz.uhk.ppro.betoffice.model.repository.BetRepository
import cz.uhk.ppro.betoffice.model.repository.MatchRepository
import cz.uhk.ppro.betoffice.model.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
class BetController(
	private val userRepository: UserRepository,
	private val betRepository: BetRepository,
	private val matchRepository: MatchRepository
)
{
	@GetMapping("/user/betlist")
	fun betList(model: Model): String{
		model.addAttribute("matches", matchRepository.findAll())
		model.addAttribute("bet", Bet())
		return "betform"
	}

    @PostMapping("/user/bet")
    fun bet(@ModelAttribute betDto: BetDto, @RequestParam amount: Int, bindingResult: BindingResult): String? {
        val bet = Bet()
        val auth = SecurityContextHolder.getContext().authentication
		val username = auth.name
		val user = userRepository.findByEmail(username).get()
        bet.user = user
		bet.description = "-" + amount
		bet.match = betDto.match
        betRepository.save(bet)
		user.amount = user.amount?.minus(amount)
		userRepository.save(user)
        return "redirect:/user/my-bets"
    }

	@GetMapping("/user/my-bets")
	fun list(model: Model): String {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		val principal = auth.getPrincipal();
		if (principal !is UserDetails) {
			throw Exception("User not logged in");
		}
		val user = userRepository.findByUsername(principal.username).orElseThrow { UsernameNotFoundException("") }
		model.addAttribute("bets", betRepository.findAllByUser(user));
		return "bets"

	}

}