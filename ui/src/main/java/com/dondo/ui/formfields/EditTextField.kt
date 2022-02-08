package com.dondo.ui.formfields

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
import android.util.AttributeSet
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isInvisible
import androidx.core.widget.doAfterTextChanged
import com.dondo.ui.R
import com.dondo.ui.databinding.FormFieldEditTextBinding
import com.dondo.ui.utils.Constants.EMPTY
import com.dondo.ui.utils.dpToPx
import com.dondo.ui.utils.getColorCompat
import com.dondo.ui.utils.getDrawableCompat
import com.dondo.ui.utils.getStringCompat
import com.dondo.ui.utils.isEmpty
import java.util.regex.Pattern

class EditTextField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr), FormField {

    /**
     * Set rule for component, default is false
     * @param [isRequired] value to define if component must be filled
     * */
    var isRequired = false
    private var minLength = 0
    private var maxLength = -1
    private var minLines = 1
    private var maxLines = 1
    private var inputType = TYPE_CLASS_TEXT
    lateinit var editText: AppCompatEditText

    /**
     * Set hint message for component, default is EMPTY
     * @param [hint] value to set as Hint
     * */
    var hint = EMPTY
        set(value) {
            field = value
            binding.tiContent.hint = field
        }

    /**
     * Set regex for String validation, default is null
     * @param [regex] value to set as Regex
     * */
    var regex: String? = null

    /**
     * Set text message for component, default is EditText String value
     * @param [text] value to set as Text on field
     * */
    var text
        get() = editText.text.toString()
        set(value) = editText.setText(value)

    private val binding: FormFieldEditTextBinding by lazy {
        FormFieldEditTextBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        rootView
        setup(attrs)
        doAfterTextChanged { isValid() }
    }

    override fun getRootView(): LinearLayoutCompat = binding.contRoot

    override fun setup(attrs: AttributeSet?) {
        attrs.let {
            context.theme.obtainStyledAttributes(it, R.styleable.EditTextField, 0, 0).apply {
                isRequired = getBoolean(R.styleable.EditTextField_is_required, false)
                hint = getString(R.styleable.EditTextField_hint) ?: EMPTY
                minLength = getInt(R.styleable.EditTextField_minLength, minLength)
                maxLength = getInt(R.styleable.EditTextField_maxLength, maxLength)
                minLines = getInt(R.styleable.EditTextField_minLines, minLines)
                maxLines = getInt(R.styleable.EditTextField_minLines, maxLines)
                inputType = getInt(R.styleable.EditTextField_android_inputType, TYPE_CLASS_TEXT)
                recycle()
            }
        }

        editText = binding.etContent.apply {
            minLines = minLines
            maxLines = maxLines
            inputType = inputType

            if (maxLength >= 0) {
                filters = arrayOf(InputFilter.LengthFilter(maxLength))
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

    /**
     * Transforms Edittext doAfterTextChanged action so it returns a String and not an EditText
     * @param [action] Returns a String value for the action
     * */
    fun doAfterTextChanged(action: (text: String) -> Unit) {
        this.editText.doAfterTextChanged { action(it.toString()) }
    }

    /**
     * Set error message to show
     * @param [errorMessage] String resource to set as error
     * */
    fun showError(errorMessage: String) {
        with(binding) {
            val gd = GradientDrawable().apply {
                setStroke(2, getColorCompat(R.color.red))
                cornerRadius = dpToPx(10f).toFloat()
            }

            tvErrorLabel.text = errorMessage
            tvErrorLabel.isInvisible = false
            editText.background = gd
        }
    }

    private fun hideError() {
        with(binding) {
            tvErrorLabel.isInvisible = true
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

        return when (inputType) {
            inputTypeEmail -> EMAIL_ADDRESS.matcher(editText.text.toString()).matches()
            else -> true
        }
    }

    private fun validateRegex(): Boolean {
        return regex?.let {
            if (Pattern.compile(it).matcher(text).matches()) {
                true
            } else {
                showError(String.format(getStringCompat(R.string.error_not_match_regex), minLength))
                false
            }
        } ?: true
    }
}

