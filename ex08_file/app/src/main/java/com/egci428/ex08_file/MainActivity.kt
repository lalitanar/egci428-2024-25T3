package com.egci428.ex08_file

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private var inputText : EditText? = null
    private var resultText : EditText? = null

    private val file = "mydata.txt"
    lateinit var adapter: ArrayAdapter<String>
    private var resultList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inputText = findViewById(R.id.inputText)
        resultText = findViewById(R.id.resultText)
        val listView = findViewById<ListView>(R.id.listView)

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, resultList)
        listView.adapter = adapter

    }

    fun save(view: View){
        var data = inputText!!.text.toString()

        try {
            val fOut = openFileOutput(file, Context.MODE_PRIVATE)
            fOut.write(data.toByteArray())
            fOut.close()
            Toast.makeText(baseContext, "Data has been saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun read(view: View){
        try {
            val fIn = openFileInput(file)
            val mFile = InputStreamReader(fIn)
            val br = BufferedReader(mFile)
            var line = br.readLine()
            br.close()
            mFile.close()
            fIn.close()

            resultText?.setText(line)
            resultList.add(line)
            adapter.notifyDataSetChanged()

            Toast.makeText(baseContext, "reading data from file", Toast.LENGTH_SHORT).show()

        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}





