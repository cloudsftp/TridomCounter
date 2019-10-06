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
    val playerNames = ArrayList<Editable>(maxNumberOfPlayers)

    fun getPlayers() = playerNames.filterIndexed {i, _ ->  i < numberOfPlayers} .toTypedArray()

    fun changeNumberOfPlayers(delta: Int) : Int {
        numberOfPlayers += delta

        if (numberOfPlayers < minNumberOfPlayers) numberOfPlayers = minNumberOfPlayers
        if (numberOfPlayers > maxNumberOfPlayers) numberOfPlayers = maxNumberOfPlayers

        return numberOfPlayers

    }

    init {
        for (i in 0 until maxNumberOfPlayers) {
            val playerEditable = Editable.Factory.getInstance().newEditable("Spieler $i")
            playerNames.add(playerEditable)

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
        viewHolder.nameTextField.text = playerNames[position]
        viewHolder.nameTextField.setOnKeyListener(View.OnKeyListener {
            view, _, _ ->
            playerNames[position] = (view as EditText).text
            return@OnKeyListener true
        })
    }

    override fun getItemCount() = numberOfPlayers

}