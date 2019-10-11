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
    val playerNames = Array(maxNumberOfPlayers) {String()}
    val viewHolders = Array<EditPlayerCardViewHolder?>(maxNumberOfPlayers) {null}

    init {
        for (i in 0 until maxNumberOfPlayers)
            playerNames[i] = "Spieler $i"
    }

    val editableFactory = Editable.Factory.getInstance()

    fun savePlayerNames() {
        for (i in 0 until numberOfPlayers)
            playerNames[i] = viewHolders[i]?.nameTextField?.text.toString()

    }

    fun getNeededPlayerNames() = playerNames
        .filterIndexed {i, _ -> i < numberOfPlayers}
        .toTypedArray()

    fun changeNumberOfPlayers(delta: Int) : Int {
        savePlayerNames()

        numberOfPlayers += delta

        if (numberOfPlayers < minNumberOfPlayers) numberOfPlayers = minNumberOfPlayers
        if (numberOfPlayers > maxNumberOfPlayers) numberOfPlayers = maxNumberOfPlayers

        return numberOfPlayers

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
        viewHolder.nameTextField.text = editableFactory.newEditable(playerNames[position])
        viewHolders[position] = viewHolder

    }

    override fun getItemCount() = numberOfPlayers

}