package com.dondo.ui.mediaslider

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dondo.ui.R
import com.dondo.ui.databinding.ElementImageBinding

class ImageViewHolder(
    private val binding: ElementImageBinding,
    private val enableZoom: Boolean,
    private val onClickAction: () -> Unit
) : RecyclerView
.ViewHolder(binding.root) {

    fun bind(url: String) {
        binding.run {
            mBigImage.apply {
                load(url) {
                    placeholder(R.drawable.images)
                    error(R.drawable.images)
                }
                isZoomEnabled = enableZoom
                setOnTouchImageViewListener {
                    onClickAction.invoke()
                }
            }
        }
    }
}
