package cz.uhk.ppro.betoffice.dto

import cz.uhk.ppro.betoffice.model.entity.Bet
import cz.uhk.ppro.betoffice.model.entity.Match
import cz.uhk.ppro.betoffice.model.entity.User
import java.util.*

data class BetDto(
    var match: MutableList<Match>? = null,
    var user: User? = null,
    var startDate: Date? = null,
    var endDate: Date? = null,
    var description: String? = null
    )
