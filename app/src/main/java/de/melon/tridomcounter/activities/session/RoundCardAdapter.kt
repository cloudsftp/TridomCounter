package de.melon.tridomcounter.activities.session

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.Round

class RoundCardAdapter(val rounds: MutableList<Round>)
    : RecyclerView.Adapter<RoundCardAdapter.RoundCardViewHolder>() {

    class RoundCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.roundNameTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RoundCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.round_card, parent, false) as View

        return RoundCardViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: RoundCardViewHolder, position: Int) {
        val originalText = viewHolder.nameTextView.text
        viewHolder.nameTextView.text = String.format("$originalText %2d", position)

    }

    override fun getItemCount() = rounds.size

}
