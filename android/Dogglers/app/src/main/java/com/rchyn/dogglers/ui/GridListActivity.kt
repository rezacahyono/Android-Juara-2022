package com.rchyn.dogglers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rchyn.dogglers.R
import com.rchyn.dogglers.adapter.DogCardAdapter
import com.rchyn.dogglers.data.DataSource
import com.rchyn.dogglers.databinding.ActivityGridListBinding
import com.rchyn.dogglers.utils.Layout

class GridListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DogCardAdapter(this, Layout.GRID)
        adapter.setAdapter(DataSource.dogs)

        binding.recyclerGrid.setHasFixedSize(true)

        binding.recyclerGrid.adapter = adapter
    }
}