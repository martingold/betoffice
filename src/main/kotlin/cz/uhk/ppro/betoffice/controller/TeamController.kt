package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.dto.TeamDto
import cz.uhk.ppro.betoffice.model.entity.Match
import cz.uhk.ppro.betoffice.model.entity.Team
import cz.uhk.ppro.betoffice.model.repository.TeamRepository
import cz.uhk.ppro.betoffice.util.DateUtils
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.server.ResponseStatusException

@Controller
class TeamController(private val teamRepository: TeamRepository) {

    @GetMapping("/user/teams")
    fun bet(model: Model): String {
        model.addAttribute("teams", teamRepository.findAll())
        return "teamList"
    }

    @GetMapping("/edit-team/{id}")
    fun editTeam(model: Model, @PathVariable id: Int): String {
        val team = teamRepository.findById(id.toLong())
        if (team.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        }
        model.addAttribute("team", team.get());
        model.addAttribute("create", false);
        return "teamForm"
    }

    @GetMapping("/create-team")
    fun editTeam(model: Model): String {
        model.addAttribute("team", Team());
        model.addAttribute("create", true);
        return "teamForm"
    }

    @PostMapping("/save-team")
    fun processSignup(@ModelAttribute teamDto: TeamDto, bindingResult: BindingResult): String? {
        val team = if (teamDto.id !== null) teamRepository.findById(teamDto.id!!).get() else Team()
        team.name = teamDto.name
        team.loses = teamDto.loses
        team.wins = teamDto.wins

        teamRepository.save(team)
        return "redirect:/user/teams"
    }
}
