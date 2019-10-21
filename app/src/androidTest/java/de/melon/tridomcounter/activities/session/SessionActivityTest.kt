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
import de.melon.tridomcounter.activities.util.*
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.BaseMove
import de.melon.tridomcounter.logic.PlaceMove
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SessionActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        createSession()
        startActivity()

    }

    val players = arrayOf("Fabian", "Tim", "Paul")
    val numberOfRounds = 2

    fun createSession() {
        current.sessionId = GameData.newSession(players)

        for (i in 0 until numberOfRounds)
            GameData.sessions[current.sessionId].newRound(0)

        current.roundId = 0

        addPointsToPlayer(0, 60)
        addPointsToPlayer(1, 15)
        addPointsToPlayer(2, -25)

        addPointsToPlayer(0, 2)
        addPointsToPlayer(1, 10)
        addPointsToPlayer(2, 14)

        current.roundId = 1

        addPointsToPlayer(0, 23)
        addPointsToPlayer(1, 2)
        addPointsToPlayer(2, 0)

    }

    fun addPointsToPlayer(id: Int, points: Int) {
        val round = GameData.sessions[current.sessionId].rounds[current.roundId]
        round.moves[id].add(PlaceMove(BaseMove, points))
    }

    @JvmField
    @Rule
    val activityRule = ActivityTestRule(SessionActivity::class.java, false, false)

    fun startActivity() = activityRule.launchActivity(Intent())
    fun stopActivity() = activityRule.finishActivity()

    val playerNameRecyclerViewMatcher = withPlayerRecyclerViewName(R.id.playerRecyclerView)

    @Test
    fun displayPlayers() {
        onView(withId(R.id.playerRecyclerView)).check(matches(hasChildCount(players.size)))

        for (i in players.indices)
            onView(playerNameRecyclerViewMatcher.atPosition(i)).check(matches(withText(players[i])))

    }

    @Test
    fun displayRoundsQuantitative() {
        onView(withId(R.id.roundRecyclerView)).check(matches(hasChildCount(numberOfRounds)))

    }

    val roundRecyclerViewChild = withRoundRecyclerView(R.id.roundRecyclerView)

    @Test
    fun displayRoundsQualitative() {
        for (i in 0 until numberOfRounds)
            onView(roundRecyclerViewChild.atPosition(i)).check(matches(withText("Runde $i")))

    }

    val playerPointsRecyclerViewChild = withPlayerRecyclerViewPoints(R.id.playerRecyclerView)

    @Test
    fun displayPoints() {
        onView(playerPointsRecyclerViewChild.atPosition(0))
            .check(matches(withText("85")))
        onView(playerPointsRecyclerViewChild.atPosition(1))
            .check(matches(withText("27")))
        onView(playerPointsRecyclerViewChild.atPosition(2))
            .check(matches(withText("-11")))

    }

    val playerPointsNestedRecyclerViewChild = withRoundRecyclerViewAndPlayerRecyclerView(R.id.roundRecyclerView)

    @Test
    fun displayPointsRound() {
        onView(playerPointsNestedRecyclerViewChild.atPosition(0, 0))
            .check(matches(withText("62")))
        onView(playerPointsNestedRecyclerViewChild.atPosition(0, 1))
            .check(matches(withText("25")))
        onView(playerPointsNestedRecyclerViewChild.atPosition(0, 2))
            .check(matches(withText("-11")))

        onView(playerPointsNestedRecyclerViewChild.atPosition(1, 0))
            .check(matches(withText("23")))
        onView(playerPointsNestedRecyclerViewChild.atPosition(1, 1))
            .check(matches(withText("2")))
        onView(playerPointsNestedRecyclerViewChild.atPosition(1, 2))
            .check(matches(withText("0")))

    }

    @Test
    fun startRound() {
        onView(withId(R.id.fab)).perform(click())

        intendedActivity(NewRoundActivity::class.java.name)

    }

    @Test
    fun openAllSessions() {
        onView(withId(R.id.allSessionsButton)).perform(click())

        intendedActivity(MainActivity::class.java.name)

    }

    @After fun tearDownIntents() = Intents.release()

}