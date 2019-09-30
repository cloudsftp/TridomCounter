package de.melon.tridomcounter.activities.session

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import de.melon.tridomcounter.R

class NewPlayerCardAdapter(var playerCount: Int) : RecyclerView.Adapter<NewPlayerCardAdapter.PickPlayerViewHolder>() {

    class PickPlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameTextField: EditText

        init {
            nameTextField = view.findViewById(R.id.playerName)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PickPlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_player_card, parent, false) as View

        return PickPlayerViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: PickPlayerViewHolder, position: Int) {
        viewHolder.nameTextField.setText(String.format("Spieler %1d", position + 1))

    }

    override fun getItemCount() = playerCount

}