package com.rchyno.lemonade

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rchyno.lemonade.databinding.ActivityMainBinding
import com.rchyno.lemonade.model.LemonTree

class MainActivity : AppCompatActivity() {

    // initial key state lemon
    companion object {
        private const val LEMONADE_STATE = "LEMONADE_STATE"
        private const val LEMON_SIZE = "LEMON_SIZE"
        private const val SQUEEZE_COUNT = "SQUEEZE_COUNT"

        // SELECT represents the "pick lemon" state
        private const val SELECT = "select"

        // SQUEEZE represents the "squeeze lemon" state
        private const val SQUEEZE = "squeeze"

        // DRINK represents the "drink lemonade" state
        private const val DRINK = "drink"

        // RESTART represents the state where the lemonade has been drunk and the glass is empty
        private const val RESTART = "restart"
    }

    // Default the state to select
    private var lemonadeState = "select"

    // Default lemonSize to -1
    private var lemonSize = -1

    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView? = null

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }

        lemonImage = binding.ivLemon
        setViewElements()
        lemonImage!!.setOnClickListener { clickLemonImage() }
        lemonImage!!.setOnLongClickListener {
            showSnackBar()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    private fun clickLemonImage() {
        when (lemonadeState) {
            SELECT -> {
                lemonadeState = SQUEEZE
                lemonSize = lemonTree.pick()
                squeezeCount = 0
            }
            SQUEEZE -> {
                squeezeCount += 1
                lemonSize -= 1
                if (lemonSize == 0) {
                    lemonadeState = DRINK
                    lemonSize = -1
                }
            }
            DRINK -> {
                lemonadeState = RESTART
            }
            RESTART -> {
                lemonadeState = SELECT
            }
        }
        setViewElements()
    }

    private fun setViewElements() {
        val textAction: TextView = binding.tvSelectItem
        when (lemonadeState) {
            SELECT -> {
                textAction.text = getString(R.string.lemon_select)
                lemonImage!!.setImageResource(R.drawable.lemon_tree)
            }
            SQUEEZE -> {
                textAction.text = getString(R.string.lemon_squeeze)
                lemonImage!!.setImageResource(R.drawable.lemon_squeeze)
            }
            DRINK -> {
                textAction.text = getString(R.string.lemon_drink)
                lemonImage!!.setImageResource(R.drawable.lemon_drink)
            }
            RESTART -> {
                textAction.text = getString(R.string.lemon_empty_glass)
                lemonImage!!.setImageResource(R.drawable.lemon_restart)
            }
        }
    }

    private fun showSnackBar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(binding.root, squeezeText, Snackbar.LENGTH_SHORT).show()
        return true
    }
}