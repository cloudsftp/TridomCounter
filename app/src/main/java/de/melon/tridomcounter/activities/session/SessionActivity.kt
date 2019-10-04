package de.melon.tridomcounter.activities.session

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.melon.tridomcounter.R
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->

        }
    }

}
