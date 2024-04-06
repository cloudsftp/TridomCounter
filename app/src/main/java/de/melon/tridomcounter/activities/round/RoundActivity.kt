package de.melon.tridomcounter.activities.round

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.ActivityWithMenu
import de.melon.tridomcounter.activities.current
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_round, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_help        -> displayHint()
            R.id.action_privacy     -> openPrivacyDeclaration()
            else                    -> super.onContextItemSelected(item)
        }

        return true
    }

    private fun displayHint() {
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(getString(R.string.help))
        alertDialog.setMessage(round.help())
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") {
                dialog, _ -> dialog.dismiss()
        }

        alertDialog.show()
    }
}
