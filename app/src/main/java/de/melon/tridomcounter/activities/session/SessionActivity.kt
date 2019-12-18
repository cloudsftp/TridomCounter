package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.OnItemClickListener
import de.melon.tridomcounter.activities.addOnItemClickListener
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.menu.MainActivity
import de.melon.tridomcounter.activities.round.RoundActivity
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.round.Round
import de.melon.tridomcounter.logic.Session
import kotlinx.android.synthetic.main.activity_session.*
import kotlinx.android.synthetic.main.content_session.*

class SessionActivity : AppCompatActivity() {

    var sessionId = -1
    lateinit var session: Session
    lateinit var rounds: MutableList<Round>

    lateinit var roundCardAdapter: RoundCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)

        sessionId = current.sessionId

        title = "$title ${sessionId+1}"

        session = GameData.sessions[sessionId]
        val players = session.players

        playerRecyclerView.adapter = PlayerCardAdapter(players, session)
        playerRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rounds = session.rounds

        roundCardAdapter = RoundCardAdapter(rounds)
        roundRecyclerView.adapter = roundCardAdapter
        roundRecyclerView.layoutManager = LinearLayoutManager(this)

        roundRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val intent = Intent(view.context, RoundActivity::class.java)
                current.roundId = position
                startActivity(intent)
            }
        })

        fab.setOnClickListener {
            current.roundId = session.newRound()
            val intent = Intent(this, RoundActivity::class.java)
            startActivity(intent)

        }

        val infoBack = Toast.makeText(this, R.string.back_message_session, Toast.LENGTH_LONG)
        infoBack.show()
        
    }

    override fun onResume() {
        updateRoundRecyclerView()

        super.onResume()
    }

    fun updateRoundRecyclerView() = roundCardAdapter.notifyDataSetChanged()

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

}
