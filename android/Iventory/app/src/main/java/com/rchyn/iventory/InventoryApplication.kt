package com.rchyn.iventory

import android.app.Application
import com.rchyn.iventory.data.ItemRoomDatabase

class InventoryApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}