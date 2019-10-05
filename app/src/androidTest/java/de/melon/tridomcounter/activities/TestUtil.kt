package de.melon.tridomcounter.activities

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
import de.melon.tridomcounter.R
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

abstract class RecyclerViewMatcher(val id: Int) {

    fun atPosition(pos: Int) = RecyclerCardSafeMatcher(pos, id)
    class RecyclerCardSafeMatcher(val pos: Int, val recyclerViewId: Int) : TypeSafeMatcher<View>() {
        var resources: Resources? = null

        override fun describeTo(description: Description?) {
            val idDescription = resources?.getResourceName(recyclerViewId)
            description?.appendText(idDescription)
        }

        override fun matchesSafely(view: View?): Boolean {
            resources = view?.resources

            val recyclerView = view?.rootView?.findViewById<RecyclerView>(recyclerViewId)
            val childView = recyclerView?.findViewHolderForAdapterPosition(pos)?.itemView

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
