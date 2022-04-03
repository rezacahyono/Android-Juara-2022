package com.rchyn.dogglers

import android.content.Context
import com.rchyn.dogglers.adapter.DogCardAdapter
import com.rchyn.dogglers.data.DataSource
import com.rchyn.dogglers.utils.Layout
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class DogCardAdapterTests {

    private val context = mock(Context::class.java)
    private val data = DataSource.dogs


    @Test
    fun test_adapter_size(){
        val adapter = DogCardAdapter(context, Layout.VERTICAL)
        adapter.setAdapter(data)
        assertEquals("Dog card adapter is not the correct size", data.size, adapter.itemCount)
    }
}