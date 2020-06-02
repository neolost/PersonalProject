package com.projemanag.robots

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.projemanag.R
import org.hamcrest.Matcher

fun boards(func: HomeRobot.() -> Unit) = HomeRobot().apply { func() }

class HomeRobot : BaseRobot() {

    private val boardsListRecyclerMatcher: Matcher<View> = withId(R.id.rv_boards_list)
    private val recyclerViewMatcher: Matcher<View> = withId(R.id.rv_boards_list)

    fun selectBoard(name: String) = tapRecyclerItemWithText(name, boardsListRecyclerMatcher)
    fun swipeToolBar() = swipeFromLeftToRight(recyclerViewMatcher)
}