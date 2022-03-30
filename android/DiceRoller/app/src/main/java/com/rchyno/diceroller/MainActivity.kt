package com.rchyno.diceroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rchyno.diceroller.databinding.ActivityMainBinding
import com.rchyno.diceroller.model.Dice

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRoll.setOnClickListener { diceRoll() }
    }

    private fun diceRoll() {
        val dice = Dice(6)
        val diceImageResource = when(dice.roll()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.ivDice.setImageResource(diceImageResource)
    }

}