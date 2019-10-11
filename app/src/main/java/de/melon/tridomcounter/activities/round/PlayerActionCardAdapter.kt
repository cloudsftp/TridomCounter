package de.melon.tridomcounter.activities.round

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.Round

class PlayerActionCardAdapter(val round: Round, val activity: RoundActivity)
    : RecyclerView.Adapter<ActionCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = inflateActionCard(parent)

    override fun onBindViewHolder(viewHolder: ActionCardViewHolder, position: Int) {
        val cardView = viewHolder.itemView
        val context = cardView.context
        val actionNameTextView = viewHolder.actionNameTextView

        when (position) {
            0 -> simpleMoveCard(context, cardView, actionNameTextView)

        }

    }

    fun simpleMoveCard(context: Context, cardView: View, actionNameTextView: TextView) {
        actionNameTextView.text = context.getString(R.string.make_move)

        cardView.setOnClickListener {
            activity.place()
        }

    }

    override fun getItemCount() = 1

}
