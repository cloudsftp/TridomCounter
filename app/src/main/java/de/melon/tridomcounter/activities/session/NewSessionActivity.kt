package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import kotlinx.android.synthetic.main.activity_new_session.*
import kotlinx.android.synthetic.main.content_new_session.*

class NewSessionActivity : AppCompatActivity() {

    lateinit var editPlayerCardAdapter: EditPlayerCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_session)
        setSupportActionBar(toolbar as Toolbar?)

        setUpPlayerControls()

        fab.setOnClickListener {
            val intent = Intent(this, SessionActivity::class.java)

            editPlayerCardAdapter.savePlayerNames()
            val players = editPlayerCardAdapter.getNeededPlayerNames()
            current.sessionId = GameData.newSession(players)

            startActivity(intent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun setUpPlayerControls() {
        numberOfPlayersMinus.setOnClickListener { changeNumberOfPlayers(-1) }
        numberOfPlayersPlus.setOnClickListener { changeNumberOfPlayers(1) }

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

    fun renderCards() = editPlayerCardAdapter.notifyDataSetChanged()

}
