package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.FragmentNotesBinding
import com.example.firstxmlprojectainotepad.presentation.features.Popup_template.PopupTemplateActivity
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter.CategoryAdapter
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.categoriesItem
import com.example.firstxmlprojectainotepad.presentation.features.premium_screen.Premium_Activity
import com.example.firstxmlprojectainotepad.presentation.features.setting_activity.SettingsActivity


class NotesFragment : Fragment(){
    private var _binding: FragmentNotesBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentNotesBinding.inflate(inflater,container,false)
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = CategoryAdapter(categoriesItem = categoriesItem)
        binding.IconHamburgur.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.premium.setOnClickListener{
            val intent = Intent(requireContext(),Premium_Activity::class.java)
            startActivity(intent)
        }
        binding.DrawerSettings.setOnClickListener {
            val intent= Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }
        binding.homeSearch.setOnClickListener(){
            val intent=Intent(requireContext(),PopupTemplateActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}