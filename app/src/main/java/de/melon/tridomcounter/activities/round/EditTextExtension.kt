package de.melon.tridomcounter.activities.round

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onSubmit(func: () -> Unit) = setOnEditorActionListener {
        _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT)
            func()

        true

}
