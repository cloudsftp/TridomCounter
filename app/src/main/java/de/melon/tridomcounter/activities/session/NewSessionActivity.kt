package de.melon.tridomcounter.activities.session

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.activities.ActivityWithMenu
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.databinding.ActivityNewSessionBinding

class NewSessionActivity : ActivityWithMenu() {
    lateinit var editPlayerCardAdapter: EditPlayerCardAdapter

    private lateinit var binding: ActivityNewSessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setUpPlayerControls()

        binding.fab.setOnClickListener {
            val intent = Intent(this, SessionActivity::class.java)

            editPlayerCardAdapter.savePlayerNames()
            val players = editPlayerCardAdapter.getNeededPlayerNames()
            current.sessionId = GameData.newSession(players)

            startActivity(intent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setUpPlayerControls() {
        binding.numberOfPlayersMinus.setOnClickListener { changeNumberOfPlayers(-1) }
        binding.numberOfPlayersPlus.setOnClickListener { changeNumberOfPlayers(1) }

        editPlayerCardAdapter = EditPlayerCardAdapter()
        binding.editPlayerRecyclerView.adapter = editPlayerCardAdapter
        binding.editPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

        renderCards()
    }

    fun changeNumberOfPlayers(delta: Int) {
        val newValue = editPlayerCardAdapter.changeNumberOfPlayers(delta)

        binding.numberOfPlayersTextView.text = "$newValue"
        renderCards()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun renderCards() = editPlayerCardAdapter.notifyDataSetChanged()
}
