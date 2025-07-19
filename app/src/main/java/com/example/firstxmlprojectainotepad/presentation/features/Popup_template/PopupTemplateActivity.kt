package com.example.firstxmlprojectainotepad.presentation.features.Popup_template

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityPopupTemplateBinding

class PopupTemplateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPopupTemplateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       //     dialog.setContentView(R.layout.activity_popup_template)
         //   dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
           // dialog.setCancelable(true)


    }
}