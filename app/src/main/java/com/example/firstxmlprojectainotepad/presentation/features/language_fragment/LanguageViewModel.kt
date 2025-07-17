package com.example.firstxmlprojectainotepad.presentation.features.language_fragment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class LanguageViewModelState(
    var filterListLanguage: List<LanguageModel> = LanguageModel.entries.toList()
)


class LanguageViewModel : ViewModel() {
    private val _state = MutableStateFlow(LanguageViewModelState())
    val state: StateFlow<LanguageViewModelState> get() = _state


    fun onSearchQueryChanged(query: String) {

        val filtered=if(query.isBlank()){
            LanguageModel.entries.toList()
        }else{
            _state.value.filterListLanguage.filter{
                it.displayName.contains(query,ignoreCase = true)
            }
        }

        _state.update {
            it.copy(
                filterListLanguage = filtered
            )
        }

    }
}