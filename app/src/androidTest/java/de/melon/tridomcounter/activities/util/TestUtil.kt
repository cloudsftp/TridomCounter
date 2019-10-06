package de.melon.tridomcounter.activities.util

import android.content.res.Resources
import android.os.SystemClock
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.clearText
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import de.melon.tridomcounter.R
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withSessionRecyclerView(id: Int) = RecyclerChildViewMatcher<TextView>(id, R.id.sessionNameTextView)
fun withEditPlayerRecyclerView(id: Int) = RecyclerChildViewMatcher<EditText>(id, R.id.playerNameEditText)
fun withPlayerRecyclerView(id: Int) = RecyclerChildViewMatcher<TextView>(id, R.id.playerNameTextView)
class RecyclerChildViewMatcher<T : View>(val recyclerViewId: Int, val childViewId: Int) {

    fun atPosition(position: Int) = RecyclerChildSafeMatcher<T>(position, recyclerViewId, childViewId)
    class RecyclerChildSafeMatcher<T : View>(val position: Int, val recyclerViewId: Int, val childViewId: Int)
        : TypeSafeMatcher<View>() {

        var resources: Resources? = null

        override fun describeTo(description: Description?) {
            val idDescription = resources?.getResourceName(recyclerViewId)
            description?.appendText(idDescription)

        }

        override fun matchesSafely(view: View?): Boolean {
            resources = view?.resources

            val recyclerView = view?.rootView?.findViewById<RecyclerView>(recyclerViewId)
            val viewHolder = recyclerView?.findViewHolderForAdapterPosition(position)?.itemView
            val childView = viewHolder?.findViewById<T>(childViewId)

            return view == childView

        }

    }

}

fun ViewInteraction.performTypeTextSafe(text: String)
    = this.perform(clearText())?.perform(typeText(text))

fun sleep(millis: Long) = SystemClock.sleep(millis)

fun intendedActivity(className: String) {
    intended(hasComponent(className))

}
