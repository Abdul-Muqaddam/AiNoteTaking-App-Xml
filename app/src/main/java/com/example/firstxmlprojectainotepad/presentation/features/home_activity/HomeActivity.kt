package com.example.firstxmlprojectainotepad.presentation.features.home_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstxmlprojectainotepad.databinding.ActivityHomeBinding
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter.BottomAdapter
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter.CategoryAdapter
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.CalenderFragment
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.NotesFragment
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.SearchFragments
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.TemplatesFragments
import com.example.firstxmlprojectainotepad.presentation.features.setting_activity.SettingsActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = CategoryAdapter(categoriesItem = categoriesItem)

        binding.BottomSheet.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.BottomSheet.adapter =
            BottomAdapter(bottomSheetItem = bottomSheetItems, onItemSelected = { bottomIndex ->
                binding.viewPager.currentItem=bottomIndex
            })
        binding.BottomSheet.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.homeSearch.setOnClickListener{
            val intent=Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}