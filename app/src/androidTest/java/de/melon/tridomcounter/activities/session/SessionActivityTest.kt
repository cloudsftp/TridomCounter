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
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.menu.MainActivity
import de.melon.tridomcounter.activities.round.NewRoundActivity
import de.melon.tridomcounter.activities.util.intendedActivity
import de.melon.tridomcounter.activities.util.withPlayerRecyclerView
import de.melon.tridomcounter.activities.util.withRoundRecyclerView
import de.melon.tridomcounter.data.GameData
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class SessionActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        createSession()
        startActivity()

    }

    val players = arrayOf("Fabian", "Tim", "Paul")
    val numberOfRounds = 4

    fun createSession() {
        current.sessionId = GameData.newSession(players)

        for (i in 0 until numberOfRounds)
            GameData.sessions[current.sessionId].newRound(0)

    }

    @JvmField
    @Rule
    val activityRule = ActivityTestRule(SessionActivity::class.java, false, false)

    fun startActivity() = activityRule.launchActivity(Intent())

    val playerRecyclerViewMatcher = withPlayerRecyclerView(R.id.playerRecyclerView)

    @Test
    fun t00_displayPlayers() {
        onView(withId(R.id.playerRecyclerView)).check(matches(hasChildCount(players.size)))

        for (i in players.indices)
            onView(playerRecyclerViewMatcher.atPosition(i)).check(matches(withText(players[i])))

    }

    @Test
    fun t01_displayRoundsQuantitative() {
        onView(withId(R.id.roundRecyclerView)).check(matches(hasChildCount(numberOfRounds)))

    }

    val roundRecyclerViewChild = withRoundRecyclerView(R.id.roundRecyclerView)

    @Test
    fun t02_displayRoundsQualitative() {
        for (i in 0 until numberOfRounds)
            onView(roundRecyclerViewChild.atPosition(i)).check(matches(withText("Runde $i")))

    }

    @Test
    fun t98_startRound() {
        onView(withId(R.id.fab)).perform(click())

        intendedActivity(NewRoundActivity::class.java.name)

    }

    @Test
    fun t99_openAllSessions() {
        onView(withId(R.id.allSessionsButton)).perform(click())

        intendedActivity(MainActivity::class.java.name)

    }

    @After fun tearDownIntents() = Intents.release()

}