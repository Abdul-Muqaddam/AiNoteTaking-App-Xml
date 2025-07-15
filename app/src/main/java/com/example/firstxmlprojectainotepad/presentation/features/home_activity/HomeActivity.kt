package com.example.firstxmlprojectainotepad.presentation.features.home_activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityHomeBinding
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter.BottomAdapter

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




        binding.featureToAddNote.visibility = View.GONE

        binding.bgToHideTheAddNoteFeatures.setOnClickListener {

            binding.featureToAddNote.visibility = View.GONE
            binding.bgToHideTheAddNoteFeatures.visibility = View.GONE
        }
        binding.iconAddNote.setOnClickListener {
            binding.featureToAddNote.visibility = View.VISIBLE
            binding.bgToHideTheAddNoteFeatures.visibility = View.VISIBLE
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
    }
}