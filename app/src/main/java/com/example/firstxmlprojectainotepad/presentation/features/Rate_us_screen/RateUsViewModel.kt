package com.example.firstxmlprojectainotepad.presentation.features.Rate_us_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstxmlprojectainotepad.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class RateUsViewModelState(
    val reviewText: Int = R.string._empty,
    val reviewImg: Int = R.drawable.ic_happy_emoji,
    val ratingStar: Int = 0
)


class RateUsViewModel : ViewModel() {
    private val _state = MutableStateFlow(RateUsViewModelState())
    val state: StateFlow<RateUsViewModelState> get() = _state


    fun onRatingChange(index: Int) {
        val reviewTextLocal= when (index) {
            1 -> R.string.very_bad
            2 ->R.string.confused
            3 -> R.string.average
            4 -> R.string.good
            5-> R.string.excellent
            else->R.string._empty

        }
        val reviewImgLocal= when (index) {
            1 -> R.drawable.ic_bad_emoji
            2 ->R.drawable.ic_confuse_emoji
            3 -> R.drawable.ic_average_emoji
            4 -> R.drawable.ic_good_emoji
            5-> R.drawable.ic_excellent_emoji
            else->R.drawable.ic_happy_emoji

        }


        _state.update {
            it.copy(
                reviewText = reviewTextLocal,
                reviewImg = reviewImgLocal,
                ratingStar = index
            )
        }

    }
}