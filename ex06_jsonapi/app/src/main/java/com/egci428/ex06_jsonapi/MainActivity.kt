package com.egci428.ex06_jsonapi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class MainActivity : AppCompatActivity() {
    private val jsonURL = "https://egci428-d78f6-default-rtdb.firebaseio.com/movies/2.json"
    private val client = OkHttpClient()
    lateinit var jsonBtn: Button
    lateinit var movieNameText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        jsonBtn = findViewById(R.id.jsonBtn)
        movieNameText = findViewById(R.id.movieNameText)

        jsonBtn.setOnClickListener {
            fetchJson()
        }
    }

    fun fetchJson() {
        val request = Request.Builder().url(jsonURL).build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val body = response.body.string()
                    val gson = GsonBuilder().create()
                    val aMovie = gson.fromJson(body, Movie::class.java)
                    movieNameText.text = aMovie.name
                }
            }

        })
    }
}