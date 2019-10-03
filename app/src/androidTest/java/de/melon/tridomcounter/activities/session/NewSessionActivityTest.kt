package de.melon.tridomcounter.activities.session

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.*
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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

    @Test
    fun t03_constraintNumberOfPlayers() {
        val minimumNumberOfPlayers = 2
        val maximumNumberOfPlayers = 6

        val clickIterations = maximumNumberOfPlayers - minimumNumberOfPlayers + 2

        val numberOfPlayersTextField = onView(withId(R.id.numberOfPlayersText))
        val numberOfPlayersMinusButton = onView(withId(R.id.numberOfPlayersMinus))
        val numberOfPlayersPlusButton = onView(withId(R.id.numberOfPlayersPlus))

        for (i in 0..clickIterations) numberOfPlayersPlusButton.perform(click())
        numberOfPlayersTextField.check(matches(withText("$maximumNumberOfPlayers")))

        for (i in 0..clickIterations) numberOfPlayersMinusButton.perform(click())
        numberOfPlayersTextField.check(matches(withText("$minimumNumberOfPlayers")))

    }

    @Test
    fun t04_changePlayerName() {
        val newPlayer = "Fabian"

        val playerCard = onView(withRecycleView(R.id.newPlayerNamesRecyclerView).atPosition(1))

        playerCard.perform(insertPlayerName(newPlayer))
        playerCard.check(checkPlayerName(newPlayer))

    }

}

