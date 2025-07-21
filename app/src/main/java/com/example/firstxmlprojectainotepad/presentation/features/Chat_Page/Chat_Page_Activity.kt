package com.example.firstxmlprojectainotepad.presentation.features.Chat_Page

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstxmlprojectainotepad.databinding.ActivityChatPageBinding
import com.example.firstxmlprojectainotepad.databinding.ActivityCreditDialogBinding
import com.example.firstxmlprojectainotepad.networking.createHttpClient
import com.example.firstxmlprojectainotepad.networking.sendMessageToChatGPT
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.HomeActivity
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.launch


class Chat_Page_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityChatPageBinding
    private val messages = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter
    private val client: HttpClient by lazy {
        createHttpClient(Android.create())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChatPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        chatAdapter = ChatAdapter(messages)

        binding.chatRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@Chat_Page_Activity)
            adapter = chatAdapter
        }

        binding.BackArrowChat.setOnClickListener() {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.sendButton.setOnClickListener {

            if (binding.messageInput.text.isNotEmpty()) {

                binding.IdealGreeting.visibility = View.GONE
                val query = binding.messageInput.text.toString()
                binding.messageInput.setText("")

                // Step 1: Add user message
                messages.add(ChatMessage(query, true))
                chatAdapter.notifyItemInserted(messages.size - 1)

                // Step 2: Add bot loading animation
                val loadingMessage = ChatMessage("", false, isLoading = true)
                messages.add(loadingMessage)
                val loadingIndex = messages.size - 1
                chatAdapter.notifyItemInserted(loadingIndex)
                binding.chatRecyclerView.scrollToPosition(loadingIndex)

                lifecycleScope.launch {
                    val response = try {
                        sendMessageToChatGPT(
                            client,
                            query,
                            "11fdea3979msh6f6d72aad3b1b6fp14b0b0jsnfe210dec620e"
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.e("ChatGPTError", "API call failed: ${e.message}", e)
                        "Sorry, something went wrong."
                    }

                    // Step 3: Remove loading animation
                    messages.removeAt(loadingIndex)
                    chatAdapter.notifyItemRemoved(loadingIndex)

                    // Step 4: Add bot text message
                    messages.add(ChatMessage(response, false, isLoading = false))
                    chatAdapter.notifyItemInserted(messages.size - 1)
                    binding.chatRecyclerView.scrollToPosition(messages.size - 1)
                }
            }

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
        dialog.window?.decorView?.setPadding(30, 0, 30, 0)
    }
}
