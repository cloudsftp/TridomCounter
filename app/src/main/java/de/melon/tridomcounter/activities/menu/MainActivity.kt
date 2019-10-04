package de.melon.tridomcounter.activities.menu

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.session.NewSessionActivity
import de.melon.tridomcounter.data.GameData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sessionsRecyclerView: RecyclerView
    lateinit var sessionsAdapter: SessionCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        sessionsRecyclerView = findViewById(R.id.sessionsRecyclerView)

        setSessionsList()

        fab.setOnClickListener {
            val intent = Intent(this, NewSessionActivity::class.java)
            startActivity(intent)
        }
    }

    fun setSessionsList() {
        val sessions = GameData.sessions
        sessionsAdapter = SessionCardAdapter(sessions.toTypedArray())
        sessionsRecyclerView.adapter = sessionsAdapter

    }

    override fun onResume() {
        updateSessionsList()

        super.onResume()
    }

    fun updateSessionsList() {
        sessionsAdapter.sessions = GameData.sessions.toTypedArray()
        sessionsRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
