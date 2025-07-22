package com.example.firstxmlprojectainotepad.presentation.features.Notes_Screen


import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityNotesScreenBinding
import com.example.firstxmlprojectainotepad.networking.createHttpClient
import com.example.firstxmlprojectainotepad.networking.sendMessageToChatGPT
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Notes_Screen_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesScreenBinding
    private val viewModel: NoteViewModel by viewModel()
    private val client: HttpClient by lazy {
        createHttpClient(Android.create())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityNotesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dietPlanQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.diet_plan)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }
        binding.healthAndFitnessQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.health_fitness)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }
        binding.BrainStrommingQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.brain_stromming)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }
        binding.BusinuessQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.business)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }
        binding.TweetQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.tweet)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }
        binding.SportsQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.sports)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }
        binding.TravelQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.travel_tips)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }
        binding.CaptionQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.captions)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }
        binding.EssaysQuery.setOnClickListener {
            val existingText = binding.messageInput.text.toString()
            val newText = existingText + " " + getString(R.string.essays)
            binding.messageInput.setText(newText)
            binding.messageInput.setSelection(newText.length)
        }

        val today = LocalDate.now()
        val formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        binding.date.text = formattedDate




        binding.saveBtn.setOnClickListener {
            if(binding.noteTitle.text.toString().isNotEmpty() && binding.noteDescription.text.toString().isNotEmpty()){
                viewModel.saveNote(
                    title = binding.noteTitle.text.toString(),
                    description = binding.noteDescription.text.toString(),
                    date = formattedDate,
                )
                finish()
            }
        }

        binding.messageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int
            ) {
                val textEntered = s.toString().trim()
                if (textEntered.isNotEmpty()) {
                    binding.sendButton.setColorFilter(
                        ContextCompat.getColor(
                            this@Notes_Screen_Activity, R.color.purpleD6
                        )
                    )
                    binding.sendButton.setOnClickListener {
                        binding.sendButton.visibility = View.GONE
                        binding.chattingAnimation.visibility = View.VISIBLE
                        lifecycleScope.launch {
                            val Response = try {
                                sendMessageToChatGPT(
                                    client = client,
                                    prompt = "Write a Essays For me at least 100 word",
                                    apiKey = "11fdea3979msh6f6d72aad3b1b6fp14b0b0jsnfe210dec620e"
                                )
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Log.e("ChatGPTError", "API call failed: ${e.message}", e)
                                "Sorry, something went wrong."
                            }
                            binding.noteDescription.setText(Response)
                            binding.sendButton.visibility = View.VISIBLE
                            binding.chattingAnimation.visibility = View.GONE
                        }
                        binding.messageInput.setText("")
                    }
                } else {
                    binding.sendButton.setColorFilter(
                        ContextCompat.getColor(
                            this@Notes_Screen_Activity, R.color.grayD3
                        )
                    )
                    binding.sendButton.setOnClickListener(null)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


        // Set click listener on menu icon
        binding.dropdownMenu.setOnClickListener {
            val popup = PopupMenu(this, binding.dropdownMenu)
            popup.menuInflater.inflate(R.menu.template_menu, popup.menu)

            // Optional: Show icons
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popup.setForceShowIcon(true)
            }

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.all_edit -> {
                        binding.categoryTextAddNote.setText(R.string.all)
                        true
                    }

                    R.id.home_edit -> {
                        binding.categoryTextAddNote.setText(R.string.home)
                        true
                    }

                    R.id.work_edit -> {
                        binding.categoryTextAddNote.setText(R.string.work)
                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }
    }
}


//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.firstxmlprojectainotepad.R
//import com.example.firstxmlprojectainotepad.databinding.ActivityNotesScreenBinding
//
//class Notes_Screen_Activity : AppCompatActivity() {
//    private lateinit var binding :ActivityNotesScreenBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding=ActivityNotesScreenBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        enableEdgeToEdge()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            popup.setForceShowIcon(true)
//        }
//        holder.binding.menuIcon.setOnClickListener {
//            val popup = PopupMenu(holder.itemView.context, holder.binding.menuIcon)
//            popup.menuInflater.inflate(R.menu.template_menu, popup.menu)
//
//            popup.setOnMenuItemClickListener { menuItem ->
//                when (menuItem.itemId) {
//                    R.id.action_edit -> {
//                        // Handle edit
//                        Toast.makeText(context, "Edit clicked", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    R.id.action_delete -> {
//                        // Handle delete
//                        Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    else -> false
//                }
//            }
//
//            popup.show()
//        }
//
//    }
//}