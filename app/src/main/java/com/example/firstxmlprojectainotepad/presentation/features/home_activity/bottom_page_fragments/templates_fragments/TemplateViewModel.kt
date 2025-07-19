package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments

import androidx.lifecycle.ViewModel
import com.example.firstxmlprojectainotepad.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


data class TemplateViewModelState(
    val color: Int = R.color.blueF6
)

class TemplateViewModel:ViewModel() {
    private val _state = MutableStateFlow(TemplateViewModelState())
    val state: StateFlow<TemplateViewModelState> get() =_state

    fun SelectedColor(color: Int) {
     _state.update {
         it.copy(
          color=color
         )
     }
    }

}