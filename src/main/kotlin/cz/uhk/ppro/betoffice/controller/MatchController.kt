package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.model.entity.Match
import cz.uhk.ppro.betoffice.model.repository.MatchRepository
import cz.uhk.ppro.betoffice.model.repository.TeamRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@Controller
class MatchController(
    private val matchRepository: MatchRepository,
    private val teamRepository: TeamRepository
) {

    @GetMapping("/user/matches")
    fun matchesList(model: Model): String {
        model.addAttribute("matches", matchRepository.findAll())
        return "matchesList"
    }

    @GetMapping("/edit-match/{id}")
    fun editMatch(model: Model, @PathVariable id: Int): String {
        val match = matchRepository.findById(id.toLong())
        if (match.isEmpty) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        }
        model.addAttribute("match", match.get());
        model.addAttribute("create", false);
        model.addAttribute("teams", teamRepository.findAll());
        return "matchForm"
    }

    @GetMapping("/create-match")
    fun editMatch(model: Model): String {
        model.addAttribute("match", Match());
        model.addAttribute("create", true);
        model.addAttribute("teams", teamRepository.findAll());
        return "matchForm"
    }

    @PostMapping("/save-match")
    fun processSignup(@ModelAttribute("match") match: Match , bindingResult: BindingResult): String? {
        matchRepository.save(match)
        return "/matches"
    }

}
