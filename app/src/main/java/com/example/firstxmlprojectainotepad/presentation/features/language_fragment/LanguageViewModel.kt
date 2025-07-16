package com.example.firstxmlprojectainotepad.presentation.features.language_fragment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LanguageViewModel : ViewModel(){
    private val _language= listOf("English","German","French","Japan","Korean","Italian","Thai","Afrikaans","Turkish","Spanish","Chinese","Hindi","Russian","Urdu")
    private val _filteredList= MutableStateFlow(_language)
    val filteredList: StateFlow<List<String>> =_filteredList

    fun onSearchQueryChanged(query: String){
        val filtered = if(query.isBlank()) _language
        else _language.filter{it.contains(query,ignoreCase = true)}
        _filteredList.value=filtered
    }
}