package de.melon.tridomcounter.activities.session

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.round.Round

class RoundCardAdapter(val rounds: MutableList<Round>)
    : RecyclerView.Adapter<RoundCardAdapter.RoundCardViewHolder>() {

    class RoundCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.roundNameTextView)
        val playerRecyclerView = view.findViewById<RecyclerView>(R.id.nestedPlayerRecyclerView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : RoundCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_round, parent, false) as View

        return RoundCardViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: RoundCardViewHolder, position: Int) {
        viewHolder.nameTextView.text = String.format("%s %d", viewHolder.itemView.context.getString(R.string.round), position + 1)

        val session = GameData.sessions[current.sessionId]
        viewHolder.playerRecyclerView.adapter = PlayerCardAdapter(session.players, session.rounds[position], true)
        viewHolder.playerRecyclerView.layoutManager = LinearLayoutManager(viewHolder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun getItemCount() = rounds.size

}
