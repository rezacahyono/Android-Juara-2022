package com.rezac.affirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rezac.affirmation.adapter.ItemAdapter
import com.rezac.affirmation.data.DataSource
import com.rezac.affirmation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataset = DataSource().loadAffirmations()

        binding.affirmationRecyclerView.adapter = ItemAdapter(this, dataset)

        binding.affirmationRecyclerView.setHasFixedSize(true)

    }
}