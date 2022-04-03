package com.rchyn.desserclicker.ui

import android.content.ActivityNotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ShareCompat
import com.rchyn.desserclicker.R
import com.rchyn.desserclicker.data.DataSource
import com.rchyn.desserclicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var revenue = 0
    private var dessertSold = 0
    private var currentDessert = DataSource.allDeserts[0]

    companion object {
        private const val KEY_REVENUE = "key_revenue"
        private const val KEY_DESSERT_SOLD = "key_dessert_sold"
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            revenue = savedInstanceState.getInt(KEY_REVENUE, 0)
            dessertSold = savedInstanceState.getInt(KEY_DESSERT_SOLD, 0)
        }


        binding.dessertButton.setOnClickListener {
            onDessertClicked()
        }

        binding.revenueText.text = getString(R.string.amount_dessert, revenue.toString())
        binding.amountSoldText.text = dessertSold.toString()

        binding.dessertButton.setImageResource(currentDessert.imageResourceId)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_REVENUE, revenue)
        outState.putInt(KEY_DESSERT_SOLD, dessertSold)
    }

    private fun onDessertClicked() {
        revenue += currentDessert.price
        dessertSold++

        binding.revenueText.text = getString(R.string.amount_dessert, revenue.toString())
        binding.amountSoldText.text = dessertSold.toString()

        showCurrentDesserts()
    }

    private fun showCurrentDesserts() {
        var newDessert = currentDessert
        val allDessert = DataSource.allDeserts
        for (dessert in allDessert) {
            if (dessertSold >= dessert.startProductionAmount) {
                newDessert = dessert
            } else break
        }
        if (newDessert != currentDessert) {
            currentDessert = newDessert
            binding.dessertButton.setImageResource(newDessert.imageResourceId)
        }
    }

    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setText(getString(R.string.share_text, dessertSold, revenue))
            .setText("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this, getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_menu_button -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }

}