package com.example.firstxmlprojectainotepad.presentation.features.Hello_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.firstxmlprojectainotepad.databinding.ActivityHelloBinding
import com.example.firstxmlprojectainotepad.presentation.features.bottom_exit_dialog.BottomExitDialog
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.HomeActivity

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
            val intent = Intent(this,BottomExitDialog::class.java)
            startActivity(intent)
            finish()
        }

    }
}


//private val client: HttpClient by lazy {
//    createHttpClient(Android.create())
//}
//
//lifecycleScope.launch {
//    val response = sendMessageToChatGPT(client, "Write a blog post on tech!",
//        "23a7819980msh3eb3b5081972f4cp1eed03jsn403f78d8eea7")
//    Log.d("chatgpt","ChatGPT: $response")
//    Log.d("apikey", "23a7819980msh3eb3b5081972f4cp1eed03jsn403f78d8eea7")
//}