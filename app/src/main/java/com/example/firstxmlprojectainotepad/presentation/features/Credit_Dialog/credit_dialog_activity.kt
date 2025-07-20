package com.example.firstxmlprojectainotepad.presentation.features.Credit_Dialog

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityChatPageBinding
import com.example.firstxmlprojectainotepad.databinding.ActivityCreditDialogBinding

class credit_dialog_activity : AppCompatActivity() {
    private lateinit var binding: ActivityCreditDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityCreditDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

    }
}