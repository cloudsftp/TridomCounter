package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.menu.MainActivity
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

    @JvmField
    @Rule
    val mainActivityIntent = IntentsTestRule(MainActivity::class.java, false, false)

    @Before
    fun initIntents() {
        Intents.init()

        createSession()
        startActivity()

    }

    var sessionId = 0

    val players = arrayOf("Fabian", "Tim", "Paul")

    fun createSession() {
        GameData.clear()

        val session = Session(players)
        sessionId = GameData.addSession(session)

    }

    fun startActivity() {
        val intent = Intent()
        intent.putExtra("SessionId", sessionId)
        activityRule.launchActivity(intent)

    }

    @Test
    fun t00_displayPlayers() {
        onView(withPlayerRecyclerView(R.id.playerRecyclerView).atPosition(0))
            .check(matches(withText(players[0])))

    }

    @Test
    fun t99_openAllSessions() {
        onView(withId(R.id.allSessionsButton)).perform(click())

        intendedActivity(MainActivity::class.java.name)

    }

    @After
    fun tearDownIntents() {
        Intents.release()
    }

}