package com.projemanag.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.resources.MaterialAttributes
import com.projemanag.R
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert
import java.util.jar.Attributes

fun profileDetail(func: MyProfileRobot.() -> Unit) = MyProfileRobot().apply { func() }


class MyProfileRobot : BaseRobot() {


    private val profileNameMatcher: Matcher<View> = withId(R.id.et_name)
    private val updateButton: Matcher<View> = withId(R.id.btn_update)

    fun tapUpdateProfileButton() = tapBy(updateButton)
    fun editProfileName(text: String) = typeInText(text, profileNameMatcher)
    fun getUserName() = getMatcherText(profileNameMatcher)

}