package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.model.repository.MatchRepository
import cz.uhk.ppro.betoffice.model.repository.TeamRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TeamController(private val teamRepository: TeamRepository) {

    @GetMapping("/teams")
    fun bet(model: Model): String {
        model.addAttribute("teams", teamRepository.findAll())
        return "teamList"
    }
}