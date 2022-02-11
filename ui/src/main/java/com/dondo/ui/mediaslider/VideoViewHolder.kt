package com.dondo.ui.mediaslider

import android.net.Uri
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.dondo.ui.databinding.ElementVideoBinding

class VideoViewHolder(
    private val binding: ElementVideoBinding,
    private val showVideoController: Boolean,
    private val onClickAction: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(url: String) {
        binding.run {
            videoView.apply {
                if (showVideoController) {
                    val mediaController = MediaController(this.context)
                    mediaController.setAnchorView(this)
                    this.setMediaController(mediaController)
                }

                setVideoURI(Uri.parse(url))
                setOnPreparedListener { it.start() }
                setOnCompletionListener { it.start() }
                setOnClickListener { onClickAction.invoke() }
            }
        }
    }
}
