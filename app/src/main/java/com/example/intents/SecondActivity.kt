package com.example.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.intents.databinding.ActivitySecondBinding
import kotlin.math.roundToInt

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intent contains the intent that started the activity (along with any extras)
        val userText = intent.getStringExtra(RatingActivity.EXTRA_TEXT)
        val rating = intent.getFloatExtra(RatingActivity.EXTRA_RATING, -1f)

        if (userText.isNullOrBlank() || rating == -1f) {
            Log.e("SecondActivity", "No/invalid data found in intent.")
            finish() //closes the activity
        } else {
            binding.userTextView.text = userText
            binding.ratingTextView.text = getString(R.string.star_rating, rating.roundToInt())
        }
    }
}