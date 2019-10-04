package de.melon.tridomcounter.activities

import android.content.res.Resources
import android.os.SystemClock
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.clearText
import android.support.test.espresso.action.ViewActions.typeText
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import de.melon.tridomcounter.R
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withSessionRecyclerView(id: Int) = SessionRecyclerViewMatcher(id)
class SessionRecyclerViewMatcher(val id: Int) {

    fun atPosition(pos: Int) = RecyclerChildSafeMatcher(pos, id)

    class RecyclerChildSafeMatcher(val pos: Int, val recyclerViewId: Int) : TypeSafeMatcher<View>() {
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

            return view == textField as View

        }

    }

}

fun withPlayersRecyclerView(id: Int) = PlayerRecyclerViewMatcher(id)
class PlayerRecyclerViewMatcher(val id: Int) {

    fun atPosition(pos: Int) = RecyclerChildSafeMatcher(pos, id)

    class RecyclerChildSafeMatcher(val pos: Int, val recyclerViewId: Int) : TypeSafeMatcher<View>() {
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

fun ViewInteraction.performTypeTextSafe(text: String)
    = this.perform(clearText()).perform(typeText(text))

fun sleep(millis: Long) = SystemClock.sleep(millis)
