package com.rchyn.busschedule

import android.app.Application
import com.rchyn.busschedule.database.AppDatabase

class BusScheduleApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}