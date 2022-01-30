package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.model.entity.Match
import cz.uhk.ppro.betoffice.model.repository.MatchRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException


@Controller
class MatchController(private val matchRepository: MatchRepository) {

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
        return "matchForm"
    }

    @GetMapping("/create-match")
    fun editMatch(model: Model): String {
        model.addAttribute("match", Match());
        model.addAttribute("create", true);
        return "matchForm"
    }

    @PostMapping("/save-match")
    fun processSignup(match: Match, bindingResult: BindingResult): String? {
        matchRepository.save(match)
        return "/matches"
    }

}
