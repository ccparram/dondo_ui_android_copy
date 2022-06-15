package com.dondo.ui.formfields

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
import android.util.AttributeSet
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.dondo.ui.R
import com.dondo.ui.databinding.FormFieldEditTextBinding
import com.dondo.ui.utils.Constants.EMPTY
import com.dondo.ui.utils.extensions.dpToPx
import com.dondo.ui.utils.extensions.getColorCompat
import com.dondo.ui.utils.extensions.getColorStateListCompat
import com.dondo.ui.utils.extensions.getDrawableCompat
import com.dondo.ui.utils.extensions.getStringCompat
import com.dondo.ui.utils.extensions.isEmpty
import java.util.regex.Pattern

class EditTextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr), FormField {

    private val binding: FormFieldEditTextBinding by lazy {
        FormFieldEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var isRequired = false
    var minLength = 0
    private var minLines = 1
    var editText: AppCompatEditText = binding.etContent

    var inputType = TYPE_CLASS_TEXT
        set(value) {
            field = value
            editText.inputType = field
        }

    var maxLength = -1
        set(value) {
            field = value
            val maxLengthFilter = InputFilter.LengthFilter(value)

            if (value > 0) {
                editText.filters = arrayOf(maxLengthFilter)
            } else {
                val filters = editText.filters.filterNot { it == maxLengthFilter }.toTypedArray()

                editText.filters = filters
            }
        }

    var title = EMPTY
        set(value) {
            field = value
            binding.tilContent.hint = field
        }

    var placeholder = EMPTY
        set(value) {
            field = value
            binding.tilContent.placeholderText = field
        }

    var regex: String? = null
    var regexErrorMessage: String = getStringCompat(R.string.error_not_match_regex)
    var text
        get() = editText.text.toString()
        set(value) = editText.setText(value)

    override fun isEnabled(): Boolean = editText.isEnabled

    override fun setEnabled(enabled: Boolean) {
        editText.isEnabled = enabled
    }

    init {
        rootView
        setupAttrs(attrs)
        doAfterTextChanged { isValid() }
    }

    override fun getRootView(): LinearLayoutCompat = binding.contRoot

    private fun setupAttrs(attrs: AttributeSet?) {
        attrs.let {
            context.theme.obtainStyledAttributes(it, R.styleable.EditTextField, 0, 0).apply {
                isEnabled = getBoolean(R.styleable.EditTextField_android_enabled, true)
                isRequired = getBoolean(R.styleable.EditTextField_is_required, false)
                title = getString(R.styleable.EditTextField_hint) ?: EMPTY
                placeholder = getString(R.styleable.EditTextField_placeholder) ?: EMPTY
                minLength = getInt(R.styleable.EditTextField_minLength, minLength)
                maxLength = getInt(R.styleable.EditTextField_maxLength, maxLength)
                minLines = getInt(R.styleable.EditTextField_minLines, minLines)
                inputType = getInt(R.styleable.EditTextField_android_inputType, TYPE_CLASS_TEXT)

                editText.minLines = minLines
                recycle()
            }
        }
    }

    override fun isValid(): Boolean =
        if (validateIsRequired() && validateMinLength() && validateInputType() && validateRegex()) {
            hideError()
            true
        } else {
            false
        }

    fun doAfterTextChanged(action: (text: String) -> Unit) = editText.doAfterTextChanged { action(it.toString()) }

    fun showErrorField(errorMessage: String) {
        showError(errorMessage)
    }

    fun hideErrorField() {
        hideError()
    }

    private fun showError(errorMessage: String) {
        with(binding) {
            val gd = GradientDrawable().apply {
                setStroke(2, getColorCompat(R.color.red))
                cornerRadius = dpToPx(10f).toFloat()
                color = getColorStateListCompat(R.color.elevation_1)
            }

            tvErrorLabel.text = errorMessage
            tvErrorLabel.isVisible = true
            editText.background = gd
        }
    }

    private fun hideError() {
        with(binding) {
            tvErrorLabel.isVisible = false
            editText.background = getDrawableCompat(R.drawable.text_field_states)
        }
    }

    private fun validateIsRequired(): Boolean =
        if (isRequired && editText.isEmpty()) {
            showError(getStringCompat(R.string.error_field_required))
            false
        } else {
            true
        }

    private fun validateMinLength(): Boolean =
        if (editText.length() >= minLength) {
            true
        } else {
            showError(String.format(getStringCompat(R.string.error_minimum_length), minLength))
            false
        }

    private fun validateInputType(): Boolean {
        val inputTypeEmail = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        return if (inputType == inputTypeEmail) {
            EMAIL_ADDRESS.matcher(editText.text.toString()).matches()
        } else {
            true
        }
    }

    private fun validateRegex(): Boolean = regex?.let {
        if (Pattern.compile(it).matcher(text).matches()) {
            true
        } else {
            showError(regexErrorMessage)
            false
        }
    } ?: true

    fun validateEqualTo(
        editText: EditTextField,
        editTextToCompare: EditTextField,
        @StringRes error: Int
    ): Boolean = if (editText.text == editTextToCompare.text) {
        editText.showError(getStringCompat(error))
        editTextToCompare.showError(getStringCompat(error))
        true
    } else {
        false
    }
}
