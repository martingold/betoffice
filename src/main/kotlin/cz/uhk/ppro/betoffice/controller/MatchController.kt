package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.model.repository.MatchRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MatchController(private val matchRepository: MatchRepository) {

    @GetMapping("/matches")
    fun matchesList(model: Model): String {
        model.addAttribute("matches", matchRepository.findAll())
        return "matchesList"
    }
}
