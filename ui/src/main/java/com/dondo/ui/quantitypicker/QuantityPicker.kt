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
        get() = binding.etQuantity.toIntOrMin()
        set(value) {
            field = value
            binding.etQuantity.setText(value.toString())
            updateSideControls()
        }

    init {
        rootView
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
            ivNegative.setStateFromCondition(quantity == minValue)
            ivPositive.setStateFromCondition(quantity == maxValue)
        }
    }

    private fun updateRangeFilter() {
        binding.etQuantity.filters = arrayOf(InputFilterMinMax(minValue, maxValue))
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
                        oldQuantity = text.toString().takeUnless { it.isEmpty() }?.toInt() ?: minValue
                        setSelection(length())
                    } else {
                        setText(validateQuantity().toString())
                        updateSideControls()
                    }

                    ivNegative.isVisible = !hasFocus
                    ivPositive.isVisible = !hasFocus
                }
            }
        }
    }

    private fun validateQuantity() : Int =
        if (!binding.etQuantity.text.isNullOrEmpty()) {
            val newCount = binding.etQuantity.text.toString().toInt()

            if (newCount in minValue..maxValue) newCount else oldQuantity
        } else minValue

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
        binding.etQuantity.doAfterTextChanged { action(validateQuantity()) }

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

        private fun isInRange(a:Int, b:Int, c:Int):Boolean = if (b > a) c in a..b else c in b..a
    }

    private fun AppCompatImageView.setStateFromCondition(condition: Boolean) {
       with(this) {
           isClickable = if (condition) {
               setColorFilter(getColorCompat(R.color.quantity_picker_action_disabled))
               false
           } else {
               setColorFilter(getColorCompat(R.color.quantity_picker_action_enable))
               true
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
