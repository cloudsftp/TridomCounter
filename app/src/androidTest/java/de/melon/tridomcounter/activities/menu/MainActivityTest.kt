package de.melon.tridomcounter.activities.menu

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.session.NewSessionActivity
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.activities.util.intendedActivity
import de.melon.tridomcounter.activities.util.withSessionRecyclerView
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.Session
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        createSessions()
        startActivity()

    }

    val numberOfSessions = 2
    val sessions = Array(numberOfSessions) {Session(Array(0) { String()})}

    fun createSessions() {
        for (session in sessions)
            GameData.addSession(session)

    }

    @JvmField
    @Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    fun startActivity() = activityRule.launchActivity(Intent())

    val sessionRecyclerView = onView(withId(R.id.sessionRecyclerView))

    @Test
    fun t01_displaySessionsQuantitative() {
        sessionRecyclerView.check(matches(hasChildCount(numberOfSessions)))

    }

    val sessionRecyclerViewChild = withSessionRecyclerView(R.id.sessionRecyclerView)

    @Test
    fun t02_displaySessionsQualitative() {
        onView(sessionRecyclerViewChild.atPosition(0)).check(matches(withText("Session 0")))
        onView(sessionRecyclerViewChild.atPosition(1)).check(matches(withText("Session 1")))

    }

    @Test
    fun t98_openSession() {
        val sessionId = 1

        onView(withSessionRecyclerView(R.id.sessionRecyclerView).atPosition(1)).perform(click())

        intendedActivity(SessionActivity::class.java.name)

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withText("Session $sessionId")).check(matches(withParent(withId(R.id.toolbar))))

    }

    @Test
    fun t99_newSession() {
        onView(withId(R.id.fab)).perform(click())

        intendedActivity(NewSessionActivity::class.java.name)

    }

    @After fun tearDownIntents() = Intents.release()

}
