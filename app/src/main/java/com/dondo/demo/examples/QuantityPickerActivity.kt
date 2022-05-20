package com.dondo.demo.examples

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dondo.demo.databinding.ActivityQuantityPickerBinding
import com.dondo.ui.utils.extensions.viewBinding

class QuantityPickerActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityQuantityPickerBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.qp.doAfterQuantityChange = {
            Toast.makeText(this, "New value: $it", Toast.LENGTH_SHORT).show()
        }
    }
}
