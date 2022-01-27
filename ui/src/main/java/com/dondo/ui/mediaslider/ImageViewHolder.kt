package com.dondo.ui.mediaslider

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dondo.ui.R
import com.dondo.ui.databinding.ElementImageBinding

class ImageViewHolder(private val binding: ElementImageBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(url: String) {
		binding.run {
			mBigImage.load(url) {
				placeholder(R.drawable.images)
				error(R.drawable.images)
			}
		}
	}
}
