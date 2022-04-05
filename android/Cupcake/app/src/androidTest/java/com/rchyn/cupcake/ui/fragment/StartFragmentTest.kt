package com.rchyn.cupcake.ui.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.rchyn.cupcake.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class StartFragmentTest {

    private lateinit var navController: TestNavHostController
    private lateinit var startFragmentTestScenario: FragmentScenario<StartFragment>

    @Before
    fun setUpNavController(){
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        startFragmentTestScenario = launchFragmentInContainer(themeResId = R.style.Theme_Cupcake)
        startFragmentTestScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }


    @Test
    fun navigation_button_order_one_to_flavor_fragment(){
        onView(withId(R.id.button_order_one_cake)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.flavorFragment)
    }

    @Test
    fun navigation_button_order_six_to_flavor_fragment(){
        onView(withId(R.id.button_order_six_cupcake)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.flavorFragment)
    }

    @Test
    fun navigation_button_order_twelve_to_flavor_fragment(){
        onView(withId(R.id.button_order_twelve_cake)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.flavorFragment)
    }

}