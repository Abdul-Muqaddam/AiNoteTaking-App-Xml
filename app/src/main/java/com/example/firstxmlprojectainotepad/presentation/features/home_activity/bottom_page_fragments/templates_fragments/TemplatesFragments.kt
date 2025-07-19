package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.blue
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityPopupTemplateBinding
import com.example.firstxmlprojectainotepad.databinding.FragmentTemplatesBinding
import com.example.firstxmlprojectainotepad.presentation.features.Rate_us_screen.RateUsViewModel
import kotlinx.coroutines.launch

class TemplatesFragments : Fragment(

) {

    private var _binding: FragmentTemplatesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTemplatesBinding.inflate(layoutInflater, container, false)
        val recyclerView = binding.TemplateRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)

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


        recyclerView.adapter =
            TemplatesAdapter(colors, onTemplateClicks = {
                showtemplatedialog(it)
            })
        return binding.root


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showtemplatedialog(selectedColor: Int) {
        val viewModel = ViewModelProvider(this)[TemplateViewModel::class.java]
        viewModel.SelectedColor(selectedColor)


        val dialog = Dialog(requireContext())
        val dialogbinding = ActivityPopupTemplateBinding.inflate(layoutInflater)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogbinding.root)
        dialog.setCancelable(true)

        dialogbinding.crossPopup.setOnClickListener() {
            dialog.dismiss()
        }

        dialogbinding.buttonNoteWithThis.setOnClickListener() {
            dialog.dismiss()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->


                    dialogbinding.templateChangableBgColor.setColorFilter(state.color)
                }

            }
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.decorView?.setPadding(30,0,30,0)
    }
}

