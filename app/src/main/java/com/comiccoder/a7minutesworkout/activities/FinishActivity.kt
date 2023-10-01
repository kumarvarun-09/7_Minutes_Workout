package com.comiccoder.a7minutesworkout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.comiccoder.a7minutesworkout.R
import com.comiccoder.a7minutesworkout.application.WorkoutApp
import com.comiccoder.a7minutesworkout.dao.HistoryDao
import com.comiccoder.a7minutesworkout.databinding.ActivityFinishBinding
import com.comiccoder.a7minutesworkout.models.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FinishActivity : AppCompatActivity() {
    private var binding: ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val historyDao = (application as WorkoutApp).db.historyDao()
        lifecycleScope.launch {
            addDateToDatabase(historyDao)
        }
    }

    private suspend fun addDateToDatabase(historyDao: HistoryDao) {
        withContext(Dispatchers.IO) {
            val cal = Calendar.getInstance()
            val dateTime = cal.timeInMillis
            val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
            val date = sdf.format(dateTime)
            historyDao.insert(HistoryEntity(date))
        }
    }
}
