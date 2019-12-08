package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.Round
import de.melon.tridomcounter.logic.Session
import kotlinx.android.synthetic.main.activity_round.*
import kotlinx.android.synthetic.main.content_round.*

class RoundActivity : AppCompatActivity() {

    lateinit var session: Session
    lateinit var round: Round

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round)
        setSupportActionBar(toolbar)

        title = "$title ${current.roundId}"

        buildActivity()

    }

    fun buildActivity() {
        session = GameData.sessions[current.sessionId]
        round = session.rounds[current.roundId]

        customActionRecyclerView.adapter = ActionCardAdapter(round, this)
        customActionRecyclerView.layoutManager = LinearLayoutManager(this)

        activePlayerNameTextView.text = session.players[round.currentPlayerId]
        pointsTextView.text = round.getPoints(round.currentPlayerId).toString()

    }

    private fun backToSession() {
        val intent = Intent(this, SessionActivity::class.java)
        startActivity(intent)

    }

    override fun onBackPressed() = backToSession()

}
