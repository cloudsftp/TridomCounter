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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        createSessions()
        startActivity()

    }

    val numberOfSessions = 2

    fun createSessions() {
        GameData.clear()

        for (i in 0 until numberOfSessions)
            GameData.newSession(arrayOf(String()))

    }

    @JvmField
    @Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    fun startActivity() = activityRule.launchActivity(Intent())

    val sessionRecyclerView = onView(withId(R.id.sessionRecyclerView))

    @Test
    fun displaySessionsQuantitative() {
        sessionRecyclerView.check(matches(hasChildCount(numberOfSessions)))

    }

    val sessionRecyclerViewChild = withSessionRecyclerView(R.id.sessionRecyclerView)

    @Test
    fun displaySessionsQualitative() {
        onView(sessionRecyclerViewChild.atPosition(0)).check(matches(withText("Session 0")))
        onView(sessionRecyclerViewChild.atPosition(1)).check(matches(withText("Session 1")))

    }

    @Test
    fun openSession() {
        val sessionId = 1

        onView(withSessionRecyclerView(R.id.sessionRecyclerView).atPosition(1)).perform(click())

        intendedActivity(SessionActivity::class.java.name)

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withText("Session $sessionId")).check(matches(withParent(withId(R.id.toolbar))))

    }

    @Test
    fun newSession() {
        onView(withId(R.id.fab)).perform(click())

        intendedActivity(NewSessionActivity::class.java.name)

    }

    @After fun tearDownIntents() = Intents.release()

}
