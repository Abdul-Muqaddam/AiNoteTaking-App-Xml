package com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.CalenderFragment
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.NotesFragment
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.SearchFragments
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments.TemplatesFragments

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
     return when(position){
         0->{
             NotesFragment()
         }
         1->{
             CalenderFragment()
         }
         2->{
             SearchFragments()
         }
         3->{
             TemplatesFragments()
         }
         else-> throw IllegalArgumentException("Invalid position :$position")
     }
    }

    override fun getItemCount(): Int = 4

}