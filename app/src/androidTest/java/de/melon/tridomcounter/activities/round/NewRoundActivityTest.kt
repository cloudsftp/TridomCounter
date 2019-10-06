package de.melon.tridomcounter.activities.round

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import de.melon.tridomcounter.R
import de.melon.tridomcounter.activities.current
import de.melon.tridomcounter.activities.util.withChoosePlayerRecyclerView
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

    val choosePlayerRecyclerViewChild = withChoosePlayerRecyclerView(R.id.choosePlayerRecyclerView)

    @Test
    fun t00_displayPlayersQuantitative() {
        onView(withId(R.id.choosePlayerRecyclerView)).check(matches(hasChildCount(players.size)))

    }

    @Test
    fun t01_displayPlayersQualitative() {
        for (i in players.indices)
            onView(choosePlayerRecyclerViewChild.atPosition(i)).check(matches(withText(players[i])))

    }

    @Test
    fun t99_startRound() {

    }

    @After fun tearDownIntents() = Intents.release()

}