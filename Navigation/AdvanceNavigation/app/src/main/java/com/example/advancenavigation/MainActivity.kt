package com.example.advancenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//using the parameterized constructor is the same as using the default constructor
class MainActivity : AppCompatActivity(R.layout.activity_main)

/**class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}**/

