package de.melon.tridomcounter.activities.round

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.ActionCardComplex
import de.melon.tridomcounter.logic.ActionCardSimple
import de.melon.tridomcounter.logic.DisplayCard
import de.melon.tridomcounter.logic.Round

class ActionCardAdapter(val round: Round, val activity: RoundActivity)
    : RecyclerView.Adapter<ActionCardAdapter.AbstractActionCardViewHolder>() {

    abstract class AbstractActionCardViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ComplexActionCardViewHolder(view: View) : AbstractActionCardViewHolder(view) {
        val actionNameTextView = view.findViewById<TextView>(R.id.actionNameTextView)
        val inputEditText = view.findViewById<EditText>(R.id.actionInputEditText)

    }

    open class SimpleActionCardViewHolder(view: View) : AbstractActionCardViewHolder(view) {
        val actionNameTextView = view.findViewById<TextView>(R.id.actionNameTextView)

    }

    class DisplayCardViewHolder(view: View) : SimpleActionCardViewHolder(view)

    enum class ViewType {
        DisplayCard,
        SimpleActionCard,
        ComplexActionCard,
        Undefined
    }

    override fun getItemViewType(position: Int) = when (round.cards[position]) {
        is DisplayCard -> ViewType.DisplayCard.ordinal
        is ActionCardSimple -> ViewType.SimpleActionCard.ordinal
        is ActionCardComplex -> ViewType.ComplexActionCard.ordinal
        else -> ViewType.Undefined.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): AbstractActionCardViewHolder {
        if (type == ViewType.ComplexActionCard.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_complex_action, parent, false) as View

            return ComplexActionCardViewHolder(view)

        } else if (type == ViewType.SimpleActionCard.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_action, parent, false) as View

            return SimpleActionCardViewHolder(view)

        } else if (type == ViewType.DisplayCard.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_display, parent, false) as View

            return DisplayCardViewHolder(view)

        } else {
            throw Exception("Card Type not defined.")

        }

    }

    override fun onBindViewHolder(viewHolder: AbstractActionCardViewHolder, position: Int) {
        val card = round.cards[position]

        when (getItemViewType(position)) {
            ViewType.ComplexActionCard.ordinal -> {
                viewHolder as ComplexActionCardViewHolder
                card as ActionCardComplex

                viewHolder.actionNameTextView.text = card.displayText

                viewHolder.itemView.setOnClickListener {
                    vibrate()

                    val pointsString = viewHolder.inputEditText.text.toString()
                    if (pointsString.isNotEmpty()) {
                        val points = pointsString.toInt()
                        card.function(points)
                    }

                    activity.buildActivity()
                }

                viewHolder.inputEditText.requestFocus()

            }
            ViewType.SimpleActionCard.ordinal -> {
                viewHolder as SimpleActionCardViewHolder
                card as ActionCardSimple

                viewHolder.actionNameTextView.text = card.displayText

                viewHolder.itemView.setOnClickListener {
                    vibrate()

                    card.function()

                    activity.buildActivity()
                }

            }
            ViewType.DisplayCard.ordinal -> {
                viewHolder as DisplayCardViewHolder
                card as DisplayCard

                viewHolder.actionNameTextView.text = card.displayText

            }

        }


    }

    private val v = activity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    private fun vibrate() = v.vibrate(
        VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
    )


    override fun getItemCount() = round.cards.size

}
