package com.projemanag.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.projemanag.R
import org.hamcrest.Matcher

fun alert(func: AlertRobot.() -> Unit) = AlertRobot().apply { func() }

class AlertRobot : BaseRobot() {
    val yesButtonMatcher: Matcher<View> = withText("Yes")
    val noButtonMatcher: Matcher<View> = withText("No")
    val okButtonMatcher: Matcher<View> = withText("OK")

    fun tapOnYesButtonOnAlert() = tapBy(yesButtonMatcher)
    fun tapOnNoButtonOnAlert() = tapBy(noButtonMatcher)
    fun tapOnOkButtonMatcher() = tapBy(okButtonMatcher)
}