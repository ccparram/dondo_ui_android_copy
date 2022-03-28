package com.dondo.demo.examples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dondo.demo.databinding.ActivityTextFieldsBinding
import com.dondo.ui.utils.viewBinding

class TextFieldsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityTextFieldsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.e.isRequired
    }
}
