package com.dondo.demo.examples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dondo.demo.databinding.ActivityMediaSliderDemoBinding
import com.dondo.ui.utils.viewBinding
import timber.log.Timber

class MediaSliderDemoActivity : AppCompatActivity() {

	private val binding by viewBinding(ActivityMediaSliderDemoBinding::inflate)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val list = arrayListOf(
			"https://res.cloudinary.com/kartiksaraf/image/upload/v1564514468/github_MediaSliderView/demo_images/8-phone-wallpaper_gcseap.jpg",
			"https://res.cloudinary.com/kartiksaraf/image/upload/v1564514549/github_MediaSliderView/demo_images/ea0ef44d800aa07722c25b1a6db58800--iphone-backgrounds-phone-wallpapers_cqmbbx.jpg",
			"https://res.cloudinary.com/kartiksaraf/video/upload/v1564516308/github_MediaSliderView/demo_videos/video1_jetay3.mp4",
			"https://res.cloudinary.com/kartiksaraf/image/upload/v1564514590/github_MediaSliderView/demo_images/Quotefancy-20588-3840x2160_msurjx.jpg",
			"https://res.cloudinary.com/kartiksaraf/image/upload/v1564514634/github_MediaSliderView/demo_images/Quotefancy-2098-3840x2160_nrez6k.jpg",
			"https://res.cloudinary.com/kartiksaraf/image/upload/v1564514699/github_MediaSliderView/demo_images/download_totbb2.jpg",
			"https://res.cloudinary.com/kartiksaraf/video/upload/v1564516308/github_MediaSliderView/demo_videos/video2_sn3sek.mp4"
		)

		binding.cvSlider.setElements(list)
		binding.cvSlider.currentItem = 2
		binding.cvSlider.onPageSelected = { position ->
			Timber.d("onPageSelected, position: $position")
		}
	}
}
