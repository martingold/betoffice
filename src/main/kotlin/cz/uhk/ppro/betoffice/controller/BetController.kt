package cz.uhk.ppro.betoffice.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BetController {

    @GetMapping("/user/bet")
    fun bet(model: Model): String {
        return "bet"
    }
}