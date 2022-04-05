package com.rchyn.cupcake.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class OrderViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: OrderViewModel

    @Before
    fun before() {
        viewModel = OrderViewModel()
    }

    @Test
    fun quantity_twelve_cupcakes() {
        viewModel.quantity.observeForever {}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.quantity.value)
    }

    @Test
    fun price_twelve_cupcakes() {
        viewModel.price.observeForever {}
        viewModel.setQuantity(12)
        assertEquals("$27.00", viewModel.price.value)
    }
}