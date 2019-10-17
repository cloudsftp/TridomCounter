package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.logic.Round

class ActionCardAdapter(val round: Round)
    : RecyclerView.Adapter<ActionCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = inflateActionCard(parent)

    override fun onBindViewHolder(viewHolder: ActionCardViewHolder, position: Int) {
        val context = viewHolder.itemView.context
        val actionNameTextView = viewHolder.actionNameTextView

        if (position == 0) {
            actionNameTextView.gravity = Gravity.CENTER
            actionNameTextView.text = context.getString(R.string.pause_round)

            viewHolder.itemView.setOnClickListener {
                context.startActivity(Intent(context, SessionActivity::class.java))
            }

        }

    }

    override fun getItemCount() = 1

}