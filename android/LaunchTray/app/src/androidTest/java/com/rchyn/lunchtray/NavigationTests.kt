package com.rchyn.lunchtray

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.rchyn.lunchtray.ui.order.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationTests: BaseTest() {
    /**
     * Test navigation from [StartOrderFragment] to [EntreeMenuFragment]
     */
    @Test
    fun `navigate_to_entree_menu_from_start_order`() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        // Launch StartOrderFragment
        val startFragmentScenario =
            launchFragmentInContainer<StartFragment>(themeResId = R.style.Theme_LaunchTray)
        // Configure nav controller
        startFragmentScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click start order
        onView(withId(R.id.button_start_order)).perform(click())
        // Check destination is correct
        assertEquals(navController.currentDestination?.id, R.id.entreeMenuFragment)
    }

    /**
     * Test navigation from [EntreeMenuFragment] to [StartOrderFragment]
     */
    @Test
    fun `navigate_to_start_order_from_entree_menu`() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        // Launch EntreeMenuFragment
        val entreeMenuScenario =
            launchFragmentInContainer<EntreeMenuFragment>(themeResId = R.style.Theme_LaunchTray)
        // Configure nav controller
        entreeMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            // Destination defaults to the home fragment, we have to explicitly set the current
            // destination
            navController.setCurrentDestination(destId = R.id.entreeMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click the cancel button
        onView(withId(R.id.cancel_button)).perform(click())
        // Check that the destination is correct
        assertEquals(navController.currentDestination?.id, R.id.startFragment)
    }

    /**
     * Test navigation from [EntreeMenuFragment] to [SideMenuFragment]
     */
    @Test
    fun `navigate_to_side_menu_from_entree_menu`() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        // Launch the EntreeMenuFragment
        val entreeMenuScenario =
            launchFragmentInContainer<EntreeMenuFragment>(themeResId = R.style.Theme_LaunchTray)
        // Configure nav controller
        entreeMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            // Destination defaults to the home fragment, we have to explicitly set the current
            // destination
            navController.setCurrentDestination(destId = R.id.entreeMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click the next button
        onView(withId(R.id.button_next)).perform(click())
        // Check that the destination is correct
        assertEquals(navController.currentDestination?.id, R.id.sideMenuFragment)
    }

    /**
     * Test navigation from [SideMenuFragment] to [StartOrderFragment]
     */
    @Test
    fun `navigate_to_start_order_from_side_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val sideMenuScenario =
            launchFragmentInContainer<SideMenuFragment>(themeResId = R.style.Theme_LaunchTray)
        sideMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.sideMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startFragment)
    }

    /**
     * Test navigation from [SideMenuFragment] to [AccompanimentMenuFragment]
     */
    @Test
    fun `navigate_to_accompaniment_menu_from_side_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val sideMenuScenario =
            launchFragmentInContainer<SideMenuFragment>(themeResId = R.style.Theme_LaunchTray)
        sideMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.sideMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_next)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.accompanimentMenuFragment)
    }

    /**
     * Test navigation from [AccompanimentMenuFragment] to [StartOrderFragment]
     */
    @Test
    fun `navigate_to_start_order_from_accompaniment_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val accompanimentMenuScenario =
            launchFragmentInContainer<AccompanimentMenuFragment>(
                themeResId = R.style.Theme_LaunchTray)
        accompanimentMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.accompanimentMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startFragment)
    }

    /**
     * Test navigation from [AccompanimentMenuFragment] to [CheckoutFragment]
     */
    @Test
    fun `navigate_to_checkout_from_accompaniment_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val accompanimentMenuScenario =
            launchFragmentInContainer<AccompanimentMenuFragment>(
                themeResId = R.style.Theme_LaunchTray)
        accompanimentMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.accompanimentMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_next)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.checkoutFragment)
    }

    /**
     * Test navigation from [CheckoutFragment] to [StartOrderFragment]
     */
    @Test
    fun `navigate_to_start_order_from_checkout`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val checkoutScenario =
            launchFragmentInContainer<CheckoutFragment>(themeResId = R.style.Theme_LaunchTray)
        checkoutScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.checkoutFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startFragment)
    }

    /**
     * Test Navigation from [CheckoutFragment] to [StartOrderFragment]
     */
    @Test
    fun `navigate_to_start_order_from_checkout_via_submit`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val checkoutScenario =
            launchFragmentInContainer<CheckoutFragment>(themeResId = R.style.Theme_LaunchTray)
        checkoutScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.checkoutFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.submit_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startFragment)
    }
}