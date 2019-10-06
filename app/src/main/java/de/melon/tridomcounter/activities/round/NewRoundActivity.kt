package de.melon.tridomcounter.activities.round

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import kotlinx.android.synthetic.main.activity_new_round.*

class NewRoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_round)
        setSupportActionBar(toolbar)

        val players = GameData.sessions[current.sessionId].players

        val choosePlayerRecyclerView = findViewById<RecyclerView>(R.id.choosePlayerRecyclerView)
        choosePlayerRecyclerView.adapter = ChoosePlayerCardAdapter(players)
        choosePlayerRecyclerView.layoutManager = LinearLayoutManager(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
