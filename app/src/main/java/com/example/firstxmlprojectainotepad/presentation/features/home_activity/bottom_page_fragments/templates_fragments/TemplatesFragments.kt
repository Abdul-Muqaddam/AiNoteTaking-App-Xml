package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.FragmentTemplatesBinding

class TemplatesFragments : Fragment(){
    private var _binding: FragmentTemplatesBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentTemplatesBinding.inflate(layoutInflater,container,false)
        val recyclerView = binding.TemplateRecyclerView
        recyclerView.layoutManager= GridLayoutManager(requireContext(),4)

        val colors = listOf(
            ContextCompat.getColor(requireContext(), R.color.yellowEA),
            ContextCompat.getColor(requireContext(), R.color.pinkF8),
            ContextCompat.getColor(requireContext(), R.color.blueFF),
            ContextCompat.getColor(requireContext(), R.color.pinkEB),
            ContextCompat.getColor(requireContext(), R.color.greenE5),
            ContextCompat.getColor(requireContext(), R.color.yellowCE),
            ContextCompat.getColor(requireContext(), R.color.blueF6),
            ContextCompat.getColor(requireContext(), R.color.blueF4),
            ContextCompat.getColor(requireContext(), R.color.purple2FF),
            ContextCompat.getColor(requireContext(), R.color.pinkDB),
            ContextCompat.getColor(requireContext(), R.color.greenD3),
            ContextCompat.getColor(requireContext(), R.color.white),
            ContextCompat.getColor(requireContext(), R.color.yellowEA),
            ContextCompat.getColor(requireContext(), R.color.pinkF8),
            ContextCompat.getColor(requireContext(), R.color.blueFF),
            ContextCompat.getColor(requireContext(), R.color.pinkEB),
            ContextCompat.getColor(requireContext(), R.color.greenE5),
            ContextCompat.getColor(requireContext(), R.color.yellowCE),
            ContextCompat.getColor(requireContext(), R.color.blueF6),
        )


        recyclerView.adapter = TemplatesAdapter(colors)

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}

