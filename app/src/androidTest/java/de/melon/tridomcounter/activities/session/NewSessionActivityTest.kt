package de.melon.tridomcounter.activities.session

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.NumberPicker
import de.melon.tridomcounter.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewSessionActivityTest {

    val initialNumberOfPlayers = 2

    @JvmField
    @Rule
    var activityRule = ActivityTestRule(NewSessionActivity::class.java)

    @Test
    fun t01_initial() {
        onView(withId(R.id.numberOfPlayersText)).check(matches(withText("$initialNumberOfPlayers")))

        onView(withId(R.id.newPlayerNamesRecyclerView)).check(matches(hasChildCount(initialNumberOfPlayers)))

    }

    @Test
    fun t02_changeNumberOfPlayers() {
        val newNumberOfPlayers = 4

        onView(withId(R.id.numberOfPlayersPlus)).perform(click()).perform(click())

        onView(withId(R.id.newPlayerNamesRecyclerView)).check(matches(hasChildCount(newNumberOfPlayers)))

    }

}