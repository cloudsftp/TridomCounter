package de.melon.tridomcounter.activities.menu

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.session.NewSessionActivity
import de.melon.tridomcounter.activities.session.SessionActivity
import de.melon.tridomcounter.activities.sleep
import de.melon.tridomcounter.activities.withSessionRecyclerView
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.Session
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @JvmField
    @Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    val sessionsRecyclerView = onView(withId(R.id.sessionsRecyclerView))

    val numberOfSessions = 2
    val sessions = Array(numberOfSessions) {Session(Array(0) { String()})}

    @Test
    fun t01_displaySessionsQuantitative() {
        GameData.sessions.addAll(sessions)

        onView(withId(R.id.fab)).perform(click())
        pressBack()

        sessionsRecyclerView.check(matches(hasChildCount(numberOfSessions)))

    }

    @Test
    fun t02_displaySessionsQualitative() {
        val sessionRecyclerViewMatcher = withSessionRecyclerView(R.id.sessionsRecyclerView)
        onView(sessionRecyclerViewMatcher.titleAtPosition(0)).check(matches(withText("Session 0")))
        onView(sessionRecyclerViewMatcher.titleAtPosition(1)).check(matches(withText("Session 1")))

    }

    @Before
    fun initIntents() {
        Intents.init()
    }

    @After
    fun tearDownIntents() {
        Intents.release()
    }

    @Test
    fun t98_openSession() {
        val sessionId = 1

        onView(withSessionRecyclerView(R.id.sessionsRecyclerView).atPosition(1)).perform(click())

        sleep(50)
        intended(hasComponent(SessionActivity::class.java.name))

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withText("Session $sessionId")).check(matches(withParent(withId(R.id.toolbar))))

    }

    @Test
    fun t99_newSession() {
        onView(withId(R.id.fab)).perform(click())

        sleep(50)
        intended(hasComponent(NewSessionActivity::class.java.name))

    }

}