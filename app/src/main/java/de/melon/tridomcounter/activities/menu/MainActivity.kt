package de.melon.tridomcounter.activities.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import de.melon.tridomcounter.activities.ActivityWithMenu
import de.melon.tridomcounter.activities.OnItemClickListener
import de.melon.tridomcounter.activities.addOnItemClickListener
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.session.NewSessionActivity
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.databinding.ActivityMainBinding

class MainActivity : ActivityWithMenu() {

    lateinit var sessionCardAdapter : SessionCardAdapter

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val sessions = GameData.sessions
        sessionCardAdapter = SessionCardAdapter(sessions)
        binding.sessionRecyclerView.adapter = sessionCardAdapter
        binding.sessionRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.sessionRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val intent = Intent(view.context, SessionActivity::class.java)
                current.sessionId = position
                startActivity(intent)
            }
        })

        binding.fab.setOnClickListener {
            val intent = Intent(this, NewSessionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        updateSessionRecyclerView()

        super.onResume()
    }

    private fun updateSessionRecyclerView() = sessionCardAdapter.notifyDataSetChanged()

}
