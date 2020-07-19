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

class RegressionTest {

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
        private val defaultName = "Shrek"
        private val updatedName = "Green"
        private val emptyCardText = "Add Card"

        // Test
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
                assertEquals(getCardName(), defaultCardName)
                tapCard()
            }

            cardDetail {
                typeInUpdateCardNameField(updatedCardName)
                tapUpdateCardDetails()
            }

            boardDetail {
                assertEquals(getCardName(), updatedCardName)
            }

            boardDetail {
                tapCard()
            }

            cardDetail {
                typeInUpdateCardNameField(defaultCardName)
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
                tapAddCardButton()
            }

            boardDetail {
                typeInCardNameField(defaultCardName)
                tapSaveCardNameButton()
                tapCard()
            }

            cardDetail {
                tapDeleteCardButton()
            }

            alert {
                tapYesAlertButton()
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
                tapAddListButton()
                typeInListNameField(listName)
                tapSaveListNameButton()
                tapDeleteListButton(listName)
            }

            alert {
                tapYesAlertButton()
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
                tapCard()
            }

            cardDetail {
                closeKeyboard()
                tapDataPicker()
                selectDate(date)
            }

            alert {
                tapOkAlertButton()
            }

            cardDetail {
                tapUpdateCardDetails()
            }

            boardDetail {
                tapCard()
            }

            cardDetail {
                closeKeyboard()
                assertEquals(date, getDate())
            }

            cardDetail {
                tapDataPicker()
            }

            alert {
                tapOkAlertButton()
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
                    tapMyProfileButton()
                }

                profileDetail {
                    editProfileName(updatedName)
                    tapUpdateProfileButton()
                }

                boards {
                    swipeToolBar()
                }

                profile {
                    tapMyProfileButton()
                }

                profileDetail {
                    assertEquals(getUserName(), updatedName)
                    editProfileName(defaultName)
                    tapUpdateProfileButton()
                }
        }
    }
}