package com.example.firstxmlprojectainotepad.presentation.features.speech_to_text

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class SpeechToTextViewModelState(
    var RecordingStateIdeal: Boolean = true,
    var RecordingStateRecording: Boolean = false,
    var RecordingStateCompleted: Boolean = false,
)

class SpeechToTextViewModel : ViewModel(){
    private val _state= MutableStateFlow(SpeechToTextViewModelState())
    val state: StateFlow<SpeechToTextViewModelState> get() = _state

    fun RecordingStateRecordingChange(RecordingStateRecording: Boolean){
        _state.update {
            it.copy(
                RecordingStateRecording=RecordingStateRecording
            )
        }
    }

    fun RecordingStateCompletedChange(RecordingStateCompleted: Boolean){
        _state.update {
            it.copy(
                RecordingStateCompleted =RecordingStateCompleted
            )
        }
    }

    fun RecordingStateIdealChange(RecordingStateIdeal: Boolean){
        _state.update {
            it.copy(
                RecordingStateIdeal=RecordingStateIdeal
            )
        }
    }

}