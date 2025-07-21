package com.example.firstxmlprojectainotepad.presentation.features.speech_to_text

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
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
import java.util.Locale


class SpeechToTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpeechToTextBinding
    private lateinit var speechRecognizer: SpeechRecognizer
    private var isListening = false
    private var shouldKeepListening = true
    private val REQUEST_RECORD_AUDIO_PERMISSION = 1
    private lateinit var speechIntent: Intent

    private var mediaRecorder: MediaRecorder? = null
    private var outputFile: String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySpeechToTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.SpeechToTextBackArrow.setOnClickListener {
            finish()
        }


        fun startListening() {
            if (!isListening) {
                isListening = true
                speechRecognizer.startListening(speechIntent)
            }
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(rmsdB: Float) {

            }

            override fun onBufferReceived(buffer: ByteArray?) {

            }


            override fun onEndOfSpeech() {
                isListening = false
                if (shouldKeepListening) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        startListening()
                    }, 1000)
                }
            }

            override fun onError(error: Int) {
//                Toast.makeText(applicationContext, "Recognition error: $error", Toast.LENGTH_SHORT).show()
                isListening = false
                if (shouldKeepListening) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        startListening()
                    }, 1000)
                }
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                matches?.let {
                    val previousText = binding.RecordingCompletedTxt.text.toString()
                    val newText = it[0]
//                    Toast.makeText(this@SpeechToTextActivity, newText, Toast.LENGTH_SHORT).show()
                    binding.RecordingCompletedTxt.setText("$previousText $newText")
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {

            }

            override fun onEvent(eventType: Int, params: Bundle?) {

            }

        })

        speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
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
                        binding.RecordingAnimation.visibility=View.VISIBLE
                        binding.RecordingInProgressTimeLineImg.visibility=View.GONE
                    } else {
                        binding.recordingInProcess.visibility = View.GONE
                        binding.RecordingStateImgs.visibility = View.GONE
                    }

                    if (state.RecordingStateCompleted == true) {
                        binding.RecordingInProgressTimeLineImg.visibility=View.VISIBLE
                        binding.RecordingAnimation.visibility=View.GONE
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
                speechRecognizer.startListening(speechIntent)
                viewModel.RecordingStateIdealChange(false)
                viewModel.RecordingStateRecordingChange(true)
            }
        }

        binding.copySTTResult.setOnClickListener {
            val textToCopy = binding.RecordingCompletedTxt.text
            if(textToCopy.isNotBlank()){
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("STT Result", textToCopy)
                clipboard.setPrimaryClip(clip)
            }
        }
        binding.saveSTTResult.setOnClickListener {
            val textToDownload = binding.RecordingCompletedTxt.text.toString()

            if (textToDownload.isNotBlank()) {
                val fileName = "speech_result.txt"

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    // For Android 10+
                    val contentValues = android.content.ContentValues().apply {
                        put(android.provider.MediaStore.Downloads.DISPLAY_NAME, fileName)
                        put(android.provider.MediaStore.Downloads.MIME_TYPE, "text/plain")
                        put(android.provider.MediaStore.Downloads.IS_PENDING, 1)
                    }

                    val contentResolver = applicationContext.contentResolver
                    val uri = contentResolver.insert(android.provider.MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

                    uri?.let {
                        contentResolver.openOutputStream(it)?.use { outputStream ->
                            outputStream.write(textToDownload.toByteArray())
                        }
                        contentValues.clear()
                        contentValues.put(android.provider.MediaStore.Downloads.IS_PENDING, 0)
                        contentResolver.update(uri, contentValues, null, null)
                        Toast.makeText(this, "File saved to Downloads", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // For Android 9 and below (API 26–28)
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
                        return@setOnClickListener
                    }

                    val downloadsDir = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_DOWNLOADS)
                    val file = java.io.File(downloadsDir, fileName)

                    try {
                        file.writeText(textToDownload)
                        Toast.makeText(this, "File saved to Downloads", Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "No text to save", Toast.LENGTH_SHORT).show()
            }
        }

        binding.recordingInProcess.setOnClickListener {
            shouldKeepListening = false
            speechRecognizer.stopListening()
            speechRecognizer.cancel()
            isListening = false
            viewModel.RecordingStateCompletedChange(true)
            viewModel.RecordingStateRecordingChange(false)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }
}