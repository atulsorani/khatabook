package com.atul.khatabook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class spleshscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var handler: Handler

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spleshscreen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}