package com.projemanag.robots

import android.view.View
import android.widget.DatePicker
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.PickerActions.setDate
import androidx.test.espresso.matcher.ViewMatchers.*
import com.projemanag.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers

fun cardDetail(func: CardDetailRobot.() -> Unit) = CardDetailRobot().apply { func() }

class CardDetailRobot : BaseRobot() {
    val cardNameMatcher: Matcher<View> = withId(R.id.et_name_card_details)
    val updateButton: Matcher<View> = withId(R.id.btn_update_card_details)
    val datePickerMatcher: Matcher<View> = withId(R.id.tv_select_due_date)
    val deleteBoardMatcher: Matcher<View> = withId(R.id.action_delete_card)

    fun updateCardName(name: String) = typeInText(name,cardNameMatcher)
    fun tapUpdateCardDetails() = tapBy(updateButton)
    fun tapOnDataPicker() = tapBy(datePickerMatcher)
    fun selectDate(date: String) {
        val newYear = date.substring(6, 10).toInt()
        val newMonth = date.substring(3, 5).toInt()
        val newDay = date.substring(0, 2).toInt()
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name)))
            .perform(setDate(newYear, newMonth, newDay))
    }

    fun tapDeleteCardButton() = tapBy(deleteBoardMatcher)
    fun getCurrentDate() = getMatcherText(datePickerMatcher)
}