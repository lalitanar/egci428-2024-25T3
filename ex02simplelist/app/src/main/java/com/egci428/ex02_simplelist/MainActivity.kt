package com.egci428.ex02_simplelist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val wordList = arrayOf("ant", "bat","cat","dog", "ant", "bat","cat","dog", "ant", "bat","cat","dog","ant", "bat","cat","dog","ant", "bat","cat","dog","ant", "bat","cat","dog","ant", "bat","cat","dog")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listView)
        val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,wordList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,wordList[position], Toast.LENGTH_SHORT).show()
        }

    }
}