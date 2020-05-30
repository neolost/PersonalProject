package com.projemanag.robots

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import com.projemanag.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun boardDetail(func: BoardDetailRobot.() -> Unit) = BoardDetailRobot().apply { func() }

class BoardDetailRobot : BaseRobot() {
    val cardNameMatcher: Matcher<View> = withId(R.id.tv_card_name)
    val createListMatcher: Matcher<View> = withId(R.id.tv_add_task_list)
    val listNameFieldMatcher: Matcher<View> = withId(R.id.et_task_list_name)
    val saveListNameMatcher: Matcher<View> = withId(R.id.ib_done_list_name)
    val deleteListButtonMatcher: Matcher<View> = withId(R.id.ib_delete_list)
    val addCardMatcher: Matcher<View> = allOf(
        withId(R.id.tv_add_card),
        withEffectiveVisibility(Visibility.VISIBLE)
    )
    val cardNameFieldMatcher: Matcher<View> = allOf(
        withId(R.id.et_card_name),
        withEffectiveVisibility(Visibility.VISIBLE)
    )
    val saveCardNameMatcher: Matcher<View> = allOf(
        withId(R.id.ib_done_card_name),
        withEffectiveVisibility(Visibility.VISIBLE)
    )
    fun tapDeleteListButton(text: String) {
        onView(allOf(deleteListButtonMatcher, hasSibling(withText(text))))
            .perform(click())
    }
    fun tapOncard() = tapBy(cardNameMatcher)
    fun getCardName() = getMatcherText(cardNameMatcher)
    fun getEmptyBoardCardName() = getMatcherText(createListMatcher)
    fun tapOnAddListButton() = tapBy(createListMatcher)
    fun typeInListNameField(text: String) = typeInText(text, listNameFieldMatcher)
    fun tapSaveListNameButton() = tapBy(saveListNameMatcher)
    fun typeInCardNameField(text: String) = typeInText(text,cardNameFieldMatcher)
    fun tapOnSaveCardNameButton() = tapBy(saveCardNameMatcher)
    fun tapOnAddCardButton() = tapBy(addCardMatcher)
    fun getEmptyCardText() = getMatcherText(addCardMatcher)
}