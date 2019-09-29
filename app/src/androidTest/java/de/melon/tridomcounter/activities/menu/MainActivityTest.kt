package de.melon.tridomcounter.activities.menu

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.session.NewSessionActivity
import org.junit.*
import org.junit.runner.RunWith
import android.view.WindowManager
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

    @Before
    fun unlockScreen() {
        val activity = activityRule.getActivity()
        val wakeUpDevice = Runnable {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                                            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                                            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)}
        activity.runOnUiThread(wakeUpDevice)
    }

    @After
    fun tearDonwIntents()  {
        Intents.release()
    }

    @Test
    fun t00_dummy() {
        onView(withId(R.id.testTextView)).check(matches(withText("test")))

    }

    @Test
    fun t01_newSessoin() {
        onView(withId(R.id.fab)).perform(click())

        intended(hasComponent(NewSessionActivity::class.java.name))

    }
}