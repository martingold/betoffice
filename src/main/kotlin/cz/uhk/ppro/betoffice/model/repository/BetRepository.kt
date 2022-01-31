package cz.uhk.ppro.betoffice.model.repository

import cz.uhk.ppro.betoffice.model.entity.Bet
import cz.uhk.ppro.betoffice.model.entity.User
import org.springframework.data.repository.CrudRepository

interface BetRepository : CrudRepository<Bet, Long> {
	fun findAllByUser(user: User): Iterable<Bet>
}
