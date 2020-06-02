package com.projemanag.tests

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import com.projemanag.activities.IntroActivity
import com.projemanag.robots.login
import com.projemanag.robots.boards
import com.projemanag.robots.boardDetail
import com.projemanag.robots.cardDetail
import com.projemanag.robots.alert
import com.projemanag.robots.profile
import com.projemanag.robots.profileDetail
import com.projemanag.utils.Constants.countingIdlingResource
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before

class BaseTest {

    @LargeTest
    @RunWith(AndroidJUnit4::class)
    class RestaurantMenuTest {

        @Before
        fun setup() { IdlingRegistry.getInstance().register(countingIdlingResource) }

        @After
        fun tearDown() { IdlingRegistry.getInstance().unregister(countingIdlingResource) }

        @Rule
        @JvmField
        var activityRule: ActivityTestRule<IntroActivity> =
            ActivityTestRule(IntroActivity::class.java)
        private val disneyBoard = "Disney"
        private val gomerBoard = "Gomer"
        private val defaultBoard = "Board"
        private val updatedCardName = "Movie"
        private val defaultCardName = "Card"
        private val emptyBoardListName = "Add List"
        private val listName = "List"
        private val date = "12/05/1993"
        private val boardName = "Board"
        private val defaultName = "Shrek"
        private val updatedName = "Green"
        private val emptyCardText = "Add Card"

        @Test
        fun updateCardNameTest() {
            login {
                tapSignIn()
                typeEmail()
                typePassword()
                tapSignInButton()
            }

            boards {
                selectBoard(disneyBoard)
            }

            boardDetail {
                tapOncard()
            }

            cardDetail {
                updateCardName(updatedCardName)
                tapUpdateCardDetails()
            }

            boardDetail {
                assertEquals(getCardName(), updatedCardName)
            }

            boardDetail {
                tapOncard()
            }

            cardDetail {
                updateCardName(defaultCardName)
                tapUpdateCardDetails()
            }
        }

        @Test
        fun createAndDeleteCardTest() {
            login {
                tapSignIn()
                typeEmail()
                typePassword()
                tapSignInButton()
            }

            boards {
                selectBoard(gomerBoard)
            }

            boardDetail {
                tapOnAddCardButton()
            }

            boardDetail {
                typeInCardNameField(defaultCardName)
                tapOnSaveCardNameButton()
                tapOncard()
            }

            cardDetail {
                tapDeleteCardButton()
            }

            alert {
                tapOnYesButtonOnAlert()
            }

            boardDetail {
                assertEquals(getEmptyCardText(), emptyCardText)
            }
        }

        @Test
        fun createAndDeleteListTest() {
            login {
                tapSignIn()
                typeEmail()
                typePassword()
                tapSignInButton()
            }

            boards {
                selectBoard(defaultBoard)
            }

            boardDetail {
                tapOnAddListButton()
                typeInListNameField(listName)
                tapSaveListNameButton()
                tapDeleteListButton(listName)
            }

            alert {
                tapOnYesButtonOnAlert()
            }

            boardDetail {
                assertEquals(getEmptyBoardCardName(), emptyBoardListName)
            }
        }

        @Test
        fun setRequestedDueDateTest() {
            login {
                tapSignIn()
                typeEmail()
                typePassword()
                tapSignInButton()
            }

            boards {
                selectBoard(disneyBoard)
            }

            boardDetail {
                tapOncard()
            }

            cardDetail {
                closeKeyboard()
                tapOnDataPicker()
                selectDate(date)
            }

            alert {
                tapOnOkButtonMatcher()
            }

            cardDetail {
                tapUpdateCardDetails()
            }

            boardDetail {
                tapOncard()
            }

            cardDetail {
                closeKeyboard()
                assertEquals(date, getCurrentDate())
            }

            cardDetail {
                tapOnDataPicker()
            }

            alert {
                tapOnOkButtonMatcher()
            }
        }

        @Test
        fun updateUserNameTest() {

                login {
                    tapSignIn()
                    typeEmail()
                    typePassword()
                    tapSignInButton()
                }

                boards {
                    swipeToolBar()
                }

                profile {
                    tapOnMyProfile()
                }

                profileDetail {
                    editProfileName(updatedName)
                    tapUpdateProfileButton()
                }

                boards {
                    swipeToolBar()
                }

                profile {
                    tapOnMyProfile()
                }

                profileDetail {
                    assertEquals(getUserName(), updatedName)
                    editProfileName(defaultName)
                    tapUpdateProfileButton()
                }
        }
    }
}