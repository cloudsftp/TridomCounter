package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
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

    @After fun tearDownIntents() = Intents.release()

}