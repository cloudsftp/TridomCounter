package de.melon.tridomcounter.activities.session

import android.support.test.espresso.Espresso.onView
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

    lateinit var numOfPlayersPicker: NumberPicker

    @JvmField
    @Rule
    var activityRule = ActivityTestRule(NewSessionActivity::class.java)

    @Test
    fun t01_setNumberOfPlayers() {
        numOfPlayersPicker = activityRule.activity.findViewById<NumberPicker>(R.id.numOfPlayersPicker)
        assert(numOfPlayersPicker.value == initialNumberOfPlayers)

        onView(withId(R.id.newPlayerNamesRecyclerView)).check(matches(hasChildCount(initialNumberOfPlayers)))

    }

}