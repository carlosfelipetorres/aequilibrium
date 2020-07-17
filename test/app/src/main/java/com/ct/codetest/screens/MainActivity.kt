package com.ct.codetest.screens

import android.os.Bundle
import com.ct.codetest.R
import com.ct.codetest.platform.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
