package com.example.firstxmlprojectainotepad.presentation.features.speech_to_text

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.firstxmlprojectainotepad.databinding.ActivitySpeechToTextBinding
import kotlinx.coroutines.launch
import kotlinx.io.IOException


class SpeechToTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpeechToTextBinding
    private val REQUEST_RECORD_AUDIO_PERMISSION = 1
    private var mediaRecorder: MediaRecorder? = null
    private var outputFile: String = ""


    private fun startRecording() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(outputFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
                start()
                Toast.makeText(this@SpeechToTextActivity, "Recording Started", Toast.LENGTH_SHORT)
                    .show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this@SpeechToTextActivity, "Recording Failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
            Toast.makeText(
                this@SpeechToTextActivity,
                "Recording Stopped. File saved to: $outputFile",
                Toast.LENGTH_LONG
            ).show()
        }
        mediaRecorder = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySpeechToTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.SpeechToTextBackArrow.setOnClickListener {
            finish()
        }

        val viewModel = ViewModelProvider(this)[SpeechToTextViewModel::class.java]

        outputFile = "${externalCacheDir?.absolutePath}/audiorecord.3gp"
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

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_PERMISSION
                )

            } else {
                startRecording()

                viewModel.RecordingStateIdealChange(false)
                viewModel.RecordingStateRecordingChange(true)
            }
        }
        binding.recordingInProcess.setOnClickListener {
            stopRecording()
            viewModel.RecordingStateCompletedChange(true)
            viewModel.RecordingStateRecordingChange(false)
        }

    }
}