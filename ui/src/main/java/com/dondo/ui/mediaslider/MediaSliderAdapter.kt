package com.dondo.ui.mediaslider;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dondo.ui.databinding.ElementImageBinding
import com.dondo.ui.databinding.ElementUnknownBinding
import com.dondo.ui.databinding.ElementVideoBinding

class MediaSliderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	val IMAGE_TYPE = 0
	val VIDEO_TYPE = 1
	val UNKNOWN_TYPE = -1

	val VIDEO_EXTENSION = ".mp4"
	val IMAGE_EXTENSION = ".jpeg"

	val elements = ArrayList<String>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return when (viewType) {
			IMAGE_TYPE -> ImageViewHolder(
				ElementImageBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
			VIDEO_TYPE -> VideoViewHolder(
				ElementVideoBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
			else -> UnknownMediaViewHolder(
				ElementUnknownBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (holder) {
			is ImageViewHolder -> holder.bind(elements[position])
			is VideoViewHolder -> holder.bind(elements[position])
			else -> {}
		}
	}

	override fun getItemViewType(position: Int): Int {
		return when {
			elements[position].endsWith(IMAGE_EXTENSION) -> IMAGE_TYPE
			elements[position].endsWith(VIDEO_EXTENSION) -> VIDEO_TYPE
			else -> UNKNOWN_TYPE
		}

	}

	override fun getItemCount(): Int {
		return elements.size
	}

	fun setElements(elements: List<String>) {
		this.elements.clear()
		this.elements.addAll(elements)
		notifyItemRangeInserted(0, elements.size)
	}
}
