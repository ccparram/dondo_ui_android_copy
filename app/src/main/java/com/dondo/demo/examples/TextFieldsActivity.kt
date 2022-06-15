package com.dondo.demo.examples

import android.os.Bundle
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
import android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE
import androidx.appcompat.app.AppCompatActivity
import com.dondo.demo.databinding.ActivityTextFieldsBinding
import com.dondo.ui.utils.extensions.viewBinding

class TextFieldsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityTextFieldsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            etMultiline.inputType = TYPE_CLASS_TEXT or TYPE_TEXT_FLAG_CAP_SENTENCES or TYPE_TEXT_FLAG_MULTI_LINE
            etDisable.text = "This is a field disable"
            etDisable.isEnabled = false
        }
    }
}
