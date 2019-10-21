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
import de.melon.tridomcounter.activities.util.withPlayerRecyclerViewName
import de.melon.tridomcounter.activities.util.withPlayerRecyclerViewPoints
import de.melon.tridomcounter.activities.util.withRoundRecyclerView
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.BaseMove
import de.melon.tridomcounter.logic.PlaceMove
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

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
    fun displayPointsInitial() {
        for (i in players.indices)
            onView(playerPointsRecyclerViewChild.atPosition(i))
                .check(matches(withText("0")))

    }

    @Test
    fun displayPoints() {
        current.roundId = 0

        addPointsToPlayer(0, 60)
        addPointsToPlayer(1, 15)
        addPointsToPlayer(2, -25)

        addPointsToPlayer(0, 2)
        addPointsToPlayer(1, 10)
        addPointsToPlayer(2, 14)

        stopActivity()
        startActivity()

        onView(playerPointsRecyclerViewChild.atPosition(0))
            .check(matches(withText("62")))
        onView(playerPointsRecyclerViewChild.atPosition(1))
            .check(matches(withText("25")))
        onView(playerPointsRecyclerViewChild.atPosition(2))
            .check(matches(withText("-11")))

    }

    fun addPointsToPlayer(id: Int, points: Int) {
        val round = GameData.sessions[current.sessionId].rounds[current.roundId]
        round.moves[id].add(PlaceMove(BaseMove, points))
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