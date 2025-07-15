package com.example.firstxmlprojectainotepad.presentation.features.feedback_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityFeedbackBinding
import com.example.firstxmlprojectainotepad.presentation.features.setting_activity.SettingsActivity

class Feedback_activity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.icBackArrow.setOnClickListener{
            val intent=Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }


        }
    }
