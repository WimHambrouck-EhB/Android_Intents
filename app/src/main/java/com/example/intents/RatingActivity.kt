package com.example.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intents.databinding.ActivityRatingBinding

class RatingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRatingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.secondActivityButton.setOnClickListener {
            if (checkFields()) {
                startSecondActivity()
            }
        }
    }

    private fun startSecondActivity() {
        Intent(this, SecondActivity::class.java).apply {
            putExtra(EXTRA_TEXT, binding.someText.text.toString())
            putExtra(EXTRA_RATING, binding.rating.rating)
        }.also {
            startActivity(it)
        }
    }

    private fun checkFields(): Boolean {
        if (binding.someText.text.isNullOrBlank()) {
            binding.someText.error = getString(R.string.invalid_input)
            return false
        }

        return true
    }

    companion object {
        const val EXTRA_TEXT = " org.example.intents.EXTRA_TEXT"
        const val EXTRA_RATING = " org.example.intents.EXTRA_RATING"
    }
}