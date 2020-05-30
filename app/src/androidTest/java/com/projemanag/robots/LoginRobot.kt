package com.projemanag.robots

import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.projemanag.R
import getJsonValue
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import java.util.EnumSet.allOf

fun login(func: LoginRobot.() -> Unit) = LoginRobot().apply { func() }

class LoginRobot : BaseRobot() {
    private val signInButton: Matcher<View> = withId(R.id.btn_sign_in_intro)
    private val signUpButton: Matcher<View> = withId(R.id.btn_sign_up_intro)
    private val emailMatcher: Matcher<View> = withId(R.id.et_email)
    private val passwordMatcher: Matcher<View> = withId(R.id.et_password)
    private val signInButtonMatcher: Matcher<View> = withId(R.id.btn_sign_in)
    private val signInEmail = getJsonValue("email")
    private val signInPassword = getJsonValue("password")
    private val hamburgerMenuButtonMatcher: Matcher<View> = withId(R.drawable.ic_action_navigation_menu)


    fun Hamburger() {
        tapBy(hamburgerMenuButtonMatcher)
    }


    fun tapSignIn() = tapBy(signInButton)
    fun tapSignUp() = tapBy(signUpButton)
    fun typeEmail() = typeInText(signInEmail, emailMatcher)
    fun typePassword() = typeInText(signInPassword, passwordMatcher)
    fun tapSignInButton() = tapBy(signInButtonMatcher)
}