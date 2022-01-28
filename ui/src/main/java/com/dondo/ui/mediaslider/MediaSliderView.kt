package com.dondo.ui.mediaslider

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dondo.ui.databinding.MediaSliderViewBinding

class MediaSliderView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

	private val binding: MediaSliderViewBinding by lazy {
		MediaSliderViewBinding.inflate(LayoutInflater.from(context), this, true)
	}

	init {
		rootView
		initViewsAndSetAdapter()
	}

	fun setElements(elements: List<String>) {
		(binding.vpSlider.adapter as? MediaSliderAdapter)?.setElements(elements)
			?: throw IllegalStateException("Initialize the adapter first")
	}

	private fun initViewsAndSetAdapter() {
		with(binding) {
			val pagerAdapter = MediaSliderAdapter()
			vpSlider.adapter = pagerAdapter
		}
	}
}
