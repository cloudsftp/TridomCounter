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
    lateinit var numberOfPlayersTextView: TextView
    lateinit var numberOfPlayersPlus: Button
    lateinit var numberOfPlayersMinus: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_session)
        setSupportActionBar(toolbar)

        setNumberPicker()

        fab.setOnClickListener { view ->
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setNumberPicker() {
        numberOfPlayersTextView = findViewById(R.id.numberOfPlayersText)

        numberOfPlayersMinus = findViewById(R.id.numberOfPlayersMinus)
        numberOfPlayersMinus.setOnClickListener { changeNumberOfPlayers(-1) }

        numberOfPlayersPlus = findViewById(R.id.numberOfPlayersPlus)
        numberOfPlayersPlus.setOnClickListener { changeNumberOfPlayers(1) }

        newPlayerRecyclerView = findViewById(R.id.newPlayerNamesRecyclerView)

        newPlayerRecyclerView.adapter = NewPlayerCardAdapter(numberOfPlayersTextView.text.toString().toInt())
        newPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    val minValue = 2
    val maxValue = 6
    fun changeNumberOfPlayers(delta: Int) {
        val currentValue = numberOfPlayersTextView.text.toString().toInt()
        var newValue = currentValue + delta

        if (newValue > maxValue) newValue = maxValue
        if (newValue < minValue) newValue = minValue

        numberOfPlayersTextView.text = "$newValue"
        displayPlayerInputCards(newValue)

    }

    fun displayPlayerInputCards(num: Int) {
        (newPlayerRecyclerView.adapter as NewPlayerCardAdapter).playerCount = num
        newPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

    }

}
