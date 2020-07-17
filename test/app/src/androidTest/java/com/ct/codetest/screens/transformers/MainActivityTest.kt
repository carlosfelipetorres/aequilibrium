package com.ct.codetest.screens.transformers

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.ct.codetest.R
import com.ct.codetest.base.BaseUITest
import com.ct.codetest.di.generateTestAppComponent
import com.ct.codetest.helpers.recyclerItemAtPosition
import com.ct.codetest.screens.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
class MainActivityTest : BaseUITest(){

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    val mNameTestOne = "Lee"
    val mCountryOne = "United Kingdom"
    val mNameTestTwo = "Anonymous"
    val mCountryTwo = "Germany"

    @Before
    fun start(){
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }

    @Test
    fun test_recyclerview_elements_for_expected_response() {
        mActivityTestRule.launchActivity(null)

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)

        //Wait for MockWebServer to get back with response
        SystemClock.sleep(1000)

        //Check if item at 0th position is having 0th element in json
        onView(withId(R.id.transformersListRecyclerView))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mNameTestOne))
                    )
                ))

        onView(withId(R.id.transformersListRecyclerView))
            .check(
                matches(
                    recyclerItemAtPosition(
                        0,
                        ViewMatchers.hasDescendant(withText(mCountryOne))
                    )
                ))

        //Scroll to last index in json
        onView(withId(R.id.transformersListRecyclerView)).perform(
            RecyclerViewActions.scrollToPosition<TransformersAdapter.TransformersFragViewHolder>(9))

        //Check if item at 9th position is having 9th element in json
        onView(withId(R.id.transformersListRecyclerView))
            .check(matches(recyclerItemAtPosition(9, ViewMatchers.hasDescendant(withText(mNameTestTwo)))))

        onView(withId(R.id.transformersListRecyclerView))
            .check(matches(recyclerItemAtPosition(9, ViewMatchers.hasDescendant(withText(mCountryTwo)))))

    }
}