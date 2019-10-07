package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class RoundActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        createSession()
        startActivity()

    }

    val players = arrayOf("Fabian", "Finn", "Kenji")

    fun createSession() {
        current.sessionId = GameData.newSession(players)
        val session = GameData.sessions[current.sessionId]
        current.roundId = session.newRound()

    }

    @JvmField
    @Rule
    val activityRule = ActivityTestRule<RoundActivity>(RoundActivity::class.java)

    fun startActivity() = activityRule.launchActivity(Intent())



    @After fun tearDownIntents() = Intents.release()

}