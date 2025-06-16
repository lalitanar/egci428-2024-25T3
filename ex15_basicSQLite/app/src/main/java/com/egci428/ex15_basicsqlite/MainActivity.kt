package com.egci428.ex15_basicsqlite

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private  var dataSource: CommentDataSource? = null
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
        val addBtn = findViewById<Button>(R.id.addBtn)
        val commentText = findViewById<EditText>(R.id.commentTextView)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)
        val deleteText = findViewById<EditText>(R.id.delTextView)

        var comment: Comment
        var adpterSize = 0

        dataSource = CommentDataSource(this)
        dataSource!!.open()
        val values = dataSource!!.allComments

        val adapter = ArrayAdapter<Comment>(this, android.R.layout.simple_expandable_list_item_1, values)

        listView.adapter = adapter

        addBtn.setOnClickListener {
            if (commentText.text.toString() != null){
                comment = dataSource!!.createComment(commentText.text.toString())
                adapter.add(comment)
                adapter.notifyDataSetChanged()
            }
        }
        deleteBtn.setOnClickListener {

        }
    }
}