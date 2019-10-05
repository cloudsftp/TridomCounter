package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.menu.MainActivity
import kotlinx.android.synthetic.main.activity_session.*

class SessionActivity : AppCompatActivity() {

    var sessionId = -1

    lateinit var allSessionsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        setSupportActionBar(toolbar)

        allSessionsButton = findViewById(R.id.allSessionsButton)
        allSessionsButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sessionId = intent?.extras?.get("SessionId") as Int
        title = "$title $sessionId"

        fab.setOnClickListener {

        }
        
    }

}
