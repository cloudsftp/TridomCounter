package de.melon.tridomcounter.activities.round

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import de.melon.tridomcounter.logic.Round

class PlayerActionCardAdapter(val round: Round)
    : RecyclerView.Adapter<ActionCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = inflateActionCard(parent)

    override fun onBindViewHolder(viewHolder: ActionCardViewHolder, position: Int) {

    }

    override fun getItemCount() = 0

}
