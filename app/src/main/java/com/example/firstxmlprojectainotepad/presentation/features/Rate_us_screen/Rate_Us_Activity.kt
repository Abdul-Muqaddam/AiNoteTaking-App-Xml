package com.example.firstxmlprojectainotepad.presentation.features.Rate_us_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityRateUsBinding
import com.example.firstxmlprojectainotepad.presentation.features.setting_activity.SettingsActivity
import kotlinx.coroutines.launch

class Rate_Us_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRateUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.icBackArrowRate.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val viewModel=ViewModelProvider(this)[RateUsViewModel::class.java]

        lifecycleScope.launch(){
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{ state->
                    binding.icEmoji.setImageResource(state.reviewImg)
                    binding.reviewText.setText(state.reviewText)


                    val starIds = listOf(
                        binding.icStar1,
                        binding.icStar2,
                        binding.icStar3,
                        binding.icStar4,
                        binding.icStar5
                    )

                    for (i in starIds.indices) {
                        if (i < state.ratingStar) {
                            starIds[i].setImageResource(R.drawable.ic_selected_star)
                        } else {
                            starIds[i].setImageResource(R.drawable.ic_star_unselected)
                        }
                    }

                }
            }
        }

        binding.icStar1.setOnClickListener {
            viewModel.onRatingChange(1)
        }
        binding.icStar2.setOnClickListener {
            viewModel.onRatingChange(2)

        }
        binding.icStar3.setOnClickListener {
            viewModel.onRatingChange(3)

        }
        binding.icStar4.setOnClickListener {
            viewModel.onRatingChange(4)

        }
        binding.icStar5.setOnClickListener {
            viewModel.onRatingChange(5)
        }



    }
}