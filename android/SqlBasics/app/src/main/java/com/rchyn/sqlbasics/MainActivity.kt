package com.rchyn.sqlbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.rchyn.sqlbasics.data.database.AppDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            val data = AppDatabase.getDatabase(applicationContext).californiaParkDao().getAll()
            Log.d("TAG", "onCreate: ${data.size}")
        }
    }
}