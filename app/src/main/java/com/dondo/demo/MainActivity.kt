package com.dondo.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dondo.mediasliderview.databinding.ActivityMainBinding
import com.dondo.ui.viewBinding

class MainActivity : AppCompatActivity() {

	private val binding by viewBinding(ActivityMainBinding::inflate)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
	}
}
