package com.rchyn.words.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rchyn.words.R
import com.rchyn.words.adapter.LettersAdapter
import com.rchyn.words.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var isLinearLayoutManager = true

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chooseLayout()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            binding.recyclerLetter.layoutManager = LinearLayoutManager(this)
        } else {
            binding.recyclerLetter.layoutManager = GridLayoutManager(this, 4)
        }
        binding.recyclerLetter.adapter = LettersAdapter()
    }


    private fun setIcon(menu: MenuItem?) {
        if (menu == null) return
        menu.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_view_linear)
            else ContextCompat.getDrawable(this, R.drawable.ic_view_grid)
    }


}