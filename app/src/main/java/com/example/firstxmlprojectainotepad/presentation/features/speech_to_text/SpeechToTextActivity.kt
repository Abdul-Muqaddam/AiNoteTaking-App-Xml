package com.example.firstxmlprojectainotepad.presentation.features.speech_to_text

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.firstxmlprojectainotepad.databinding.ActivitySpeechToTextBinding
import kotlinx.coroutines.launch


class SpeechToTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpeechToTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySpeechToTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.SpeechToTextBackArrow.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProvider(this)[SpeechToTextViewModel::class.java]

        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->

                    if (state.RecordingStateIdeal == true) {
                        binding.tapToRecord.visibility = View.VISIBLE
                        binding.idealStateImg.visibility = View.VISIBLE
                    } else {
                        binding.tapToRecord.visibility = View.GONE
                        binding.idealStateImg.visibility = View.GONE
                    }

                    if (state.RecordingStateRecording == true) {
                        binding.recordingInProcess.visibility = View.VISIBLE
                        binding.RecordingStateImgs.visibility = View.VISIBLE
                    } else {
                        binding.recordingInProcess.visibility = View.GONE
                        binding.RecordingStateImgs.visibility = View.GONE
                    }

                    if (state.RecordingStateCompleted == true) {
                        binding.CompletedTapToRecord.visibility = View.VISIBLE
                        binding.RecordingCompletedTxt.visibility = View.VISIBLE
                        binding.RecordingCompletedFeature.visibility = View.VISIBLE


                    } else {
                        binding.CompletedTapToRecord.visibility = View.GONE
                        binding.RecordingCompletedTxt.visibility = View.GONE
                        binding.RecordingCompletedFeature.visibility = View.GONE


                    }
                }
            }
        }


        binding.tapToRecord.setOnClickListener {
            viewModel.RecordingStateIdealChange(false)
            viewModel.RecordingStateRecordingChange(true)
        }
        binding.recordingInProcess.setOnClickListener {
            viewModel.RecordingStateCompletedChange(true)
            viewModel.RecordingStateRecordingChange(false)
        }

    }
}