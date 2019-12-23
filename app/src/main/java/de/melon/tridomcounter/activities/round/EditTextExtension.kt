package de.melon.tridomcounter.activities.round

import android.view.KeyEvent
import android.widget.EditText

fun EditText.onSubmit(func: () -> Unit) = setOnKeyListener {
    _, keyCode, event ->

    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
        func()

        true

    }

    false

}
