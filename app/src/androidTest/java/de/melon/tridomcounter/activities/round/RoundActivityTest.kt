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
import de.melon.tridomcounter.activities.util.performTypeTextSafe
import de.melon.tridomcounter.activities.util.withActionRecyclerView
import de.melon.tridomcounter.data.GameData
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    fun t00_activePlayerDisplayed() {
        onView(withId(R.id.activePlayerNameTextView)).check(matches(withText(players[firstPlayerId])))

    }

    @Test
    fun t01_roundNumberDisplayed() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withText("Runde ${current.roundId}")).check(matches(withParent(withId(R.id.toolbar))))

    }

    val pauseRoundCard = onView(withActionRecyclerView(R.id.actionRecyclerView)
        .atPosition(0))

    @Test
    fun t02_pauseRoundCardDisplayed() {
        pauseRoundCard.check(matches(withText("Runde Pausieren")))

    }

    val makeMoveCard = onView(withActionRecyclerView(R.id.playerActionRecyclerView)
        .atPosition(0))

    @Test
    fun t04_makeMoveCardDisplayed() {
        makeMoveCard.check(matches(withText("Legen")))

    }

    @Test
    fun t05_makeMoveCardWorks() {
        makeMoveCard.perform(click())
        checkPopupIsDisplayed()

    }

    @Test
    fun t06_makeMoveChangeDisplayedPlayer() {
        checkActivePlayer(0)

        makeMove(0)

        checkActivePlayer(1)

    }

    @Test
    fun t07_makeThreeMovesChangeDisplayedPlayer() {
        checkActivePlayer(0)

        makeMove(0)
        checkActivePlayer(1)

        makeMove(0)
        checkActivePlayer(2)

        makeMove(0)
        checkActivePlayer(0)

    }

    @Test
    fun t08_makeMovesCheckPoints() {
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
        makeMoveCard.perform(click())
        checkPopupIsDisplayed()

        insertNumberOfPoints(points)

    }

    fun insertNumberOfPoints(points: Int) {
        onView(withId(R.id.numberOfPointsEditText)).performTypeTextSafe("$points")
        onView(withId(R.id.acceptButton)).perform(click())

    }

    @Test
    fun t99_pauseRoundCardWorks() {
        pauseRoundCard.perform(click())

        intendedActivity(SessionActivity::class.java.name)

    }

    @After fun tearDownIntents() = Intents.release()

}