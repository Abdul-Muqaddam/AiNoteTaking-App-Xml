package com.example.firstxmlprojectainotepad.presentation.features.bottom_exit_dialog

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityBottomExitDialogBinding

class BottomExitDialog : AppCompatActivity() {
    private lateinit var binding:ActivityBottomExitDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityBottomExitDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}