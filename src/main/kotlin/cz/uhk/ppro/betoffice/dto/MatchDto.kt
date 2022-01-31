package cz.uhk.ppro.betoffice.dto

data class MatchDto(
	var id: Long? = null,
	var team1: Int? = null,
	var team2: Int? = null,
	var date: String? = null,
	var description: String? = null,
	var result: String? = null,
)
