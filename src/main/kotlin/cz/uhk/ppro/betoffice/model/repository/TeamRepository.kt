package cz.uhk.ppro.betoffice.model.repository

import cz.uhk.ppro.betoffice.model.entity.Team
import org.springframework.data.repository.CrudRepository

interface TeamRepository: CrudRepository<Team, Long>