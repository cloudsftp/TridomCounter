package de.melon.tridomcounter.activities.round

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.util.intendedActivity
import de.melon.tridomcounter.activities.util.withChoosePlayerRecyclerView
import de.melon.tridomcounter.data.GameData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewRoundActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        createSession()
        startActivity()

    }

    val players = arrayOf("Fabian", "Tim", "Paul")

    fun createSession() {
        current.sessionId = GameData.newSession(players)

    }

    @JvmField
    @Rule
    val activityRule = ActivityTestRule<NewRoundActivity>(NewRoundActivity::class.java, false, false)

    fun startActivity() = activityRule.launchActivity(Intent())

    val choosePlayerRecyclerViewChild = withChoosePlayerRecyclerView(R.id.choosePlayerRecyclerView)

    @Test
    fun displayPlayersQuantitative() {
        onView(withId(R.id.choosePlayerRecyclerView)).check(matches(hasChildCount(players.size)))

    }

    @Test
    fun displayPlayersQualitative() {
        for (i in players.indices)
            onView(choosePlayerRecyclerViewChild.atPosition(i)).check(matches(withText(players[i])))

    }

    @Test
    fun choosePlayer() {
        onView(choosePlayerRecyclerViewChild.atPosition(0)).perform(click())

        intendedActivity(RoundActivity::class.java.name)

    }

    @After fun tearDownIntents() = Intents.release()

}