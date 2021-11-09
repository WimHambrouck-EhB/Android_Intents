package com.example.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = View.OnClickListener { buttonClicked(it as? Button) }

        binding.appButton.setOnClickListener(listener)
        binding.implicitButton.setOnClickListener(listener)
        binding.explicitButton.setOnClickListener(listener)
    }

    private fun buttonClicked(button: Button?) {
        when (button?.id) {
            R.id.appButton -> {
                // intent to other activity, no data
                startActivity(Intent(this, RatingActivity::class.java))
            }
            R.id.implicitButton -> {
                // intents to other applications define an action
                val implicitIntent = Intent(Intent.ACTION_VIEW)

                // add some data to the intent
                implicitIntent.data = Uri.parse("http://www.ehb.be")

                // start activity with the intent, the system will choose the appropriate app
                // or ask the user to choose if multiple apps are available
                startActivity(implicitIntent)
            }
            R.id.explicitButton -> {
                // intents to other applications define an action
                val explicitIntent = Intent(Intent.ACTION_VIEW)

                // add some data to the intent (see also: https://developer.android.com/guide/components/intents-common#Maps)
                explicitIntent.data = Uri.parse(EHB_GEO_URI)

                // define which application we want to handle the intent (in this case Google Maps)
                explicitIntent.setPackage(GOOGLE_MAPS_PACKAGE)

                // note: you can also use an apply scope function for intent configuration:
                // (https://kotlinlang.org/docs/scope-functions.html#apply)
                /*
                val explicitIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("geo:0,0?q=Nijverheidskaai 170, 1070 Anderlecht")
                    setPackage("com.google.android.apps.maps")
                }
                 */

                // check if an application with that package name is available to start the intent
                if (explicitIntent.resolveActivity(packageManager) != null) {
                    startActivity(explicitIntent)
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.google_maps_not_installed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        private const val EHB_GEO_URI = "geo:0,0?q=Nijverheidskaai 170, 1070 Anderlecht"
        private const val GOOGLE_MAPS_PACKAGE = "com.google.android.apps.maps"
    }
}