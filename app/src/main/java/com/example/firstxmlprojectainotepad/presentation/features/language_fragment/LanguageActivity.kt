package com.example.firstxmlprojectainotepad.presentation.features.language_fragment

import android.app.DownloadManager.Query
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstxmlprojectainotepad.databinding.ActivityLanguageBinding
import com.example.firstxmlprojectainotepad.presentation.features.Hello_screen.Hello_Activity
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.HomeActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LanguageActivity : AppCompatActivity() {
    private lateinit var adView: AdView
    private lateinit var binding: ActivityLanguageBinding
    private lateinit var adaptor: LanguageAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        MobileAds.initialize(this)

        // Setup AdView
        adView = AdView(this).apply {
            adUnitId = "ca-app-pub-3940256099942544/9214589741" // Replace with your actual Ad Unit ID later
            setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this@LanguageActivity, 360))
        }

        val adContainer = binding.adViewContainer
        adContainer.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        val viewModel=ViewModelProvider(this)[LanguageViewModel::class.java]

        binding.LanguageInput.addTextChangedListener {
            viewModel.onSearchQueryChanged(it.toString())
        }

        adaptor=LanguageAdaptor()
        binding.languageRecycler.adapter=adaptor
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect(){
                    adaptor.submitList(it.filterListLanguage)
                }
            }
        }



        binding.languageRecycler.layoutManager = LinearLayoutManager(this)
        binding.isLanguageSelect.setOnClickListener {
            val intent = Intent(this, Hello_Activity::class.java)
            startActivity(intent)
            finish()
        }


    }
}




