package de.melon.tridomcounter.activities.round

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.EditText
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.BaseMove
import de.melon.tridomcounter.logic.PlaceMove
import de.melon.tridomcounter.logic.Round
import de.melon.tridomcounter.logic.Session
import kotlinx.android.synthetic.main.activity_round.*
import kotlinx.android.synthetic.main.content_round.*

class RoundActivity : AppCompatActivity() {

    lateinit var session: Session
    lateinit var round: Round

    var currentMove = BaseMove

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_round)
        setSupportActionBar(toolbar)

        title = "$title ${current.roundId}"

        session = GameData.sessions[current.sessionId]
        round = session.rounds[current.roundId]

        playerActionRecyclerView.adapter = PlayerActionCardAdapter(round, this)
        playerActionRecyclerView.layoutManager = LinearLayoutManager(this)

        actionRecyclerView.adapter = ActionCardAdapter(round)
        actionRecyclerView.layoutManager = LinearLayoutManager(this)

        activePlayerNameTextView.text = session.players[round.currentPlayerId]
        pointsTextView.text = round.getPoints(round.currentPlayerId).toString()

    }

    lateinit var numberOfPointsEditText: EditText

    fun displayPlacementDialog() {
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_place)

        numberOfPointsEditText = dialog.findViewById(R.id.numberOfPointsEditText)
        val acceptButton = dialog.findViewById<Button>(R.id.acceptButton)
        acceptButton.setOnClickListener {
            val numberOfPointsPlaced = numberOfPointsEditText.text.toString().toInt()
            dialog.dismiss()
            completeMove(numberOfPointsPlaced)

        }

        dialog.show()

    }

    fun completeMove(numberOfPointsPlaced: Int) {
        val move = PlaceMove(currentMove, numberOfPointsPlaced)
        round.makeMove(move)

        val intent = Intent(this, RoundActivity::class.java)
        startActivity(intent)

    }

    override fun onBackPressed() {}

}
