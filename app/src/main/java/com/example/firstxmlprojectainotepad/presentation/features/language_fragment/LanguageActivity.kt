package com.example.firstxmlprojectainotepad.presentation.features.language_fragment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstxmlprojectainotepad.databinding.ActivityLanguageBinding
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.HomeActivity

class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.languageRecycler.layoutManager = LinearLayoutManager(this)
        binding.languageRecycler.adapter =
            LanguageAdaptor(language = LanguageModel.values().toList())
        binding.isLanguageSelect.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
