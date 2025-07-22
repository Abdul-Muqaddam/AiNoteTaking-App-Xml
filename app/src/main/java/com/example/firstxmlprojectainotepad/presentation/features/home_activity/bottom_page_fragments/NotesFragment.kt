package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstxmlprojectainotepad.databinding.FragmentNotesBinding
import com.example.firstxmlprojectainotepad.presentation.features.Notes_Screen.NoteViewModel
import com.example.firstxmlprojectainotepad.presentation.features.Popup_template.PopupTemplateActivity
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter.CategoryAdapter
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter.NotesAdapter
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.categoriesItem
import com.example.firstxmlprojectainotepad.presentation.features.premium_screen.Premium_Activity
import com.example.firstxmlprojectainotepad.presentation.features.setting_activity.SettingsActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotesFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModel()
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        // Setup category recycler view
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = CategoryAdapter(categoriesItem = categoriesItem)

        binding.categoryRecyclerView
        // Set up Notes RecyclerView
        val spanCount = 2
        notesAdapter = NotesAdapter(emptyList())
        binding.MainNotesRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding.MainNotesRecyclerView.adapter = notesAdapter

        // Drawer, navigation etc
        binding.IconHamburgur.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.premium.setOnClickListener {
            startActivity(Intent(requireContext(), Premium_Activity::class.java))
        }
        binding.DrawerSettings.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }
        binding.homeSearch.setOnClickListener {
            startActivity(Intent(requireContext(), PopupTemplateActivity::class.java))
        }

        // Fetch notes from ViewModel
        viewModel.loadAllNotes()
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notes.collect { notes ->
                    if (notes.isNotEmpty()) {
                        binding.NoteIfEmpty.visibility = View.GONE
                        notesAdapter.updateData(notes)
                    } else {
                        binding.NoteIfEmpty.visibility = View.VISIBLE
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
