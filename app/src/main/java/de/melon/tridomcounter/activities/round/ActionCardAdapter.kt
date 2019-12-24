package de.melon.tridomcounter.activities.round

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.logic.round.*

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
        COMPLEX_ACTION_CARD,
        CHOICE_ACTION_CARD,
        SIMPLE_ACTION_CARD,
        DISPLAY_CARD,
        UNDEFINED
    }

    override fun getItemViewType(position: Int) = when (round.cards[position]) {
        is DisplayCard -> ViewType.DISPLAY_CARD.ordinal
        is ActionCardSimple -> ViewType.SIMPLE_ACTION_CARD.ordinal
        is ActionCardChoice -> ViewType.CHOICE_ACTION_CARD.ordinal
        is ActionCardComplex -> ViewType.COMPLEX_ACTION_CARD.ordinal
        else -> ViewType.UNDEFINED.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): AbstractActionCardViewHolder {
        return when (type) {
            ViewType.COMPLEX_ACTION_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_action_complex, parent, false) as View

                ComplexActionCardViewHolder(view)

            }

            ViewType.CHOICE_ACTION_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_action, parent, false) as View

                SimpleActionCardViewHolder(view)

            }

            ViewType.SIMPLE_ACTION_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_action, parent, false) as View

                SimpleActionCardViewHolder(view)

            }

            ViewType.DISPLAY_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_display, parent, false) as View

                DisplayCardViewHolder(view)

            }

            else -> throw Exception("Card Type not defined.")

        }

    }

    override fun onBindViewHolder(viewHolder: AbstractActionCardViewHolder, position: Int) {
        val card = round.cards[position]

        when (getItemViewType(position)) {
            ViewType.COMPLEX_ACTION_CARD.ordinal -> {
                viewHolder as ComplexActionCardViewHolder
                card as ActionCardComplex

                viewHolder.actionNameTextView.text = card.displayText

                fun getInput() {
                    val pointsString = viewHolder.inputEditText.text.toString()
                    if (pointsString.isNotEmpty()) {
                        val points = pointsString.toInt()
                        card.function(points)
                        vibrate()

                        activity.buildActivity()

                    }

                }

                viewHolder.itemView.setOnClickListener { getInput() }
                viewHolder.inputEditText.onSubmit { getInput() }

                viewHolder.inputEditText.requestFocus()

            }

            ViewType.CHOICE_ACTION_CARD.ordinal -> {
                viewHolder as SimpleActionCardViewHolder
                card as ActionCardChoice

                viewHolder.actionNameTextView.text = card.displayText

                fun chooseCard() {
                    vibrate()

                    card.function(position)

                    activity.buildActivity()

                }

                viewHolder.itemView.setOnClickListener { chooseCard() }

            }

            ViewType.SIMPLE_ACTION_CARD.ordinal -> {
                viewHolder as SimpleActionCardViewHolder
                card as ActionCardSimple

                viewHolder.actionNameTextView.text = card.displayText

                viewHolder.itemView.setOnClickListener {
                    vibrate()

                    card.function()

                    activity.buildActivity()
                }

            }

            ViewType.DISPLAY_CARD.ordinal -> {
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
