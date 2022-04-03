package com.rchyn.dogglers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rchyn.dogglers.adapter.DogCardAdapter
import com.rchyn.dogglers.data.DataSource
import com.rchyn.dogglers.databinding.ActivityHorizontalListBinding
import com.rchyn.dogglers.utils.Layout

class HorizontalListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHorizontalListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorizontalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DogCardAdapter(this, Layout.HORIZONTAL)
        adapter.setAdapter(DataSource.dogs)

        binding.recyclerHorizontal.setHasFixedSize(true)
        binding.recyclerHorizontal.adapter = adapter

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


}