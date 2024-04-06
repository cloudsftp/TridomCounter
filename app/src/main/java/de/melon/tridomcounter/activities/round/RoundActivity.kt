package de.melon.tridomcounter.activities.round

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.ActivityWithMenu
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.databinding.ActivityRoundBinding
import de.melon.tridomcounter.logic.Session
import de.melon.tridomcounter.logic.round.Round

class RoundActivity : ActivityWithMenu() {

    lateinit var session: Session
    lateinit var round: Round

    private lateinit var binding: ActivityRoundBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        session = GameData.sessions[current.sessionId]
        round = session.rounds[current.roundId]

        binding.actionRecyclerView.layoutManager = LinearLayoutManager(this)

        round.context = this
        buildActivity()

        val infoBack = Toast.makeText(this, R.string.back_message_round, Toast.LENGTH_LONG)
        infoBack.show()

    }

    fun buildActivity() {
        title = round.title()

        binding.actionRecyclerView.adapter = ActionCardAdapter(round, this)

    }

    private fun backToSession() {
        val intent = Intent(this, SessionActivity::class.java)
        startActivity(intent)

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        backToSession()
    }

}
