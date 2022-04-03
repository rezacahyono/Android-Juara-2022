package com.rchyn.words.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rchyn.words.adapter.WordsAdapter
import com.rchyn.words.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val LETTER_EXTRAS = "letter_extras"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val letterId = intent?.extras?.getString(LETTER_EXTRAS).toString()
        binding.recyclerWords.layoutManager = LinearLayoutManager(this)
        binding.recyclerWords.adapter = WordsAdapter(letterId, this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}