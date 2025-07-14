package com.example.firstxmlprojectainotepad.presentation.features.home_activity

import com.example.firstxmlprojectainotepad.R

val categoriesItem = listOf(
    R.string.all,
    R.string.work,
    R.string.office,
    R.string.shopping
)

data class BottomSheetItem(val title: Int, val icon: Int)

val bottomSheetItems = listOf(
    BottomSheetItem(title = R.string.notes, icon = R.drawable.ic_note_home),
    BottomSheetItem(title = R.string.calender, icon = R.drawable.ic_calendar),
    BottomSheetItem(title = R.string.search, icon = R.drawable.ic_search_bottom),
    BottomSheetItem(title = R.string.templates, icon = R.drawable.ic_gallary),
)

