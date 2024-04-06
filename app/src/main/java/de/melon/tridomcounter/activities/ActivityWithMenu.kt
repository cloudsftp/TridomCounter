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
}