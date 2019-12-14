package de.melon.tridomcounter.activities.menu

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.Session

class SessionCardAdapter(var sessions: MutableList<Session>) : RecyclerView.Adapter<SessionCardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sessionName = view.findViewById<TextView>(R.id.sessionNameTextView)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_session, parent, false) as View

        return ViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.sessionName.text = "Session ${position+1}"

    }

    override fun getItemCount() = sessions.size

}