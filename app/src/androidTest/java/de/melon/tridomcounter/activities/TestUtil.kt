package de.melon.tridomcounter.activities

import android.content.res.Resources
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import de.melon.tridomcounter.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


fun withRecycleView(id: Int) = RecyclerViewMatcher(id)
class RecyclerViewMatcher(val id: Int) {

    fun atPosition(pos: Int) : Matcher<View> {
        return RecyclerChildSafeMatcher(pos, id)

    }

    class RecyclerChildSafeMatcher(val pos: Int, val recycleViewId: Int) : TypeSafeMatcher<View>() {
        lateinit var resources: Resources

        override fun describeTo(description: Description?) {
            var idDescription: String
            try {
                idDescription = resources.getResourceName(recycleViewId)
            } catch (e: Resources.NotFoundException) {
                idDescription = "$recycleViewId (resource name not found)"
            }

            description?.appendText(idDescription)
        }

        override fun matchesSafely(view: View?): Boolean {
            resources = view!!.resources

            val recyclerView = view.rootView.findViewById<RecyclerView>(recycleViewId)
            val childView = recyclerView.findViewHolderForAdapterPosition(pos)?.itemView

            return view == childView

        }

    }

}

fun insertPlayerName(name: String) = NewPlayerCardAction(name)
class NewPlayerCardAction(val name: String) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.withParent(ViewMatchers.withId(R.id.newPlayerNamesRecyclerView))
    }

    override fun getDescription(): String {
        return "Change Text of New Player Card to $name"
    }

    override fun perform(uiController: UiController?, view: View?) {
        val textView = view?.findViewById<EditText>(R.id.playerName)
        textView?.setText(name)

    }

}

fun checkPlayerName(name: String) = NewPlayerCardAssertion(name)
class NewPlayerCardAssertion(val name: String) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        val textView = view?.findViewById<EditText>(R.id.playerName)
        assert(textView?.text!!.equals(name))
    }

}
