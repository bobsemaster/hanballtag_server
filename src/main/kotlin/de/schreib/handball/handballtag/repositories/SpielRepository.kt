package de.schreib.handball.handballtag.repositories

import de.schreib.handball.handballtag.entities.Jugend
import de.schreib.handball.handballtag.entities.Mannschaft
import de.schreib.handball.handballtag.entities.Spiel
import de.schreib.handball.handballtag.entities.SpielTyp
import org.intellij.lang.annotations.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SpielRepository : JpaRepository<Spiel, Long> {

    @Language("SpringDataQL")
    @Query(value = "SELECT s FROM Spiel s WHERE s.heimMannschaft= ?1 OR s.gastMannschaft = ?1")
    fun findAllByMannschaft(mannschaft: Mannschaft): List<Spiel>

    @Query(value = "SELECT s FROM Spiel s WHERE s.spielTyp= ?1 AND s.gastMannschaft.jugend = ?2")
    fun findAllBySpielTypAndJugend(spielTyp: SpielTyp, jugend: Jugend): List<Spiel>

    fun deleteAllByHeimMannschaftInOrGastMannschaftIn(
        mannschaftHeim: Collection<Mannschaft>,
        mannschaftGast: Collection<Mannschaft>
    )

    fun findAllBySpielPlatz(platz: Int): List<Spiel>

    @Query(value = "SELECT s FROM Spiel s WHERE s.gastMannschaft.jugend = ?1")
    fun findAllByJugend(jugend: Jugend): List<Spiel>

    @Query(value = "SELECT s FROM Spiel s WHERE s.gastMannschaft.jugend IN ?1")
    fun findAllByAllJugend(jugend: List<Jugend>): List<Spiel>
}
