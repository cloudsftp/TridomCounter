package de.melon.tridomcounter.activities.session

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.intendedActivity
import de.melon.tridomcounter.activities.menu.MainActivity
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

        startActivity()

    }

    fun startActivity() {
        val sessionId = 1
        val intent = Intent()
        intent.putExtra("SessionId", sessionId)
        activityRule.launchActivity(intent)

    }

    @After
    fun tearDownIntents() {
        Intents.release()
    }

    @Test
    fun t99_openAllSessions() {
        onView(withId(R.id.allSessionsButton)).perform(click())

        intendedActivity(MainActivity::class.java.name)

    }

}