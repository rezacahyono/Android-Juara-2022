package com.rchyn.dogglers.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rchyn.dogglers.R
import com.rchyn.dogglers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonHorizontal.setOnClickListener { launchHorizontal() }

        binding.buttonVertical.setOnClickListener { launchVertical() }

        binding.buttonGrid.setOnClickListener { launchGrid() }
    }

    private fun launchHorizontal() {
        val intent = Intent(this, HorizontalListActivity::class.java)
        startActivity(intent)
    }

    private fun launchVertical() {
        val intent = Intent(this, VerticalListActivity::class.java)
        startActivity(intent)
    }

    private fun launchGrid() {
        val intent = Intent(this, GridListActivity::class.java)
        startActivity(intent)
    }
}