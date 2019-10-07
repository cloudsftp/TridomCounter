package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import kotlinx.android.synthetic.main.activity_new_session.*

class NewSessionActivity : AppCompatActivity() {

    lateinit var editPlayerRecyclerView: RecyclerView
    lateinit var editPlayerCardAdapter: EditPlayerCardAdapter

    lateinit var numberOfPlayersTextView: TextView
    lateinit var numberOfPlayersPlus: Button
    lateinit var numberOfPlayersMinus: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_session)
        setSupportActionBar(toolbar)

        setUpPlayerControls()

        fab.setOnClickListener {
            val intent = Intent(this, SessionActivity::class.java)

            val players = editPlayerCardAdapter.getPlayers().map { e -> e.toString() } .toTypedArray()
            current.sessionId = GameData.newSession(players)

            startActivity(intent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun setUpPlayerControls() {
        numberOfPlayersTextView = findViewById(R.id.numberOfPlayersText)

        numberOfPlayersMinus = findViewById(R.id.numberOfPlayersMinus)
        numberOfPlayersMinus.setOnClickListener { changeNumberOfPlayers(-1) }

        numberOfPlayersPlus = findViewById(R.id.numberOfPlayersPlus)
        numberOfPlayersPlus.setOnClickListener { changeNumberOfPlayers(1) }

        editPlayerRecyclerView = findViewById(R.id.editPlayerRecyclerView)

        editPlayerCardAdapter = EditPlayerCardAdapter()
        editPlayerRecyclerView.adapter = editPlayerCardAdapter
        editPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

        renderCards()

    }

    fun changeNumberOfPlayers(delta: Int) {
        val newValue = editPlayerCardAdapter.changeNumberOfPlayers(delta)

        numberOfPlayersTextView.text = "$newValue"
        renderCards()

    }

    fun renderCards() {
        editPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

    }

}
