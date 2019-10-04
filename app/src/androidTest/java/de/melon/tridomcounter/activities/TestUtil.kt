package de.melon.tridomcounter.activities

import android.content.res.Resources
import android.os.SystemClock
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.clearText
import android.support.test.espresso.action.ViewActions.typeText
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import de.melon.tridomcounter.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


fun withPlayersRecyclerView(id: Int) = NewPlayerRecyclerViewMatcher(id)
class NewPlayerRecyclerViewMatcher(val id: Int) {

    fun atPosition(pos: Int) : Matcher<View> {
        return RecyclerChildSafeMatcher(pos, id)

    }

    class RecyclerChildSafeMatcher(val pos: Int, val recycleViewId: Int) : TypeSafeMatcher<View>() {
        var resources: Resources? = null

        override fun describeTo(description: Description?) {
            val idDescription = resources?.getResourceName(recycleViewId)
            description?.appendText(idDescription)

        }

        override fun matchesSafely(view: View?): Boolean {
            resources = view?.resources

            val recyclerView = view?.rootView?.findViewById<RecyclerView>(recycleViewId)
            val childView = recyclerView?.findViewHolderForAdapterPosition(pos)?.itemView
            val editText = childView?.findViewById<EditText>(R.id.playerNameEditText)

            return view == editText

        }

    }

}

fun ViewInteraction.performTypeTextSafe(text: String)
    = this.perform(clearText()).perform(typeText(text))

fun sleep(millis: Long) = SystemClock.sleep(millis)
