package de.melon.tridomcounter.activities.session

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import de.melon.tridomcounter.R

import kotlinx.android.synthetic.main.activity_new_session.*

class NewSessionActivity : AppCompatActivity() {

    lateinit var newPlayerRecyclerView: RecyclerView
    lateinit var newPlayerCardAdapter: NewPlayerCardAdapter

    lateinit var numberOfPlayersTextView: TextView
    lateinit var numberOfPlayersPlus: Button
    lateinit var numberOfPlayersMinus: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_session)
        setSupportActionBar(toolbar)

        setUp()

        fab.setOnClickListener { view ->
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setUp() {
        numberOfPlayersTextView = findViewById(R.id.numberOfPlayersText)

        numberOfPlayersMinus = findViewById(R.id.numberOfPlayersMinus)
        numberOfPlayersMinus.setOnClickListener { changeNumberOfPlayers(-1) }

        numberOfPlayersPlus = findViewById(R.id.numberOfPlayersPlus)
        numberOfPlayersPlus.setOnClickListener { changeNumberOfPlayers(1) }

        newPlayerRecyclerView = findViewById(R.id.playersRecyclerView)

        newPlayerCardAdapter = NewPlayerCardAdapter()
        newPlayerRecyclerView.adapter = newPlayerCardAdapter
        newPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

        renderCards()

    }

    fun changeNumberOfPlayers(delta: Int) {
        val newValue = newPlayerCardAdapter.changeNumberOfPlayers(delta)

        numberOfPlayersTextView.text = "$newValue"
        renderCards()

    }

    fun renderCards() {
        newPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

    }

}
