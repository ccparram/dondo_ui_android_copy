package com.dondo.ui.quantitypicker

import android.content.Context
import android.text.InputFilter
import android.text.Spanned
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.dondo.ui.R
import com.dondo.ui.databinding.LayoutQuantityPickerBinding
import com.dondo.ui.utils.Constants.EMPTY
import com.dondo.ui.utils.extensions.getColorCompat
import com.dondo.ui.utils.extensions.getStringCompat
import com.dondo.ui.utils.extensions.setSafeOnClickListener

class QuantityPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private lateinit var editText: AppCompatEditText

    // 5000 is the max stock defined in BE
    var maxValue: Int = 5000
        set(value) {
            field = value
            updateRangeFilter()
        }
    private var minValue: Int = 0
    private var oldQuantity: Int = 0

    private val binding: LayoutQuantityPickerBinding by lazy {
        LayoutQuantityPickerBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var quantity = minValue
        get() = editText.toIntOrMin()
        set(value) {
            field = value
            editText.setText(value.toString())
            updateSideControls()
        }

    init {
        rootView
        editText = binding.etQuantity
        quantity = minValue
        setupAttrs(attrs)
        setListeners()
        updateRangeFilter()
    }

    override fun getRootView(): LinearLayoutCompat = binding.llQuantityPickerContainer

    private fun setupAttrs(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.QuantityPicker, 0, 0).apply {
            try {
                getResourceId(R.styleable.QuantityPicker_quantityPickerNextFocusDown, 0).let { nextId ->
                    if (nextId != 0) {
                        editText.nextFocusDownId = nextId
                    }
                }
            } finally {
                recycle()
            }
        }

        updateSideControls()
    }

    /**
     * Set title for component
     * @param [title] String resource to set as title
     * */
    fun setTitle(@StringRes title: Int) {
        setTitle(getStringCompat(title))
    }

    /**
     * Set title for component
     * @param [title] String value to set as title
     * */
    fun setTitle(title: String) {
        binding.tvQuantityTitle.text = title
    }

    /**
     * Update visibility of the negative and positive views
     */
    private fun updateSideControls() {
        with(binding) {
            ivNegative.setStateFromCondition(quantity == minValue, ::subtract)
            ivPositive.setStateFromCondition(quantity == maxValue, ::add)
        }
    }

    private fun updateRangeFilter() {
        editText.filters = arrayOf(InputFilterMinMax(minValue, maxValue))
    }

    private fun setListeners() {
        with(binding) {
            with(etQuantity) {
                setOnFocusChangeListener { _, hasFocus ->
                    ivNegative.isVisible = !hasFocus
                    ivPositive.isVisible = !hasFocus
                }
            }
        }
    }

    private fun validateQuantity(): Int =
        if (editText.text.isNullOrEmpty()) {
            minValue
        } else {
            val newCount = editText.text.toString().toInt()
            if (newCount in minValue..maxValue) newCount else oldQuantity
        }

    private fun add() {
        if (quantity != maxValue) {
            quantity++
        }
    }

    private fun subtract() {
        if (quantity != minValue) {
            quantity--
        }
    }

    fun doAfterQuantityChange(action: (quantity: Int) -> Unit) =
        editText.doAfterTextChanged {
            action(validateQuantity())
            updateSideControls()
        }

    inner class InputFilterMinMax(private val min: Int, private val max: Int) : InputFilter {

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? =
            try {
                val input = Integer.parseInt(dest.toString() + source.toString())
                if (isInRange(min, max, input)) {
                    null
                } else {
                    EMPTY
                }
            } catch (t: NumberFormatException) {
                EMPTY
            }

        private fun isInRange(a: Int, b: Int, c: Int): Boolean = if (b > a) c in a..b else c in b..a
    }

    private fun AppCompatImageView.setStateFromCondition(condition: Boolean, action: () -> Unit) {
        with(this) {
            if (condition) {
                setColorFilter(getColorCompat(R.color.quantity_picker_action_disabled))
                setSafeOnClickListener { }
            } else {
                setColorFilter(getColorCompat(R.color.quantity_picker_action_enable))
                setSafeOnClickListener { action() }
            }
        }
    }

    private fun AppCompatEditText.toIntOrMin() =
        try {
            this.text.toString().toInt()
        } catch (t: NumberFormatException) {
            minValue
        }
}
