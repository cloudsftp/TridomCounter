package de.melon.tridomcounter.activities.session

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.melon.tridomcounter.R
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : AppCompatActivity() {

    var sessionId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)

        sessionId = intent?.extras?.get("SessionId") as Int
        title = "$title $sessionId"

        fab.setOnClickListener { view ->

        }
        
    }

}
