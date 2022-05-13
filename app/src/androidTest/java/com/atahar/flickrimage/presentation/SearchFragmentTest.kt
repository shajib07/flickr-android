package com.atahar.flickrimage.presentation

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.atahar.flickrimage.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    private lateinit var scenario: FragmentScenario<SearchFragment>

    @Before
    fun setUp() {
        val fragmentArgs = bundleOf("query" to "Berlin")
        scenario = launchFragmentInContainer(fragmentArgs)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testRecycler() {
        onView(withId(R.id.photos_recycler_view)).check(matches(isDisplayed()))
    }

}

