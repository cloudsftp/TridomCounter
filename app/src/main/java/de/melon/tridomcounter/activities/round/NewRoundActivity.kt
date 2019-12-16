package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.OnItemClickListener
import de.melon.tridomcounter.activities.addOnItemClickListener
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import kotlinx.android.synthetic.main.activity_new_round.*
import kotlinx.android.synthetic.main.content_new_round.*

class NewRoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_round)
        setSupportActionBar(toolbar as Toolbar?)

        val session = GameData.sessions[current.sessionId]
        val players = session.players

        choosePlayerRecyclerView.adapter = ChoosePlayerCardAdapter(players)
        choosePlayerRecyclerView.layoutManager = LinearLayoutManager(this)

        choosePlayerRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val intent = Intent(view.context, RoundActivity::class.java)

                val roundId = session.newRound(position)
                current.roundId = roundId

                startActivity(intent)
            }
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
