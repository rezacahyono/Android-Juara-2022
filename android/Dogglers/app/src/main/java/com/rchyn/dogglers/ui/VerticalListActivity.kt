package com.rchyn.dogglers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rchyn.dogglers.adapter.DogCardAdapter
import com.rchyn.dogglers.data.DataSource
import com.rchyn.dogglers.databinding.ActivityVerticalListBinding
import com.rchyn.dogglers.utils.Layout

class VerticalListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerticalListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerticalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DogCardAdapter(this, Layout.VERTICAL)
        adapter.setAdapter(DataSource.dogs)

        binding.recyclerVertical.setHasFixedSize(true)

        binding.recyclerVertical.adapter = adapter

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}