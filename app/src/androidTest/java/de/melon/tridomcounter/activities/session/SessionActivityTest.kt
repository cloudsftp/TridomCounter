package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
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
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.Session
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class SessionActivityTest {

    @JvmField
    @Rule
    val activityRule = ActivityTestRule(SessionActivity::class.java, false, false)

    @Before
    fun initIntents() {
        Intents.init()

        createSession()
        startActivity()

    }

    val players = arrayOf("Fabian", "Tim", "Paul")

    fun createSession() {
        val session = Session(players)
        current.sessionId = GameData.addSession(session)

    }

    fun startActivity() {
        val intent = Intent()
        activityRule.launchActivity(intent)

    }

    val playerRecyclerViewMatcher = withPlayerRecyclerView(R.id.playerRecyclerView)

    @Test
    fun t00_displayPlayers() {
        onView(withId(R.id.playerRecyclerView)).check(matches(hasChildCount(players.size)))

        for (i in players.indices)
            onView(playerRecyclerViewMatcher.atPosition(i)).check(matches(withText(players[i])))

    }

    @Test
    fun t02_displayRounds() {
        val numberOfRounds = 4

        for (i in 0 until numberOfRounds)
            GameData.sessions[current.sessionId]!!.newRound()

        onView(withId(R.id.fab)).perform(click())
        pressBack()

        onView(withId(R.id.roundRecyclerView)).check(matches(hasChildCount(numberOfRounds)))

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