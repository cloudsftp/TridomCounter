package de.melon.tridomcounter.activities

import android.os.SystemClock
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.*
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.widget.NumberPicker
import org.hamcrest.Matcher

fun setValue(value: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "set the value of a " + NumberPicker::class.java.name
        }

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isAssignableFrom(NumberPicker::class.java)
        }

        // the only way to fire onChange event is to call this private method
        override fun perform(uiController: UiController?, view: View?) {
            val numberPicker = view as NumberPicker

            numberPicker.value = value

        }
    }
}

fun setNumberPickerValue(viewInteraction: ViewInteraction, value: Int) {
    viewInteraction.perform(setValue(value))

    viewInteraction.perform(GeneralSwipeAction(Swipe.SLOW, GeneralLocation.TOP_CENTER, GeneralLocation.BOTTOM_CENTER, Press.FINGER))
    SystemClock.sleep(50)
    viewInteraction.perform(GeneralSwipeAction(Swipe.SLOW, GeneralLocation.BOTTOM_CENTER, GeneralLocation.TOP_CENTER, Press.FINGER))
    SystemClock.sleep(50)

}