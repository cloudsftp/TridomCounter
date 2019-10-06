package de.melon.tridomcounter.activities.round

import android.support.test.espresso.intent.Intents
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class NewRoundActivityTest {

    @Before fun initIntents() = Intents.init()

    @JvmField
    @Rule
    val activityRule = ActivityTestRule<NewRoundActivity>(NewRoundActivity::class.java)

    @Test
    fun t99_startRound() {

    }

    @After fun tearDownIntents() = Intents.release()

}