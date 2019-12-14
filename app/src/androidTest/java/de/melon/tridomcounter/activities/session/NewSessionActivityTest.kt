package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.util.intendedActivity
import de.melon.tridomcounter.activities.util.performTypeTextSafe
import de.melon.tridomcounter.activities.util.withEditPlayerRecyclerView
import de.melon.tridomcounter.activities.util.withPlayerRecyclerViewName
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewSessionActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        startActivity()

    }

    @JvmField
    @Rule
    var activityRule = ActivityTestRule(NewSessionActivity::class.java)

    fun startActivity() = activityRule.launchActivity(Intent())

    val initialNumberOfPlayers = 2

    @Test
    fun initial() {
        onView(withId(R.id.numberOfPlayersTextView)).check(matches(withText("$initialNumberOfPlayers")))

        onView(withId(R.id.editPlayerRecyclerView)).check(matches(hasChildCount(initialNumberOfPlayers)))

    }

    val numberOfPlayersTextField = onView(withId(R.id.numberOfPlayersTextView))
    val numberOfPlayersMinusButton = onView(withId(R.id.numberOfPlayersMinus))
    val numberOfPlayersPlusButton = onView(withId(R.id.numberOfPlayersPlus))

    val playersRecyclerView = onView(withId(R.id.editPlayerRecyclerView))

    @Test
    fun changeNumberOfPlayers() {
        val newNumberOfPlayers = 4

        numberOfPlayersPlusButton.perform(click()).perform(click())
        playersRecyclerView.check(matches(hasChildCount(newNumberOfPlayers)))

    }

    @Test
    fun constraintNumberOfPlayers() {
        val minimumNumberOfPlayers = 2
        val maximumNumberOfPlayers = 8

        val clickIterations = maximumNumberOfPlayers - minimumNumberOfPlayers + 1

        for (i in 0..clickIterations) numberOfPlayersPlusButton.perform(click())
        numberOfPlayersTextField.check(matches(withText("$maximumNumberOfPlayers")))

        for (i in 0..clickIterations) numberOfPlayersMinusButton.perform(click())
        numberOfPlayersTextField.check(matches(withText("$minimumNumberOfPlayers")))

    }

    val newPlayer = "Fabian"
    val editPlayerRecyclerViewChildMatcher = withEditPlayerRecyclerView(R.id.editPlayerRecyclerView)
    val playerCardEditText = onView(editPlayerRecyclerViewChildMatcher.atPosition(1))

    @Test
    fun changePlayerName() {
        playerCardEditText.performTypeTextSafe(newPlayer)
        playerCardEditText.check(matches(withText("Fabian")))

    }

    @Test
    fun preservePlayerNameOnNumberChange() {
        playerCardEditText.performTypeTextSafe(newPlayer)
        numberOfPlayersPlusButton.perform(click())
        playerCardEditText.check(matches(withText(newPlayer)))

    }

    val playerCardEditTextPosition2 = onView(editPlayerRecyclerViewChildMatcher.atPosition(2))

    @Test
    fun preservePlayerNameWhenOutOfSight() {
        numberOfPlayersPlusButton.perform(click())
        playerCardEditTextPosition2.performTypeTextSafe(newPlayer)

        numberOfPlayersMinusButton.perform(click())
        numberOfPlayersPlusButton.perform(click())

        playerCardEditTextPosition2.check(matches(withText(newPlayer)))

    }

    @Test
    fun confirmPlayers() {
        onView(withId(R.id.fab)).perform(click())

        intendedActivity(SessionActivity::class.java.name)

    }

    val players = arrayOf("Fabian", "Finn", "Kenji", "Paul")
    val playerRecyclerViewChildMatcher = withPlayerRecyclerViewName(R.id.playerRecyclerView)

    @Test
    fun confirmPlayersExtended() {
        numberOfPlayersPlusButton.perform(click()).perform(click())

        for (i in players.indices)
            onView(editPlayerRecyclerViewChildMatcher.atPosition(i))
                .performTypeTextSafe(players[i])

        onView(withId(R.id.fab)).perform(click())
        intendedActivity(SessionActivity::class.java.name)

        for (i in players.indices)
            onView(playerRecyclerViewChildMatcher.atPosition(i))
                .check(matches(withText(players[i])))

    }

    @After fun tearDownIntents() = Intents.release()

}

