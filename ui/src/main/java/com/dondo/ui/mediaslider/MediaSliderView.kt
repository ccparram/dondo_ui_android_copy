package com.dondo.ui.mediaslider

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.dondo.ui.databinding.MediaSliderViewBinding
import com.dondo.ui.utils.Constants.NO_SELECTION

class MediaSliderView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

	private val binding: MediaSliderViewBinding by lazy {
		MediaSliderViewBinding.inflate(LayoutInflater.from(context), this, true)
	}

	private lateinit var adapter: MediaSliderAdapter

	init {
		rootView
		initViewsAndSetAdapter()
	}

	var currentItem = NO_SELECTION
		set(value) {
			field = value
			binding.vpSlider.currentItem = field
		}
		get() {
			return binding.vpSlider.currentItem
		}

	var onPageSelected: (position: Int) -> Unit = {}
		set(value) {
			field = value
			binding.vpSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
				override fun onPageSelected(position: Int) {
					super.onPageSelected(position)
					field(position)
				}
			})
		}

	private fun initViewsAndSetAdapter() {
		with(binding) {
			MediaSliderAdapter().apply {
				adapter = this
				vpSlider.adapter = this
			}
		}
	}

	fun setElements(elements: List<String>) {
		adapter.setElements(elements)
	}
}
