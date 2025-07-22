package com.example.firstxmlprojectainotepad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.firstxmlprojectainotepad.databinding.ActivitySplashBinding
import com.example.firstxmlprojectainotepad.presentation.features.language_fragment.LanguageActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var binding: ActivitySplashBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) // Important!
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        loadInterstitialAd()

        binding.btnContinue.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        Log.d(TAG, "Ad dismissed")
                        mInterstitialAd = null
                        goToNextScreen()
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        Log.d(TAG, "Ad failed to show: ${adError.message}")
                        mInterstitialAd = null
                        goToNextScreen()
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG, "Ad showed fullscreen content")
                    }
                }
                mInterstitialAd?.show(this)
            } else {
                Log.d(TAG, "Ad wasn't ready")
                goToNextScreen()
            }
        }
    }

    private fun goToNextScreen() {
        val intent = Intent(this@MainActivity, LanguageActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d(TAG, "Ad loaded")
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, "Failed to load ad: ${adError.message}")
                    mInterstitialAd = null
                }
            }
        )
    }
}
