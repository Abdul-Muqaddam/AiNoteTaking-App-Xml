package com.example.firstxmlprojectainotepad.presentation.features.home_activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityBottomExitDialogBinding
import com.example.firstxmlprojectainotepad.databinding.ActivityHomeBinding
import com.example.firstxmlprojectainotepad.presentation.features.Chat_Page.Chat_Page_Activity
import com.example.firstxmlprojectainotepad.presentation.features.Notes_Screen.Notes_Screen_Activity
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter.BottomAdapter
import com.example.firstxmlprojectainotepad.presentation.features.speech_to_text.SpeechToTextActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: BottomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        viewPager2 = binding.viewPager2

        adapter = BottomAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter




        binding.VoiceToTextBtn.setOnClickListener {
            val intent= Intent(this, SpeechToTextActivity::class.java)
            startActivity(intent)
        }
        binding.AiChat.setOnClickListener(){
            val intent=Intent(this,Chat_Page_Activity::class.java)
            startActivity(intent)
        }

        binding.aiNotes.setOnClickListener(){
            val intent=Intent(this,Notes_Screen_Activity::class.java)
            startActivity(intent)
        }

        binding.featureToAddNote.visibility = View.GONE

        binding.bgToHideTheAddNoteFeatures.setOnClickListener {

            binding.featureToAddNote.visibility = View.GONE
            binding.bgToHideTheAddNoteFeatures.visibility = View.GONE
        }
        binding.iconAddNote.setOnClickListener {
            binding.featureToAddNote.visibility = View.VISIBLE
            binding.bgToHideTheAddNoteFeatures.visibility = View.VISIBLE
        }

        binding.AiChat.setOnClickListener {
            val intent = Intent(this, Chat_Page_Activity::class.java)
            startActivity(intent)
        }
        binding.notesIconHomeBottom.setOnClickListener {
            viewPager2.currentItem = 0
        }
        binding.calenderIconHomeBottom.setOnClickListener {
            viewPager2.currentItem = 1
        }
        binding.searchIconHomeBottom.setOnClickListener {
            viewPager2.currentItem = 2
        }
        binding.templateIconHomeBottom.setOnClickListener {
            viewPager2.currentItem = 3
        }

        viewPager2.onFocusChangeListener
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)


                binding.notesImgIconHomeBottom.setColorFilter(getColor(R.color.grayB1))
                binding.notesTextIconHomeBottom.setTextColor(getColor(R.color.grayB1))


                binding.calenderImgIconHomeBottom.setColorFilter(getColor(R.color.grayB1))
                binding.calenderTextIconHomeBottom.setTextColor(getColor(R.color.grayB1))

                binding.searchImgIconHomeBottom.setColorFilter(getColor(R.color.grayB1))
                binding.searchTextIconHomeBottom.setTextColor(getColor(R.color.grayB1))

                binding.templateImgIconHomeBottom.setColorFilter(getColor(R.color.grayB1))
                binding.templateTextIconHomeBottom.setTextColor(getColor(R.color.grayB1))


                when (position) {
                    0 -> {
                        binding.notesImgIconHomeBottom.setColorFilter(getColor(R.color.purpleF0))
                        binding.notesTextIconHomeBottom.setTextColor(getColor(R.color.purpleF0))
                    }

                    1 -> {
                        binding.calenderImgIconHomeBottom.setColorFilter(getColor(R.color.purpleF0))
                        binding.calenderTextIconHomeBottom.setTextColor(getColor(R.color.purpleF0))
                    }

                    2 -> {
                        binding.searchImgIconHomeBottom.setColorFilter(getColor(R.color.purpleF0))
                        binding.searchTextIconHomeBottom.setTextColor(getColor(R.color.purpleF0))
                    }

                    3 -> {
                        binding.templateImgIconHomeBottom.setColorFilter(getColor(R.color.purpleF0))
                        binding.templateTextIconHomeBottom.setTextColor(getColor(R.color.purpleF0))
                    }

                }
            }
        })
//        binding.BottomSheet.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
//        binding.BottomSheet.adapter =
//            BottomAdapter(bottomSheetItem = bottomSheetItems, onItemSelected = { bottomIndex ->
//                binding.viewPager.currentItem=bottomIndex
//            })
//        binding.BottomSheet.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        binding.homeSearch.setOnClickListener{
//            val intent=Intent(this,SettingsActivity::class.java)
//            startActivity(intent)
//        }


        fun showExitDialog() {
            val dialogBinding = ActivityBottomExitDialogBinding.inflate(layoutInflater)
            val bottomSheetDialog = BottomSheetDialog(this, R.style.MyBottomSheetDialogTheme)
            bottomSheetDialog.setContentView(dialogBinding.root)
            bottomSheetDialog.setCancelable(true)

            dialogBinding.NoButton.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            dialogBinding.yesButton.setOnClickListener {
                bottomSheetDialog.dismiss()
                finish()
            }

            // Fix margins/padding when shown
            bottomSheetDialog.setOnShowListener { dialog ->
                val bottomSheet = (dialog as BottomSheetDialog)
                    .findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

                bottomSheet?.let { sheet ->
                    sheet.setBackgroundResource(R.drawable.rounded_top_dialog)

                    val behavior = BottomSheetBehavior.from(sheet)
                    behavior.isFitToContents = true
                    behavior.skipCollapsed = true
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED

                    // Force zero padding and overlap with nav bar
                    sheet.setPadding(0, 0, 0, 0)
                    val params = sheet.layoutParams as ViewGroup.MarginLayoutParams
                    params.setMargins(0, 0, 0, 0)
                    sheet.layoutParams = params

                    // Remove system insets (nav bar gap)
                    ViewCompat.setOnApplyWindowInsetsListener(sheet) { v, insets -> WindowInsetsCompat.CONSUMED }
                }
            }

            bottomSheetDialog.show()
        }

        onBackPressedDispatcher.addCallback {
            showExitDialog()
        }

    }
}

