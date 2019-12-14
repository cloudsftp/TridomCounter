package de.melon.tridomcounter.activities.round

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R

class ChoosePlayerCardAdapter(val players: Array<String>)
    : RecyclerView.Adapter<ChoosePlayerCardAdapter.ChoosePlayerCardViewHolder>() {

    class ChoosePlayerCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.playerNameTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : ChoosePlayerCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_player_choose, parent, false) as View

        return ChoosePlayerCardViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: ChoosePlayerCardViewHolder, position: Int) {
        viewHolder.nameTextView.text = players[position]

    }

    override fun getItemCount() = players.size

}