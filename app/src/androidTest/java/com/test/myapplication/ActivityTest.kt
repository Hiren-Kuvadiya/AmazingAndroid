package com.test.myapplication

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import com.test.myapplication.view.activities.MainActivity
import org.junit.Before

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class ActivityTest {

    /*  private lateinit var scenario: ActivityScenario<MainActivity>

      @Before
      fun setup() {

          scenario = launchActivity()
          scenario.moveToState(Lifecycle.State.STARTED)

      }


      @Test
      fun testAddViewSpend() {

          onView(withId(R.id.news_rv)).perform(
              RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                  0,
                  click()
              )
          )

      }*/

    @get:Rule
    public val mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSample() {
        if (getRVcount() > 0) {
            onView(withId(R.id.news_rv)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        }
    }

    private fun getRVcount(): Int {
        val recyclerView = mActivityRule.getActivity().findViewById(R.id.news_rv) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }

}