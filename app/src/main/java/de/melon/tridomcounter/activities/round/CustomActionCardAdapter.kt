package de.melon.tridomcounter.activities.round

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.Round

class CustomActionCardAdapter(val round: Round, val activity: RoundActivity)
    : RecyclerView.Adapter<CustomActionCardAdapter.CommonActionCardViewHolder>() {

    class CommonActionCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actionNameTextView = view.findViewById<TextView>(R.id.actionNameTextView)
        val inputEditText = view.findViewById<EditText>(R.id.actionInputEditText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : CommonActionCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_custom_action, parent, false) as View

        return CommonActionCardViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: CommonActionCardViewHolder, position: Int) {
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
            activity.displayPlacementDialog()
        }

    }

    override fun getItemCount() = 1

}
