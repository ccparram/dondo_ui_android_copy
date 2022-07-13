package com.dondo.demo.examples

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dondo.demo.databinding.ActivityButttonViewBinding
import com.dondo.ui.utils.extensions.viewBinding

class ButtonViewActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityButttonViewBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btPrimaryEnabled.setOnClickListener {
                Toast.makeText(this@ButtonViewActivity, "Click primary enabled", Toast.LENGTH_SHORT).show()
            }

            btSecondaryEnabled.setOnClickListener {
                Toast.makeText(this@ButtonViewActivity, "Click secondary enabled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
