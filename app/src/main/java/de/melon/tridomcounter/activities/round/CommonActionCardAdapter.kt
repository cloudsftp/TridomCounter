package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.logic.Round

class CommonActionCardAdapter(val round: Round)
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