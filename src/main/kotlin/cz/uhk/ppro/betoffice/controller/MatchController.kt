package cz.uhk.ppro.betoffice.controller

import cz.uhk.ppro.betoffice.dto.MatchDto
import cz.uhk.ppro.betoffice.model.entity.Match
import cz.uhk.ppro.betoffice.model.repository.MatchRepository
import cz.uhk.ppro.betoffice.model.repository.TeamRepository
import cz.uhk.ppro.betoffice.util.DateUtils
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

    @GetMapping("/matches")
    fun matchesList(model: Model): String {
        val matches: Iterable<Match> = matchRepository.findAll()
        val football: MutableList<Match> = mutableListOf()
        val hockey: MutableList<Match> = mutableListOf()
        val others: MutableList<Match> = mutableListOf()
        matches.forEach{
            if (it.description == "Fotbal")
                football.add(it)
            else if (it.description == "Hokej")
                hockey.add(it)
            else
                others.add(it)
        }
        model.addAttribute("football", football)
        model.addAttribute("hockey", hockey)
        model.addAttribute("others", others)
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
    fun processSignup(@ModelAttribute matchDto: MatchDto , bindingResult: BindingResult): String? {
        val match = matchRepository.findById(matchDto.id!!).orElse(Match())

        match.description = matchDto.description
        match.result = matchDto.result
        match.date = DateUtils.parseDateTime(matchDto.date)
        match.team1 = teamRepository.findById(matchDto.team1!!.toLong()).orElse(null)
        match.team2 = teamRepository.findById(matchDto.team2!!.toLong()).orElse(null)

        matchRepository.save(match)
        return "redirect:/matches"
    }
}
