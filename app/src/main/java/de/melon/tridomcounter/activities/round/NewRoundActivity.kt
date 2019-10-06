package de.melon.tridomcounter.activities.round

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.melon.tridomcounter.R
import kotlinx.android.synthetic.main.activity_new_round.*

class NewRoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_round)
        setSupportActionBar(toolbar)



        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
