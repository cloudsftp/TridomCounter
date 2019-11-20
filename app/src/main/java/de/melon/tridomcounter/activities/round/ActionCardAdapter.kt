package de.melon.tridomcounter.activities.round

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.Round

class ActionCardAdapter(val round: Round, val activity: RoundActivity)
    : RecyclerView.Adapter<ActionCardAdapter.AbstractActionCardViewHolder>() {

    abstract class AbstractActionCardViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ComplexActionCardViewHolder(view: View) : AbstractActionCardViewHolder(view) {
        val actionNameTextView = view.findViewById<TextView>(R.id.actionNameTextView)
        val inputEditText = view.findViewById<EditText>(R.id.actionInputEditText)

    }

    class SimpleActionCardViewHolder(view: View) : AbstractActionCardViewHolder(view) {
        val actionNameTextView = view.findViewById<TextView>(R.id.actionNameTextView)

    }

    override fun getItemViewType(position: Int) = if (position < round.roundActions.sizeOfComplexActions()) 0
        else 1

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) : AbstractActionCardViewHolder {
        if (type == 0) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_complex_action, parent, false) as View

            return ComplexActionCardViewHolder(view)

        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_action, parent, false) as View

            return SimpleActionCardViewHolder(view)

        }

    }

    override fun onBindViewHolder(viewHolder: AbstractActionCardViewHolder, position: Int) {
        if (position < round.roundActions.sizeOfComplexActions()) {
            viewHolder as ComplexActionCardViewHolder

            val move = round.roundActions.complexActions[position]
            val function = move.first
            val moveName = move.second

            viewHolder.actionNameTextView.text = moveName

            viewHolder.itemView.setOnClickListener {
                val pointsString = viewHolder.inputEditText.text.toString()
                if (pointsString.isNotEmpty()) {
                    val points = pointsString.toInt()
                    function.invoke(points)
                }

                activity.buildActivity()
            }

        } else {
            viewHolder as SimpleActionCardViewHolder

            val move = round.roundActions.simpleActions[position - round.roundActions.sizeOfComplexActions()]
            val function = move.first
            val moveName = move.second

            viewHolder.actionNameTextView.text = moveName

            viewHolder.itemView.setOnClickListener {
                function.invoke()

                activity.buildActivity()
            }

        }

    }

    override fun getItemCount() = round.roundActions.size()

}
