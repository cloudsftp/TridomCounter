package de.melon.tridomcounter.activities

import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import de.melon.tridomcounter.R

open class ActivityWithMenu() : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_privacy     -> openPrivacyDeclaration()
            else                    -> super.onContextItemSelected(item)
        }

        return true
    }

    protected fun openPrivacyDeclaration() {
        val url = resources.getString(R.string.privacy_url)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}