package com.example.firstxmlprojectainotepad.presentation.features.Chat_Page

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityChatPageBinding
import com.example.firstxmlprojectainotepad.databinding.ActivityCreditDialogBinding
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.HomeActivity
import com.example.firstxmlprojectainotepad.presentation.features.speech_to_text.SpeechToTextActivity

class Chat_Page_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityChatPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityChatPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BackArrowChat.setOnClickListener(){
            val intent= Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.creadit.setOnClickListener {
            showCreditDialog()
        }
    }
private fun showCreditDialog() {
    val dialog = Dialog(this)
    val dialogBinding = ActivityCreditDialogBinding.inflate(LayoutInflater.from(this))
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(dialogBinding.root)
    dialog.setCancelable(true)

    // Dismiss dialog on "Maybe Later" click
    dialogBinding.maybeLaterText.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    dialog.window?.decorView?.setPadding(30,0,30,0)
}
}
