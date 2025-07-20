package com.example.firstxmlprojectainotepad.presentation.features.Hello_screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.firstxmlprojectainotepad.databinding.ActivityHelloBinding
import com.example.firstxmlprojectainotepad.networking.createHttpClient
import com.example.firstxmlprojectainotepad.networking.sendMessageToChatGPT
import com.example.firstxmlprojectainotepad.presentation.features.bottom_exit_dialog.BottomExitDialog
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.HomeActivity
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.launch

class Hello_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHelloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.hiButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.skipbtn.setOnClickListener{
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}