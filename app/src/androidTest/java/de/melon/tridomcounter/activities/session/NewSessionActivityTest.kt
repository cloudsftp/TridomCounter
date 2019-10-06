package de.melon.tridomcounter.activities.session

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
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class NewSessionActivityTest {

    @Before fun initIntents() = Intents.init()

    val initialNumberOfPlayers = 2

    @JvmField
    @Rule
    var activityRule = ActivityTestRule(NewSessionActivity::class.java)

    @Test
    fun t01_initial() {
        onView(withId(R.id.numberOfPlayersText)).check(matches(withText("$initialNumberOfPlayers")))

        onView(withId(R.id.editPlayerRecyclerView)).check(matches(hasChildCount(initialNumberOfPlayers)))

    }

    val numberOfPlayersTextField = onView(withId(R.id.numberOfPlayersText))
    val numberOfPlayersMinusButton = onView(withId(R.id.numberOfPlayersMinus))
    val numberOfPlayersPlusButton = onView(withId(R.id.numberOfPlayersPlus))

    val playersRecyclerView = onView(withId(R.id.editPlayerRecyclerView))

    @Test
    fun t02_changeNumberOfPlayers() {
        val newNumberOfPlayers = 4

        numberOfPlayersPlusButton.perform(click()).perform(click())
        playersRecyclerView.check(matches(hasChildCount(newNumberOfPlayers)))

    }

    @Test
    fun t03_constraintNumberOfPlayers() {
        val minimumNumberOfPlayers = 2
        val maximumNumberOfPlayers = 6

        val clickIterations = maximumNumberOfPlayers - minimumNumberOfPlayers + 1

        for (i in 0..clickIterations) numberOfPlayersPlusButton.perform(click())
        numberOfPlayersTextField.check(matches(withText("$maximumNumberOfPlayers")))

        for (i in 0..clickIterations) numberOfPlayersMinusButton.perform(click())
        numberOfPlayersTextField.check(matches(withText("$minimumNumberOfPlayers")))

    }

    val newPlayer = "Fabian"
    val playerCardEditText = onView(withEditPlayerRecyclerView(R.id.editPlayerRecyclerView).atPosition(1))

    @Test
    fun t04_changePlayerName() {
        playerCardEditText.performTypeTextSafe(newPlayer)
        playerCardEditText.check(matches(withText("Fabian")))

    }

    @Test
    fun t05_preservePlayerNameOnNumberChange() {
        playerCardEditText.performTypeTextSafe(newPlayer)
        numberOfPlayersPlusButton.perform(click())
        playerCardEditText.check(matches(withText(newPlayer)))

    }

    val playerCardEditTextPosition2 = onView(withEditPlayerRecyclerView(R.id.editPlayerRecyclerView).atPosition(2))

    @Test
    fun t06_preservePlayerNameWhenOutOfSight() {
        numberOfPlayersPlusButton.perform(click())
        playerCardEditTextPosition2.performTypeTextSafe(newPlayer)

        numberOfPlayersMinusButton.perform(click())
        numberOfPlayersPlusButton.perform(click())

        playerCardEditTextPosition2.check(matches(withText(newPlayer)))

    }

    @Test
    fun t99_confirmPlayers() {
        onView(withId(R.id.fab)).perform(click())

        intendedActivity(SessionActivity::class.java.name)

    }

    @After fun tearDownIntents() = Intents.release()

}

