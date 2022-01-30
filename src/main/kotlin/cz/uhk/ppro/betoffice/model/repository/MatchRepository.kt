package cz.uhk.ppro.betoffice.model.repository

import cz.uhk.ppro.betoffice.model.entity.Match
import cz.uhk.ppro.betoffice.model.entity.User
import org.springframework.data.repository.CrudRepository

interface MatchRepository : CrudRepository<Match, Long>
