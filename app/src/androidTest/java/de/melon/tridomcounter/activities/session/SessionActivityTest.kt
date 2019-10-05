package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.intendedSafe
import de.melon.tridomcounter.activities.menu.MainActivityTest
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class SessionActivityTest {

    @JvmField
    @Rule
    var activityRule = ActivityTestRule(SessionActivity::class.java, false, false)

    @Before
    fun startActivity() {
        val sessionId = 1
        val intent = Intent()
        intent.putExtra("SessionId", sessionId)
        activityRule.launchActivity(intent)

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
    fun t99_openAllSessions() {
        onView(withId(R.id.allSessionsButton)).perform(click())

        intendedSafe(hasComponent(MainActivityTest::class.java.name))

    }

}