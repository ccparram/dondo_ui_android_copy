package com.dondo.ui.quantitypicker

import android.content.Context
import android.text.InputFilter
import android.text.Spanned
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import com.dondo.ui.R
import com.dondo.ui.databinding.LayoutQuantityPickerBinding
import com.dondo.ui.utils.extensions.getColorCompat
import com.dondo.ui.utils.extensions.getStringCompat
import com.dondo.ui.utils.extensions.setSafeOnClickListener
import timber.log.Timber

class QuantityPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var maxValue: Int = 99999999
    private var minValue: Int = 0
    private var oldQuantity: Int = 0

    private val binding: LayoutQuantityPickerBinding by lazy {
        LayoutQuantityPickerBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private var onQuantityChange: (quantity: Int) -> Unit = { _: Int -> }

    var quantity: Int = minValue
        set(value) {
            field = value
            binding.etQuantity.setText(value.toString())
            updateSideControls()
        }

    init {
        rootView
        setupAttrs(attrs)
        setListeners()
        binding.etQuantity.setText(quantity.toString())
        binding.etQuantity.filters +=
            arrayOf(InputFilter.LengthFilter(maxValue.toString().length), InputFilterMinMax(minValue, maxValue))
    }

    override fun getRootView(): LinearLayoutCompat = binding.llQuantityPickerContainer

    private fun setupAttrs(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.QuantityPicker, 0, 0).apply {
            try {
                getResourceId(R.styleable.QuantityPicker_quantityPickerNextFocusDown, 0).let { nextId ->
                    if (nextId != 0) {
                        binding.etQuantity.nextFocusDownId = nextId
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
            ivNegative.setColorFilter(getColorFromCondition(quantity == minValue))
            ivPositive.setColorFilter(getColorFromCondition(quantity == maxValue))
        }
    }

    private fun setListeners() {
        with(binding) {
            ivNegative.setSafeOnClickListener {
                subtract()
            }

            ivPositive.setSafeOnClickListener {
                add()
            }

            with(etQuantity) {
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        oldQuantity = text.toString().toInt()
                        setSelection(length())
                    } else {
                        validate()
                    }

                    ivNegative.isVisible = !hasFocus
                    ivPositive.isVisible = !hasFocus
                }
            }
        }
    }

    private fun validate() {
        val newCount = if (!binding.etQuantity.text.isNullOrEmpty()) {
            val value = binding.etQuantity.text.toString().toInt()

            if (value in minValue..maxValue) value else oldQuantity
        } else minValue

        quantity = newCount
        onQuantityChange(quantity)
    }

    private fun add() {
        if (quantity != maxValue) {
            quantity++
        }

        onQuantityChange(quantity)
    }

    private fun subtract() {
        if (quantity != minValue) {
            quantity--
        }

        onQuantityChange(quantity)
    }

    fun doAfterQuantityChange(action: (quantity: Int) -> Unit) {
        onQuantityChange = action
    }

    private fun getColorFromCondition(condition: Boolean): Int {
        val color = if (condition) R.color.quantity_picker_action_disabled else R.color.quantity_picker_action_enable

        return getColorCompat(color)
    }

    inner class InputFilterMinMax(private val min: Int, private val max: Int) : InputFilter {

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence {
            try {
                val input = (dest.toString() + source.toString()).toInt()
                if (input in min..max) return source.toString()
            } catch (t: NumberFormatException) {
                Timber.e(t)
            }
            return minValue.toString()
        }
    }
}
