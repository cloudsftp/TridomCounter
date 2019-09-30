package de.melon.tridomcounter.activities.session

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.NumberPicker
import de.melon.tridomcounter.R

import kotlinx.android.synthetic.main.activity_new_session.*

class NewSessionActivity : AppCompatActivity() {

    lateinit var newPlayerRecyclerView: RecyclerView
    lateinit var numberPicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_session)
        setSupportActionBar(toolbar)

        setNumberPicker()

        fab.setOnClickListener { view ->
            {}
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setNumberPicker() {
        numberPicker = findViewById(R.id.numOfPlayersPicker)

        numberPicker.minValue = 2
        numberPicker.maxValue = 6
        numberPicker.setOnValueChangedListener {
            _, _, num ->
            displayPlayerInputCards(num)
        }

        newPlayerRecyclerView = findViewById(R.id.newPlayerNamesRecyclerView)

        newPlayerRecyclerView.adapter = NewPlayerCardAdapter(numberPicker.value)
        newPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun displayPlayerInputCards(num: Int) {
        (newPlayerRecyclerView.adapter as NewPlayerCardAdapter).playerCount = num
        newPlayerRecyclerView.layoutManager = LinearLayoutManager(this)

    }

}
