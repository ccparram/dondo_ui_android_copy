package com.dondo.demo.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dondo.demo.R

class TestBaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
    }

    companion object {
        var layout: Int = R.layout.activity_test_base
    }
}
