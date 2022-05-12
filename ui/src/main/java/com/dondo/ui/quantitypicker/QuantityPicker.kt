package com.dondo.ui.quantitypicker

import android.content.Context
import android.content.res.TypedArray
import android.text.InputFilter
import android.text.InputType
import android.text.Spanned
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.dondo.ui.R
import com.dondo.ui.databinding.LayoutQuantityPickerBinding
import com.dondo.ui.utils.extensions.applyMargin
import com.dondo.ui.utils.extensions.getColorCompat
import com.dondo.ui.utils.extensions.getStringCompat
import com.dondo.ui.utils.extensions.setSafeOnClickListener
import timber.log.Timber

class QuantityPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    private var maxValue: Int = Integer.MAX_VALUE
    private var minValue: Int = 0
    private var oldQuantity: Int = 0

    private val binding: LayoutQuantityPickerBinding by lazy {
        LayoutQuantityPickerBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private val editableQuantity: AppCompatEditText = binding.etQuantity
    private var onQuantityChange: (quantity: Int) -> Unit = { _: Int -> }

    var quantity: Int = minValue
        set(value) {
            field = value
            editableQuantity.setText(value.toString())
            updateSideControls()
        }

    init {
        rootView
        handleAttrs(attrs)
        setListeners()
        editableQuantity.setText(quantity.toString())
        editableQuantity.filters += InputFilter.LengthFilter(8)
    }

    override fun getRootView(): LinearLayoutCompat = binding.llQuantityPickerContainer

    private fun handleAttrs(attrs: AttributeSet?) {
        val styles: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.QuantityPicker, 0, 0)

        try {
            styles.getResourceId(R.styleable.QuantityPicker_quantityPickerNextFocusDown, 0).let { nextId ->
                if (nextId != 0) {
                    editableQuantity.nextFocusDownId = nextId
                }
            }
        } finally {
            styles.recycle()
        }

        setNumericalConstraints()
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
                        oldQuantity = editableQuantity.text.toString().toInt()
                        setSelection(editableQuantity.length())
                    } else {
                        validate()
                    }

                    ivNegative.setVisibility(!hasFocus)
                    ivPositive.setVisibility(!hasFocus)
                }
            }
        }
    }

    private fun validate() {
        val newCount = if (!editableQuantity.text.isNullOrEmpty()) {
            val value = editableQuantity.text.toString().toInt()

            if (value in minValue..maxValue) value else oldQuantity
        } else minValue

        quantity = newCount
        onQuantityChange(quantity)
    }

    private fun add() {
        if (quantity != maxValue) quantity++

        onQuantityChange(quantity)
    }

    private fun subtract() {
        if (quantity != minValue) quantity--

        onQuantityChange(quantity)
    }

    fun doAfterQuantityChange(action: (quantity: Int) -> Unit) {
        onQuantityChange = action
    }

    private fun View.setVisibility(isVisible: Boolean) {
        visibility = if (isVisible) VISIBLE else GONE
    }

    private fun getColorFromCondition(condition: Boolean): Int {
        val color = if (condition) R.color.quantity_picker_action_disabled else R.color.quantity_picker_action_enable

        return getColorCompat(color)
    }

    private fun setNumericalConstraints() {
        fun setConstraints(editText: EditText) {
            val inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_CLASS_PHONE

            editText.inputType = inputType
            editText.filters =
                arrayOf(InputFilter.LengthFilter(maxValue.toString().length), InputFilterMinMax(minValue, maxValue))
        }

        binding.run {
            setConstraints(etQuantity)
        }
    }

    fun addTopMargin(topMargin: Float) {
        rootView.apply {
            applyMargin(top = topMargin)
        }
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
