package cz.uhk.ppro.betoffice.dto

import cz.uhk.ppro.betoffice.model.entity.Stream
import cz.uhk.ppro.betoffice.model.entity.Team
import java.time.LocalDateTime
import java.util.*
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

data class MatchDto(
	var id: Long? = null,
	var team1: Int? = null,
	var team2: Int? = null,
	var date: String? = null,
	var description: String? = null,
	var result: String? = null,
)
