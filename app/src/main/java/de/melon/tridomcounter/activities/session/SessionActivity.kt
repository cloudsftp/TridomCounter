package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.menu.MainActivity
import de.melon.tridomcounter.activities.round.NewRoundActivity
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.Round
import de.melon.tridomcounter.logic.Session
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : AppCompatActivity() {

    var sessionId = -1
    lateinit var session: Session
    lateinit var rounds: MutableList<Round>

    lateinit var roundRecyclerView: RecyclerView
    lateinit var allSessionsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)

        sessionId = current.sessionId

        title = "$title $sessionId"

        session = GameData.sessions[sessionId]
        val players = session.players

        val playerRecyclerView = findViewById<RecyclerView>(R.id.playerRecyclerView)
        playerRecyclerView.adapter = PlayerCardAdapter(players)
        playerRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rounds = session.rounds

        roundRecyclerView = findViewById(R.id.roundRecyclerView)

        updateRoundRecyclerView()

        allSessionsButton = findViewById(R.id.allSessionsButton)
        allSessionsButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        fab.setOnClickListener {
            val intent = Intent(this, NewRoundActivity::class.java)
            startActivity(intent)

        }
        
    }

    override fun onResume() {
        updateRoundRecyclerView()

        super.onResume()
    }

    fun updateRoundRecyclerView() {
        roundRecyclerView.adapter = RoundCardAdapter(rounds)
        roundRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onBackPressed() {}

}
