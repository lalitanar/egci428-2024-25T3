package com.egci428.ex24_emailauth

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val TAG = "EmailAuth"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signInBtn = findViewById<Button>(R.id.signInBtn)
        val signOutBtn = findViewById<Button>(R.id.signOutBtn)
        val createUserBtn = findViewById<Button>(R.id.createUserBtn)

        auth = Firebase.auth

        signInBtn.setOnClickListener {
            signIn("egci428@example.com","12345678")
        }

        signOutBtn.setOnClickListener {
            signOut()
        }

        createUserBtn.setOnClickListener {
            createAccount("newuser@example.com","12345678")
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            //reload()
        }
    }

    private fun createAccount(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                task ->
                if(task.isSuccessful){
                    Log.d(TAG, "createUserWithEamil: Success")
                    val user = auth.currentUser
                } else {
                    Log.d(TAG, "createUserWithEamil: Failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                task ->
                if(task.isSuccessful){
                    Log.d(TAG,"signInithEmail: Success")
                    val user = auth.currentUser

                } else {
                    Log.d(TAG, "signInWithEmail: Failure", task.exception)
                    when(task.exception) {
                        is FirebaseNetworkException -> {
                            Toast.makeText(baseContext, "Network error. Please check your connection and try again",
                                Toast.LENGTH_SHORT).show()
                        }
                        is FirebaseAuthException -> {
                            val errorMsg = task.exception!!.message
                            Log.e(TAG, "Firebase Auth Error: $errorMsg")
                            Toast.makeText(baseContext, "Authentication failed: ${errorMsg}",
                                Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(baseContext, "Authentication failed: ${task.exception!!.message}", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
    }
    private fun signOut(){
        Firebase.auth.signOut()
    }
}