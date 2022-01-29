package cz.uhk.ppro.betoffice.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MatchController {

    @GetMapping("/user/matches")
    fun matchesList(model: Model): String {
        return "matchesList"
    }
}