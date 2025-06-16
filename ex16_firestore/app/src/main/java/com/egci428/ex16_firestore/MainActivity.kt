package com.egci428.ex16_firestore

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var submitBtn: Button
    lateinit var ratingBar: RatingBar
    lateinit var listView: ListView

    lateinit var msgList: MutableList<Message>
    lateinit var adapter: MessageAdapter

    lateinit var dataReference: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editText = findViewById(R.id.editText)
        submitBtn = findViewById(R.id.submitBtn)
        ratingBar = findViewById(R.id.ratingBar)
        listView = findViewById(R.id.listView)

        msgList = mutableListOf()
        dataReference = FirebaseFirestore.getInstance()

        submitBtn.setOnClickListener {
            submitData()
        }
        readFirestoreData()

    }

    private fun readFirestoreData() {
        var db = dataReference.collection("dataMessage")
        db.orderBy("timeStamp").get()
            .addOnSuccessListener { snapshot ->
                if(snapshot != null){
                    msgList.clear()
                    val messages = snapshot.toObjects(Message::class.java)
                    for (amessage in messages) {
                        msgList.add(amessage)
                    }
                    adapter = MessageAdapter(applicationContext, R.layout.messages, msgList)
                    listView.adapter = adapter
                }
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Fail to read messages", Toast.LENGTH_SHORT).show()
            }
    }

    private fun submitData() {
        val msg = editText.text.toString()
        if(msg.isEmpty()){
            editText.error = "Please submit a message"
            return
        }

        var db = dataReference.collection("dataMessage")
        val messageId = db.document().id
        val messageData = Message(messageId, msg, ratingBar.rating.toInt(), System.currentTimeMillis().toString())

        db.add(messageData)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Message is saved successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Fail to save message", Toast.LENGTH_SHORT).show()
            }
    }
}