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
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.Session
import de.melon.tridomcounter.logic.round.Round
import kotlinx.android.synthetic.main.activity_round.*
import kotlinx.android.synthetic.main.content_round.*


class RoundActivity : AppCompatActivity() {

    lateinit var session: Session
    lateinit var round: Round

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round)
        setSupportActionBar(toolbar)

        session = GameData.sessions[current.sessionId]
        round = session.rounds[current.roundId]

        actionRecyclerView.layoutManager = LinearLayoutManager(this)

        round.context = this
        buildActivity()

        val infoBack = Toast.makeText(this, R.string.back_message_round, Toast.LENGTH_LONG)
        infoBack.show()

    }

    fun buildActivity() {
        title = round.title()

        actionRecyclerView.adapter = ActionCardAdapter(round, this)

    }

    private fun backToSession() {
        val intent = Intent(this, SessionActivity::class.java)
        startActivity(intent)

    }

    override fun onBackPressed() = backToSession()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_round, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(getString(R.string.help))
        alertDialog.setMessage(round.help())
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") {
                dialog, _ -> dialog.dismiss()
        }

        alertDialog.show()

        return true

    }

}
