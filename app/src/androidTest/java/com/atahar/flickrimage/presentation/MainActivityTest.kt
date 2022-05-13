package com.atahar.flickrimage.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.atahar.flickrimage.R
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_action_search_visibility() {
        Thread.sleep(1000)
        onView(withId(R.id.action_search)).check(matches(isDisplayed()))
    }

    @Test
    fun test_text_visibility() {
        Thread.sleep(1000)
        onView(withId(R.id.text)).check(matches(not(withText(""))))
    }

}