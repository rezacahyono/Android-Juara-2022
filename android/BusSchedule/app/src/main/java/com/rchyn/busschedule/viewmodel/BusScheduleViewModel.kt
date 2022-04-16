package com.rchyn.busschedule.viewmodel

import androidx.lifecycle.ViewModel
import com.rchyn.busschedule.database.ScheduleDao
import com.rchyn.busschedule.database.entity.Schedule
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(
    private val scheduleDao: ScheduleDao
) : ViewModel() {

    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForStopName(
        name: String
    ): Flow<List<Schedule>> = scheduleDao.getByStopName(stopName = name)
}