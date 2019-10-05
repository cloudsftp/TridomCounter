package de.melon.tridomcounter.activities.menu

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.RecyclerViewMatcher
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withSessionRecyclerView(id: Int) = SessionRecyclerViewMatcher(id)
class SessionRecyclerViewMatcher(id: Int) : RecyclerViewMatcher(id) {

    fun titleAtPosition(pos: Int) = RecyclerTitleSafeMatcher(pos, id)
    class RecyclerTitleSafeMatcher(val pos: Int, val recyclerViewId: Int) : TypeSafeMatcher<View>() {
        var resources: Resources? = null

        override fun describeTo(description: Description?) {
            val idDescription = resources?.getResourceName(recyclerViewId)
            description?.appendText(idDescription)

        }

        override fun matchesSafely(view: View?): Boolean {
            resources = view?.resources

            val recyclerView = view?.rootView?.findViewById<RecyclerView>(recyclerViewId)
            val childView = recyclerView?.findViewHolderForAdapterPosition(pos)?.itemView
            val textField = childView?.findViewById<TextView>(R.id.sessionNameTextView)

            return view == textField

        }

    }

}
