package com.example.firstxmlprojectainotepad.presentation.features.language_fragment

import android.app.DownloadManager.Query
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstxmlprojectainotepad.databinding.ActivityLanguageBinding
import com.example.firstxmlprojectainotepad.presentation.features.Hello_screen.Hello_Activity
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.HomeActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
            val intent = Intent(this, Hello_Activity::class.java)
            startActivity(intent)
        }




    }
}




