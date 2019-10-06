package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.OnItemClickListener
import de.melon.tridomcounter.activities.addOnItemClickListener
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import kotlinx.android.synthetic.main.activity_new_round.*

class NewRoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_round)
        setSupportActionBar(toolbar)

        val session = GameData.sessions[current.sessionId]
        val players = session.players

        val choosePlayerRecyclerView = findViewById<RecyclerView>(R.id.choosePlayerRecyclerView)

        choosePlayerRecyclerView.adapter = ChoosePlayerCardAdapter(players)
        choosePlayerRecyclerView.layoutManager = LinearLayoutManager(this)

        choosePlayerRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val intent = Intent(view.context, RoundActivity::class.java)

                val roundId = session.newRound()
                current.roundId = roundId

                startActivity(intent)
            }
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
