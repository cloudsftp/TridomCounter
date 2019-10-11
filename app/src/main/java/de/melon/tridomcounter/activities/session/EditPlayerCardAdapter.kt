package de.melon.tridomcounter.activities.session

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import de.melon.tridomcounter.R

class EditPlayerCardAdapter
    : RecyclerView.Adapter<EditPlayerCardAdapter.EditPlayerCardViewHolder>() {

    val minNumberOfPlayers = 2
    val maxNumberOfPlayers = 6

    var numberOfPlayers = minNumberOfPlayers
    val defaultPlayerNames = ArrayList<Editable>(maxNumberOfPlayers)
    val viewHolders = ArrayList<EditPlayerCardViewHolder>(maxNumberOfPlayers)

    fun getPlayers() : Array<String> {
        val playerNames = Array(numberOfPlayers) {String()}

        for (i in playerNames.indices)
            playerNames[i] = viewHolders[i].nameTextField.text.toString()

        return playerNames

    }

    fun changeNumberOfPlayers(delta: Int) : Int {
        numberOfPlayers += delta

        if (numberOfPlayers < minNumberOfPlayers) numberOfPlayers = minNumberOfPlayers
        if (numberOfPlayers > maxNumberOfPlayers) numberOfPlayers = maxNumberOfPlayers

        return numberOfPlayers

    }

    init {
        for (i in 0 until maxNumberOfPlayers) {
            val playerEditable = Editable.Factory.getInstance().newEditable("Spieler $i")
            defaultPlayerNames.add(playerEditable)

        }

    }

    class EditPlayerCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextField = view.findViewById<EditText>(R.id.playerNameEditText)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : EditPlayerCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.edit_player_card, parent, false) as View

        return EditPlayerCardViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: EditPlayerCardViewHolder, position: Int) {
        viewHolders.add(viewHolder)
        viewHolder.nameTextField.text = defaultPlayerNames[position]
    }

    override fun getItemCount() = numberOfPlayers

}