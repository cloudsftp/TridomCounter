package de.melon.tridomcounter.activities.round

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.Round
import de.melon.tridomcounter.logic.Session
import kotlinx.android.synthetic.main.activity_round.*

class RoundActivity : AppCompatActivity() {

    lateinit var session: Session
    lateinit var round: Round

    lateinit var activePlayerNameTextView: TextView
    lateinit var playerActionsRecyclerView: RecyclerView
    lateinit var actionsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round)
        setSupportActionBar(toolbar)

        title = "$title ${current.roundId}"

        session = GameData.sessions[current.sessionId]
        round = session.rounds[current.roundId]

        activePlayerNameTextView = findViewById(R.id.activePlayerNameTextView)

        playerActionsRecyclerView = findViewById(R.id.playerActionRecyclerView)
        playerActionsRecyclerView.layoutManager = LinearLayoutManager(this)

        actionsRecyclerView = findViewById(R.id.actionRecyclerView)
        actionsRecyclerView.layoutManager = LinearLayoutManager(this)

        updateActivePlayer()

        updatePlayerActions()
        updateActions()

    }

    fun updatePlayerActions() {


    }

    fun updateActions() {
        val pauseCard = LayoutInflater.from(this).inflate(R.layout.pause_card, null)

        actionsRecyclerView.layoutManager = LinearLayoutManager(this)

    }


    fun updateActivePlayer() {
        activePlayerNameTextView.text = session.players[round.playerId]

    }

    override fun onBackPressed() {}

}
