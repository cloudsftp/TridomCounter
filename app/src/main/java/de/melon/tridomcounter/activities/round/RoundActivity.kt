package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        title = "$title ${current.roundId+1}"

        buildActivity()

        val infoBack = Toast.makeText(this, R.string.back_message_round, Toast.LENGTH_LONG)
        infoBack.show()

    }

    fun buildActivity() {
        session = GameData.sessions[current.sessionId]
        round = session.rounds[current.roundId]

        customActionRecyclerView.adapter = ActionCardAdapter(round, this)
        customActionRecyclerView.layoutManager = LinearLayoutManager(this)

        activePlayerNameTextView.text = session.players[round.currentPlayerId]

    }

    private fun backToSession() {
        val intent = Intent(this, SessionActivity::class.java)
        startActivity(intent)

    }

    override fun onBackPressed() = backToSession()

}
