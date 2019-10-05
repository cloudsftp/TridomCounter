package de.melon.tridomcounter.activities.session

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.RecyclerViewMatcher
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withEditPlayerRecyclerView(id: Int) = EditPlayerRecyclerViewMatcher(id)
class EditPlayerRecyclerViewMatcher(id: Int) : RecyclerViewMatcher(id) {

    fun playerAtPosition(pos: Int) = RecyclerPlayerSafeMatcher(pos, id)
    class RecyclerPlayerSafeMatcher(val pos: Int, val recyclerViewId: Int) : TypeSafeMatcher<View>() {
        var resources: Resources? = null

        override fun describeTo(description: Description?) {
            val idDescription = resources?.getResourceName(recyclerViewId)
            description?.appendText(idDescription)

        }

        override fun matchesSafely(view: View?): Boolean {
            resources = view?.resources

            val recyclerView = view?.rootView?.findViewById<RecyclerView>(recyclerViewId)
            val childView = recyclerView?.findViewHolderForAdapterPosition(pos)?.itemView
            val editText = childView?.findViewById<EditText>(R.id.playerNameEditText)

            return view == editText as View

        }

    }

}

fun withPlayerRecyclerView(id: Int) = PlayerRecyclerViewMatcher(id)
class PlayerRecyclerViewMatcher(id: Int) : RecyclerViewMatcher(id) {

    fun playerAtPosition(pos: Int) = RecyclerPlayerSafeMatcher(pos, id)
    class RecyclerPlayerSafeMatcher(val pos: Int, val recyclerViewId: Int) : TypeSafeMatcher<View>() {
        var resources: Resources? = null

        override fun describeTo(description: Description?) {
            val idDescription = resources?.getResourceName(recyclerViewId)
            description?.appendText(idDescription)

        }

        override fun matchesSafely(view: View?): Boolean {
            resources = view?.resources

            val recyclerView = view?.rootView?.findViewById<RecyclerView>(recyclerViewId)
            val childView = recyclerView?.findViewHolderForAdapterPosition(pos)?.itemView
            val textView = childView?.findViewById<TextView>(R.id.playerNameTextView)

            return view == textView as View

        }

    }

}
