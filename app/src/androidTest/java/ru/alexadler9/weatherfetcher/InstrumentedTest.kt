package ru.alexadler9.weatherfetcher

import android.text.InputType
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.MainActivity

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testInitialStateOfCitySearchViews() {
        onView(withId(R.id.etCitySearch)).check(
            matches(
                allOf(
                    withEffectiveVisibility(Visibility.VISIBLE),
                    isFocusable(),
                    isClickable(),
                    withInputType(InputType.TYPE_CLASS_TEXT),
                    withText("")
                )
            )
        )

        onView(withId(R.id.ivCitySearch)).check(
            matches(
                allOf(
                    withEffectiveVisibility(Visibility.VISIBLE),
                    isClickable()
                )
            )
        )
    }

    @Test
    fun testSearchFieldFixesText() {
        onView(withId(R.id.etCitySearch)).perform(replaceText("Москва"))
        onView(withId(R.id.fragmentContainerView)).perform(click())
        onView(withId(R.id.etCitySearch)).check(matches(withText("Москва")))
    }

    @Test
    fun testSearchButtonAccessibility() {
        onView(withId(R.id.etCitySearch)).perform(replaceText(""))
        onView(withId(R.id.ivCitySearch)).check(matches(isNotEnabled()))
        onView(withId(R.id.etCitySearch)).perform(replaceText("Москва"))
        onView(withId(R.id.ivCitySearch)).check(matches(isEnabled()))
    }

    @Test
    fun notfound() {
        onView(withId(R.id.etCitySearch)).perform(replaceText("Undefined city"))
        onView(withId(R.id.ivCitySearch)).perform(click())
        //onView(withId(R.id.etCitySearch)).check(matches(withText("Москва")))
        onView(withId(R.id.layoutNotFound)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("ru.alexadler9.weatherfetcher", appContext.packageName)
//    }
}