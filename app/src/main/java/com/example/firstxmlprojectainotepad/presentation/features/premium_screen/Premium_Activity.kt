package com.example.firstxmlprojectainotepad.presentation.features.premium_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityPremiumBinding
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.HomeActivity

class Premium_Activity : AppCompatActivity() {
    private lateinit var binding:ActivityPremiumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityPremiumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
binding.cross.setOnClickListener{
    val intent=Intent(this,HomeActivity::class.java)
    startActivity(intent)
}
    }
}