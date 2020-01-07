package id.afdaldev.footballmatchscheduleapp.league

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import id.afdaldev.footballmatchscheduleapp.MainActivity
import id.afdaldev.footballmatchscheduleapp.R.id.*
import id.afdaldev.footballmatchscheduleapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LeagueFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun searchBehaviourTest() {
        onView(withId(searchEvent))
            .check(matches(isDisplayed())).perform(click())
        onView(withId(search_src_text)).perform(typeText("c"))
        onView(withId(search_src_text)).perform(typeText("i"))
        onView(withId(search_src_text)).perform(typeText("t"))
        onView(withId(search_src_text)).perform(typeText("y"))
        onView(withId(recyclerView))
            .check(matches(isDisplayed()))
    }
}