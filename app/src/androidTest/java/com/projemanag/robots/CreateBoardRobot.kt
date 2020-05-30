package com.projemanag.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import com.projemanag.R
import org.hamcrest.Matcher

fun createBoard(func: CreateBoardRobot.() -> Unit) = CreateBoardRobot().apply { func() }


class CreateBoardRobot : BaseRobot() {


    private val bardNameFieldMatcher: Matcher<View> = ViewMatchers.withId(R.id.et_board_name)
    private val createBoardMatcher: Matcher<View> = ViewMatchers.withId(R.id.btn_create)

    fun typeBoardName(text: String) = typeInText(text, bardNameFieldMatcher)
    fun createBoard() = tapBy(createBoardMatcher)
}