package de.melon.tridomcounter.activities.round

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R

class ActionCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val actionNameTextView = view.findViewById<TextView>(R.id.actionNameTextView)

}

fun inflateActionCard(parent: ViewGroup) : ActionCardViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.card_action, parent, false) as View

    return ActionCardViewHolder(view)

}
