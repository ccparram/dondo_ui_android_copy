package com.dondo.demo.examples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dondo.demo.databinding.ActivityProfilePictureBinding
import com.dondo.ui.utils.extensions.viewBinding

class ProfilePictureActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityProfilePictureBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val validUrl = "https://images.crowdswap.com/16803087918090442-IMG_20210415_164351.jpg"
        val invalidUrl = "https://images.crowdswap.com/16.jpg"

        with(binding) {
            profilePicture1.picture = validUrl

            profilePicture2.picture = validUrl
            profilePicture2.isVerified = true

            profilePicture3.picture = invalidUrl
        }
    }
}
