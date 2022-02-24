package com.dondo.demo.test.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.dondo.demo.R
import com.dondo.demo.test.TestBaseActivity
import com.dondo.demo.test.TestBaseActivityTest
import com.dondo.ui.formfields.EditTextField
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditTextFieldTests: TestBaseActivityTest() {

    private val editTextField =  onView(withHint("Enter some text"))

    @SmallTest
    @Test
    fun shouldDisplayHint() {
        launchActivity()

        onView(withHint("Enter some text")).check(matches(isDisplayed()))
    }

    @SmallTest
    @Test
    fun shouldDisplayText() {
        launchActivity()

        editTextField.perform(typeText("This is a test!"))

        onView(withText("This is a test!")).check(matches(isDisplayed()))
    }

    @SmallTest
    @Test
    fun shouldDisplayIsRequiredError() {
        launchActivity()

        ruleActivity.scenario.onActivity { activity ->
            val field = activity.findViewById<EditTextField>(R.id.edfBase)
            field.isRequired = true
        }

        editTextField.perform(typeText("This is a test!"))
        editTextField.perform(clearText())

        onView(withText("This field is required")).check(matches(isDisplayed()))
    }

    @SmallTest
    @Test
    fun shouldDisplayMinLengthError() {
        launchActivity()

        editTextField.perform(typeText("Test!"))

        ruleActivity.scenario.onActivity { activity ->
            val field = activity.findViewById<EditTextField>(R.id.edfBase)
            val isValid = field.isValid()

            assertFalse(isValid)
        }

        onView(withText("Must be over 8 characters")).check(matches(isDisplayed()))
    }

    @SmallTest
    @Test
    fun shouldDisplayErrorMessage() {
        launchActivity()

        ruleActivity.scenario.onActivity { activity ->
            val field = activity.findViewById<EditTextField>(R.id.edfBase)
            field.showError("This is an error message")
        }

        onView(withText("This is an error message")).check(matches(isDisplayed()))
    }

    @SmallTest
    @Test
    fun shouldDisplayRegexErrorMessage() {
        launchActivity()

        ruleActivity.scenario.onActivity { activity ->
            val field = activity.findViewById<EditTextField>(R.id.edfBase)
            field.regex = "[0-9]+"

            val isValid = field.isValid()

            assertFalse(isValid)
        }

        editTextField.perform(typeText("1234567ff"))

        ruleActivity.scenario.onActivity { activity ->
            val field = activity.findViewById<EditTextField>(R.id.edfBase)
            val isValid = field.isValid()

            assertFalse(isValid)
        }

        onView(withText("Some of these characters are not permitted")).check(matches(isDisplayed()))
    }

    private fun launchActivity() {
        TestBaseActivity.layout = R.layout.activity_edittext_field
        restartActivity()
    }
}
