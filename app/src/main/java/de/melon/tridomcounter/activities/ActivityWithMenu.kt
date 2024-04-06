package de.melon.tridomcounter.activities

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import de.melon.tridomcounter.R

open class ActivityWithMenu : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            else                    -> super.onContextItemSelected(item)
        }

        return true
    }

    /*
    private fun displayHint() {
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(getString(R.string.help))
        alertDialog.setMessage(round.help())
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK") {
                dialog, _ -> dialog.dismiss()
        }

        alertDialog.show()

        return true
    }
     */
}