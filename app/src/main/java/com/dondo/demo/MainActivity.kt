package com.dondo.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dondo.demo.databinding.ActivityMainBinding
import com.dondo.demo.examples.MediaSliderDemoActivity
import com.dondo.demo.examples.ProfilePictureActivity
import com.dondo.demo.examples.TextFieldsActivity
import com.dondo.ui.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            btMediaSlider.setOnClickListener {
                startActivity(Intent(this@MainActivity, MediaSliderDemoActivity::class.java))
            }

            btTextFields.setOnClickListener {
                startActivity(Intent(this@MainActivity, TextFieldsActivity::class.java))
            }

            btProfilePicture.setOnClickListener {
                startActivity(Intent(this@MainActivity, ProfilePictureActivity::class.java))
            }
        }
    }
}
