package com.example.firstxmlprojectainotepad.presentation.features.Rate_us_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityRateUsBinding
import com.example.firstxmlprojectainotepad.presentation.features.setting_activity.SettingsActivity

class Rate_Us_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRateUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRateUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.icBackArrowRate.setOnClickListener{
            val intent=Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}