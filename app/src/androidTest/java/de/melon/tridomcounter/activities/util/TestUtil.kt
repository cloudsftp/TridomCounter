package de.melon.tridomcounter.activities.util

import android.content.res.Resources
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import de.melon.tridomcounter.R
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withActionRecyclerView(id: Int) = RecyclerChildViewMatcher<TextView>(id, R.id.actionNameTextView)
fun withChoosePlayerRecyclerView(id: Int) = RecyclerChildViewMatcher<TextView>(id, R.id.playerNameTextView)
fun withRoundRecyclerView(id: Int) = RecyclerChildViewMatcher<TextView>(id, R.id.roundNameTextView)
fun withSessionRecyclerView(id: Int) = RecyclerChildViewMatcher<TextView>(id, R.id.sessionNameTextView)
fun withEditPlayerRecyclerView(id: Int) = RecyclerChildViewMatcher<EditText>(id, R.id.playerNameEditText)
fun withPlayerRecyclerViewName(id: Int) = RecyclerChildViewMatcher<TextView>(id, R.id.playerNameTextView)
fun withPlayerRecyclerViewPoints(id: Int) = RecyclerChildViewMatcher<TextView>(id, R.id.playerPointsTextView)
class RecyclerChildViewMatcher<T : View>(val recyclerViewId: Int, val childViewId: Int) {

    fun atPosition(position: Int) = RecyclerChildSafeMatcher<T>(position, recyclerViewId, childViewId)
    class RecyclerChildSafeMatcher<T : View>(val position: Int, recyclerViewId: Int, val childViewId: Int)
            : RecyclerViewChildSafeMatcherAbstract(recyclerViewId) {

        override fun matchesSafely(view: View?): Boolean {
            resources = view?.resources

            val recyclerView = view?.rootView?.findViewById<RecyclerView>(recyclerViewId)
            val viewHolder = recyclerView?.findViewHolderForAdapterPosition(position)
            val childView = viewHolder?.itemView?.findViewById<T>(childViewId)

            return view == childView

        }

    }

}

fun withRoundRecyclerViewAndPlayerRecyclerView(id: Int, nestedId: Int) = NestedRecyclerChildViewMatcher<TextView>(id, nestedId, R.id.playerPointsTextView)
class NestedRecyclerChildViewMatcher<T : View>(val recyclerViewId: Int, val nestedRecyclerViewId: Int, val childViewId: Int) {

    fun atPosition(positionRecyclerView: Int, position: Int)
            = NestedRecyclerViewChildSafeMatcher<T>(positionRecyclerView, position, recyclerViewId, nestedRecyclerViewId, childViewId)
    class NestedRecyclerViewChildSafeMatcher<T : View>(val positionRecyclerView: Int, val position: Int,
                                                       recyclerViewId: Int, val nestedRecyclerViewId: Int, val childViewId: Int)
        : RecyclerViewChildSafeMatcherAbstract(recyclerViewId) {

        override fun matchesSafely(view: View?): Boolean {
            resources = view?.resources

            val recyclerView = view?.rootView?.findViewById<RecyclerView>(recyclerViewId)
            val nestedRecyclerViewHolder = recyclerView?.findViewHolderForAdapterPosition(positionRecyclerView)
            val nestedRecyclerView = nestedRecyclerViewHolder?.itemView?.findViewById<RecyclerView>(nestedRecyclerViewId)
            val viewHolder = nestedRecyclerView?.findViewHolderForAdapterPosition(position)
            val childView = viewHolder?.itemView?.findViewById<T>(childViewId)

            return view == childView

        }

    }

}

abstract class RecyclerViewChildSafeMatcherAbstract(val recyclerViewId: Int) : TypeSafeMatcher<View>() {

    var resources: Resources? = null

    override fun describeTo(description: Description?) {
        val idDescription = resources?.getResourceName(recyclerViewId)
        description?.appendText(idDescription)

    }

}

fun checkTitle(text: String) {
    Espresso.onView(ViewMatchers.withId(R.id.toolbar))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    Espresso.onView(ViewMatchers.withText(text))
        .check(ViewAssertions.matches(ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))))

}

fun ViewInteraction.performTypeTextSafe(text: String)
    = this.perform(clearText())?.perform(typeText(text))

fun intendedActivity(className: String) {
    intended(hasComponent(className))

}
