package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.activities.util.intendedActivity
import de.melon.tridomcounter.activities.util.withActionRecyclerView
import de.melon.tridomcounter.data.GameData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoundActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        createSession()
        startActivity()

    }

    val players = arrayOf("Fabian", "Finn", "Kenji")
    val firstPlayerId = 0

    fun createSession() {
        current.sessionId = GameData.newSession(players)
        val session = GameData.sessions[current.sessionId]
        current.roundId = session.newRound(firstPlayerId)

    }

    @JvmField
    @Rule
    val activityRule = ActivityTestRule<RoundActivity>(RoundActivity::class.java, false, false)

    fun startActivity() = activityRule.launchActivity(Intent())

    @Test
    fun activePlayerDisplayed() {
        onView(withId(R.id.activePlayerNameTextView)).check(matches(withText(players[firstPlayerId])))

    }

    @Test
    fun roundNumberDisplayed() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withText("Runde ${current.roundId}")).check(matches(withParent(withId(R.id.toolbar))))

    }

    val pauseRoundCard = onView(withActionRecyclerView(R.id.commonActionRecyclerView)
        .atPosition(0))

    @Test
    fun pauseRoundCardDisplayed() {
        pauseRoundCard.check(matches(withText("Runde Pausieren")))

    }

    val makeMoveCard = onView(withActionRecyclerView(R.id.customActionRecyclerView)
        .atPosition(0))

    @Test
    fun makeMoveCardDisplayed() {
        makeMoveCard.check(matches(withText("Legen")))

    }

    @Test
    fun makeMoveChangeDisplayedPlayer() {
        checkActivePlayer(0)

        makeMove(0)

        checkActivePlayer(1)

    }

    @Test
    fun makeThreeMovesChangeDisplayedPlayer() {
        checkActivePlayer(0)

        makeMove(0)
        checkActivePlayer(1)

        makeMove(0)
        checkActivePlayer(2)

        makeMove(0)
        checkActivePlayer(0)

    }

    @Test
    fun makeMovesCheckPoints() {
        checkActivePlayerWithPoints(0, 0)

        makeMove(60)
        checkActivePlayerWithPoints(1, 0)

        makeMove(10)
        checkActivePlayerWithPoints(2, 0)

        makeMove(-5)
        checkActivePlayerWithPoints(0, 60)

        makeMove(8)
        checkActivePlayerWithPoints(1, 10)

        makeMove(0)
        checkActivePlayerWithPoints(2, -5)

        makeMove(15)
        checkActivePlayerWithPoints(0, 68)

        makeMove(0)
        checkActivePlayerWithPoints(1, 10)

        makeMove(0)
        checkActivePlayerWithPoints(2, 10)


    }

    fun checkPopupIsDisplayed() {
        onView(withId(R.id.placeDialogHeading))
            .inRoot(isDialog()).check(matches(isDisplayed()))

        onView(withId(R.id.numberOfPointsEditText))
            .inRoot(isDialog()).check(matches(isDisplayed()))

    }

    fun checkActivePlayer(playerId: Int)
            = onView(withId(R.id.activePlayerNameTextView)).check(matches(withText(players[playerId])))

    fun checkActivePlayerWithPoints(playerId: Int, points: Int) {
        checkActivePlayer(playerId)

        onView(withId(R.id.pointsTextView)).check(matches(withText("$points")))

    }

    fun makeMove(points: Int) {
        insertNumberOfPoints(points)

        // TODO

    }

    fun insertNumberOfPoints(points: Int) {
        // TODO

    }

    @Test
    fun pauseRoundCardWorks() {
        pauseRoundCard.perform(click())

        intendedActivity(SessionActivity::class.java.name)

    }

    @After fun tearDownIntents() = Intents.release()

}