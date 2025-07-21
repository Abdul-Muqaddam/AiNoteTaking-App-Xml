package com.example.firstxmlprojectainotepad.presentation.features.Template_Card

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityTemplateCardBinding

class Template_Card_Activity : AppCompatActivity() {
    private lateinit var binding :ActivityTemplateCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemplateCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

    }

    }
