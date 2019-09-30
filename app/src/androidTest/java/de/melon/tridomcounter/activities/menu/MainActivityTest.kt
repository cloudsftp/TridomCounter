package de.melon.tridomcounter.activities.menu

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.session.NewSessionActivity
import org.junit.*
import org.junit.runner.RunWith
import org.junit.Before



@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @JvmField
    @Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initIntents() {
        Intents.init()
    }

    @After
    fun tearDownIntents()  {
        Intents.release()
    }

    @Test
    fun t01_newSession() {
        onView(withId(R.id.fab)).perform(click())

        intended(hasComponent(NewSessionActivity::class.java.name))

    }
}