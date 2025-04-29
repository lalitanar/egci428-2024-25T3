package com.egci428.ex0

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView)
        val resultText = findViewById<TextView>(R.id.resultText)
        val addButton = findViewById<Button>(R.id.addButton)
        val minusButton = findViewById<Button>(R.id.minusButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val inputText1 = findViewById<EditText>(R.id.inputText1)
        val inputText2 = findViewById<EditText>(R.id.inputText2)



        addButton.setOnClickListener {
            var input1 = inputText1.text.toString().toInt()
            var input2 = inputText2.text.toString().toInt()
            resultText.text = (input1+input2).toString()
        }

        minusButton.setOnClickListener {
            var input1 = inputText1.text.toString().toInt()
            var input2 = inputText2.text.toString().toInt()
            resultText.text = (input1-input2).toString()
        }

        nextButton.setOnClickListener{
            val intent = Intent(this,DetailActivity::class.java)
            startActivity(intent)
        }

    }
}