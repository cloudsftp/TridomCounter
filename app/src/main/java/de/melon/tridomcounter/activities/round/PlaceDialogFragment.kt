package de.melon.tridomcounter.activities.round

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import de.melon.tridomcounter.R

class PlaceDialogFragment : DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)

            builder.setView(requireActivity().layoutInflater.inflate(R.layout.dialog_place, null))
            builder.setPositiveButton(getString(R.string.confirm)) {
                dialog, which ->

            }

            builder.create()

        }
    }

}

