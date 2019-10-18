package de.melon.tridomcounter.activities.session

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.PointInterface

class PlayerCardAdapter(val players: Array<String>, val pointSource: PointInterface)
    : RecyclerView.Adapter<PlayerCardAdapter.PlayerCardViewHolder>() {

    class PlayerCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.playerNameTextView)!!
        val pointsTextView = view.findViewById<TextView>(R.id.playerPointsTextView)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PlayerCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_player, parent, false) as View

        return PlayerCardViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: PlayerCardViewHolder, position: Int) {
        viewHolder.nameTextView.text = players[position]
        viewHolder.pointsTextView.text = "${pointSource.getPoints(position)}"

    }

    override fun getItemCount() = players.size

}