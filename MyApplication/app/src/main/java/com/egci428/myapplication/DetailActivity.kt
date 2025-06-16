package com.egci428.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //customize back button
        // calling the action bar
       /* val actionBar = supportActionBar

        if (actionBar != null) {
            // Customize the back button
            actionBar.setHomeAsUpIndicator(R.drawable.myButton)
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true)
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    // this event will enable the back
    // function to the button on press
    /*override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("Test", "back")
                onBackPressed()
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }*/
}