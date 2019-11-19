package de.melon.tridomcounter.activities.round

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.Round

class CommonActionCardAdapter(val round: Round, val activity: RoundActivity)
    : RecyclerView.Adapter<CommonActionCardAdapter.CommonActionCardViewHolder>() {

    class CommonActionCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val actionNameTextView = view.findViewById<TextView>(R.id.actionNameTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : CommonActionCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_common_action, parent, false) as View

        return CommonActionCardViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: CommonActionCardViewHolder, position: Int) {

        val move = round.commonMoveList[position]
        val function = move.first
        val moveName = move.second

        viewHolder.actionNameTextView.text = moveName

        viewHolder.itemView.setOnClickListener {
            function.invoke()

            activity.buildActivity()
        }

    }

    override fun getItemCount() = round.commonMoveList.size

}