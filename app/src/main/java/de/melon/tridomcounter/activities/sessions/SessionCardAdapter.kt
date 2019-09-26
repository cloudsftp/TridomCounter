package de.melon.tridomcounter.activities.sessions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.Session

class SessionCardAdapter(private var sessions: ArrayList<Session>) : RecyclerView.Adapter<SessionCardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val rootLayout: LinearLayout
        val dataLayout: LinearLayout
        val textHeading: TextView

        init {
            rootLayout = view.findViewById(R.id.rootLayout)
            dataLayout = view.findViewById(R.id.dataLayout)
            textHeading = view.findViewById(R.id.textHeading)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.session_card, parent, false) as View

        return ViewHolder(view)

    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textHeading.text = position.toString()

    }

    override fun getItemCount() = sessions.size

}