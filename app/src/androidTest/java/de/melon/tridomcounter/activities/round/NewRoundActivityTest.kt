package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.support.test.espresso.intent.Intents
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.data.GameData
import de.melon.tridomcounter.logic.Session
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class NewRoundActivityTest {

    @Before
    fun setUp() {
        Intents.init()

        createSession()
        startActivity()

    }

    val players = arrayOf("Fabian", "Tim", "Paul")

    fun createSession() {
        val session = Session(players)
        current.sessionId = GameData.addSession(session)

    }

    @JvmField
    @Rule
    val activityRule = ActivityTestRule<NewRoundActivity>(NewRoundActivity::class.java, false, false)

    fun startActivity() = activityRule.launchActivity(Intent())

    @Test
    fun t00_displayPlayerNames() {
        current.sessionId = GameData.addSession(Session(players))



    }

    @Test
    fun t99_startRound() {

    }

    @After fun tearDownIntents() = Intents.release()

}