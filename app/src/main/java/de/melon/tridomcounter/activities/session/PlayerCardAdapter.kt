package de.melon.tridomcounter.activities.session

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R

class PlayerCardAdapter(val players: Array<String>)
    : RecyclerView.Adapter<PlayerCardAdapter.PlayerCardViewHolder>() {

    class PlayerCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.playerNameTextView)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PlayerCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_card, parent, false) as View

        return PlayerCardViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: PlayerCardViewHolder, position: Int) {
        viewHolder.nameTextView.text = Editable.Factory.getInstance().newEditable(players[position])

    }

    override fun getItemCount() = players.size

}