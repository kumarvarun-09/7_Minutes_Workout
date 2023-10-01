package com.comiccoder.a7minutesworkout.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.comiccoder.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flStart?.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.flBmi?.setOnClickListener {
            startActivity(Intent(this@MainActivity, BMIActivity::class.java))
        }

        binding?.flHistory?.setOnClickListener {
            startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // This is just a clean way to unassigned the binding to avoid memory leaks
        binding = null
    }
}