package com.dondo.ui.button

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER_HORIZONTAL
import androidx.appcompat.widget.AppCompatButton
import com.dondo.ui.R
import com.dondo.ui.button.ButtonState.PRIMARY_DISABLED
import com.dondo.ui.button.ButtonState.PRIMARY_ENABLED
import com.dondo.ui.button.ButtonState.SECONDARY_DISABLED
import com.dondo.ui.button.ButtonState.SECONDARY_ENABLED
import com.dondo.ui.utils.extensions.getColorCompat
import com.dondo.ui.utils.extensions.getDrawableCompat

class ButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    var state: ButtonState = PRIMARY_ENABLED
        set(value) {
            field = value
            setButtonState(field)
        }

    init {
        rootView
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        attrs.let {
            context.theme.obtainStyledAttributes(it, R.styleable.ButtonView, 0, 0)
                .apply {
                    state = ButtonState.getByValue(getInt(R.styleable.ButtonView_state, PRIMARY_ENABLED.value))
                    recycle()
                }
        }

        gravity = CENTER_HORIZONTAL
        val verticalPadding = resources.getDimensionPixelSize(R.dimen.padding_normal_50)
        val horizontalPadding = resources.getDimensionPixelSize(R.dimen.padding_normal_50)
        setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        setTextAppearance(R.style.TextAppearanceButton)
    }

    private fun setButtonState(state: ButtonState) {
        when (state) {
            PRIMARY_ENABLED -> {
                background = getDrawableCompat(R.drawable.bg_button_primary_enabled)
                setTextColor(getColorCompat(R.color.text_button_primary_enabled))
                isEnabled = true
            }
            PRIMARY_DISABLED -> {
                background = getDrawableCompat(R.drawable.bg_button_primary_disabled)
                setTextColor(getColorCompat(R.color.text_button_disabled))
                isEnabled = false
            }
            SECONDARY_ENABLED -> {
                this.background = getDrawableCompat(R.drawable.bg_button_secondary_enabled)
                setTextColor(getColorCompat(R.color.text_button_secondary_enabled))
                isEnabled = true
            }
            SECONDARY_DISABLED -> {
                this.background = getDrawableCompat(R.drawable.bg_button_secondary_disabled)
                setTextColor(getColorCompat(R.color.text_button_disabled))
                isEnabled = false
            }
        }
    }
}
