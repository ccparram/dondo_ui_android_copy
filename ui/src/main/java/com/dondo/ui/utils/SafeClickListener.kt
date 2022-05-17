package com.dondo.ui.utils

import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.dondo.ui.utils.Constants.DELAY_PREVENT_TWO_CLICKS
import timber.log.Timber

class SafeClickListener(
    private val onSafeClick: (View) -> Unit,
    private val param: String? = null
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < DELAY_PREVENT_TWO_CLICKS) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        view?.let {
            trackOnClickView(view)
            onSafeClick(it)
        }
    }

    private fun trackOnClickView(view: View) {
        val resourceEntryName =
            try {
                view.resources.getResourceEntryName(view.id)
            } catch (t: Throwable) {
                when (view) {
                    is Button -> view.text
                    is TextView -> view.text
                    else -> throw IllegalArgumentException("View is not supported, consider adding it.")
                }
            }
        val message = StringBuilder("click_view: $resourceEntryName")
        param?.let { message.append(" - id: $it") }
        Timber.d(message.toString())
    }
}
