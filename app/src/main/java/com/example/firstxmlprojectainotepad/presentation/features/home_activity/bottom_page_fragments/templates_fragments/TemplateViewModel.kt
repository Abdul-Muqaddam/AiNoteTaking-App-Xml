package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.data.ImageEntity
import com.example.firstxmlprojectainotepad.repository.ImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class TemplateViewModelState(
    val color: Int = R.color.blueF6
)

class TemplateViewModel(
    private val repository: ImageRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TemplateViewModelState())
    val state: StateFlow<TemplateViewModelState> get() =_state

    val allImages: StateFlow<List<ImageEntity>> = repository.allImages
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun selectColor(color: Int) {
        _state.update { it.copy(color = color) }
    }

    fun saveImage(path: String, title: String?) {
        viewModelScope.launch {
            repository.insert(ImageEntity(imagePath = path, title = title))
        }
    }

    fun deleteImage(image: ImageEntity) {
        viewModelScope.launch {
            repository.delete(image)
        }
    }
}