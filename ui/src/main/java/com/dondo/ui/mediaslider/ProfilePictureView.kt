package com.dondo.ui.mediaslider

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import coil.load
import coil.transform.CircleCropTransformation
import com.dondo.ui.R
import com.dondo.ui.databinding.ProfilePictureViewBinding
import com.dondo.ui.utils.Constants.EMPTY
import com.dondo.ui.utils.getDrawableCompat

class ProfilePictureView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ProfilePictureViewBinding by lazy {
        ProfilePictureViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var isVerified = false
        set(value) {
            field = value
            binding.ivIsVerified.isVisible = value
        }

    var picture: String = EMPTY
        set(value) {
            field = value
            binding.ivProfile.load(picture) {
                error(context.getDrawableCompat(R.drawable.placeholder_profile_picture)!!)
                fallback(context.getDrawableCompat(R.drawable.placeholder_profile_picture)!!)
                transformations(CircleCropTransformation())
            }
        }

    init {
        rootView
    }

    override fun getRootView(): ConstraintLayout = binding.contRoot
}
