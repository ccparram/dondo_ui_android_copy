package com.dondo.ui.mediaslider

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.dondo.ui.databinding.ElementVideoBinding
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class VideoViewHolder(private val binding: ElementVideoBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(url: String) {
		binding.run {

			val player = ExoPlayerFactory.newSimpleInstance(
				DefaultRenderersFactory(binding.root.context),
				DefaultTrackSelector(), DefaultLoadControl()
			)

			val mediaUri = Uri.parse(url)

			val mediaSource = ExtractorMediaSource
				.Factory(DefaultHttpDataSourceFactory("media-slider-view"))
				.createMediaSource(mediaUri)

			playerView.player = player
			player.prepare(mediaSource, true, true)
			player.playWhenReady = false
			player.seekTo(0, 0)
		}
	}
}
