package com.example.firstxmlprojectainotepad.presentation.features.splash_fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.firstxmlprojectainotepad.BuildConfig
import com.example.firstxmlprojectainotepad.databinding.ActivitySplashBinding
import com.example.firstxmlprojectainotepad.presentation.features.language_fragment.LanguageActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}


