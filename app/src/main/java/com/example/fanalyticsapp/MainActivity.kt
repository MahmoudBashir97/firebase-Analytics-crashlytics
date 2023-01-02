package com.example.fanalyticsapp

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fanalyticsapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    lateinit var binding:ActivityMainBinding
    private  var analytics: FirebaseAnalytics = Firebase.analytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        analytics = Firebase.analytics

        binding.clickBtn.setOnClickListener {
            analytics.logEvent(
                "btn_click",null)
        }

        // Creates a button that mimics a crash when pressed
        val crashButton = Button(this)
        crashButton.setText("Test Crash")
        crashButton.setOnClickListener{
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(
            crashButton, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )


    }
}