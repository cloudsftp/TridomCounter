package de.melon.tridomcounter.activities.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.OnItemClickListener
import de.melon.tridomcounter.activities.addOnItemClickListener
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.session.NewSessionActivity
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.data.GameData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sessionCardAdapter : SessionCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val sessions = GameData.sessions
        sessionCardAdapter = SessionCardAdapter(sessions)
        sessionRecyclerView.adapter = sessionCardAdapter
        sessionRecyclerView.layoutManager = LinearLayoutManager(this)

        sessionRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val intent = Intent(view.context, SessionActivity::class.java)
                current.sessionId = position
                startActivity(intent)
            }
        })

        fab.setOnClickListener {
            val intent = Intent(this, NewSessionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        updateSessionRecyclerView()

        super.onResume()
    }

    fun updateSessionRecyclerView() = sessionCardAdapter.notifyDataSetChanged()

}
