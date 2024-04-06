package de.melon.tridomcounter.activities.session

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.ActivityWithMenu
import de.melon.tridomcounter.activities.OnItemClickListener
import de.melon.tridomcounter.activities.addOnItemClickListener
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.round.RoundActivity
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.databinding.ActivitySessionBinding
import de.melon.tridomcounter.logic.round.Round
import de.melon.tridomcounter.logic.Session

class SessionActivity : ActivityWithMenu() {
    var sessionId = -1
    lateinit var session: Session
    lateinit var rounds: MutableList<Round>

    lateinit var roundCardAdapter: RoundCardAdapter

    private lateinit var binding: ActivitySessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        sessionId = current.sessionId

        title = "$title ${sessionId+1}"

        session = GameData.sessions[sessionId]
        val players = session.players

        binding.playerRecyclerView.adapter = PlayerCardAdapter(players, session)
        binding.playerRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rounds = session.rounds

        roundCardAdapter = RoundCardAdapter(rounds)
        binding.roundRecyclerView.adapter = roundCardAdapter
        binding.roundRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.roundRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val intent = Intent(view.context, RoundActivity::class.java)
                current.roundId = position
                startActivity(intent)
            }
        })

        binding.fab.setOnClickListener {
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateRoundRecyclerView() = roundCardAdapter.notifyDataSetChanged()
}
