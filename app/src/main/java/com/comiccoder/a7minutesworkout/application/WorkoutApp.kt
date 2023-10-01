package com.comiccoder.a7minutesworkout.application

import android.app.Application
import com.comiccoder.a7minutesworkout.databases.HistoryDatabase

class WorkoutApp : Application() {
    val db: HistoryDatabase by lazy { HistoryDatabase.getInstance(this) }
}
