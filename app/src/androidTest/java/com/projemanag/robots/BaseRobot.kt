package com.projemanag.robots

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.projemanag.utils.getText
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference
// Base test
open class BaseRobot {

    fun tapBy(matcher: Matcher<View>): ViewInteraction = onView(matcher)
        .perform(click())

    fun closeSoftKeyboard(matcher: Matcher<View>): ViewInteraction =
        onView(matcher).perform(closeSoftKeyboard())

    fun typeInText(text: String, matcher: Matcher<View>): ViewInteraction =
        onView(matcher).perform(replaceText(text), closeSoftKeyboard())

    fun getMatcherText(matcher: Matcher<View>): String {
        val restTextReference: AtomicReference<String> = AtomicReference()
        onView(matcher)
            .perform(getText(restTextReference))
        return restTextReference.toString()
    }

    fun tapRecyclerItemWithText(name: String, matcher: Matcher<View>) {
        onView(matcher)
            .perform(actionOnItem<RecyclerView.ViewHolder>
                (hasDescendant(withText(name)), scrollTo()))
        onView(withText(name)).perform(click())
    }

    fun swipeFromLeftToRight(matcher: Matcher<View>) {
        onView(matcher).perform(
            GeneralSwipeAction(
                Swipe.FAST, GeneralLocation.CENTER_LEFT,
                GeneralLocation.CENTER_RIGHT, Press.FINGER
            )
        )
    }
}
