package com.example.firstxmlprojectainotepad.presentation.features.setting_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivitySettingsBinding
import com.example.firstxmlprojectainotepad.presentation.features.Rate_us_screen.Rate_Us_Activity
import com.example.firstxmlprojectainotepad.presentation.features.feedback_screen.Feedback_activity

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.feedbackArrow.setOnClickListener{
            val intent=Intent(this,Feedback_activity::class.java)
            startActivity(intent)
        }
        binding.RateUsArrow.setOnClickListener{
            val intent=Intent(this,Rate_Us_Activity::class.java)
            startActivity(intent)
        }



    }
}