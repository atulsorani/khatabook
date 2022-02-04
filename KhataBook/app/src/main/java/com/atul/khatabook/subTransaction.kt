package com.atul.khatabook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class subTransaction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar!!.hide()
        setContentView(R.layout.activity_sub_transaction)
    }
}