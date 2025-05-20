package com.egci428.ex07_cardview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egci428.ex07_cardview.Adapter.MovieAdapter
import com.egci428.ex07_cardview.Model.Movie

class MainActivity : AppCompatActivity() {
    private val jsonURL = "https://egci428-d78f6-default-rtdb.firebaseio.com/movies.json"
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager
        loadJson()
    }

    private fun loadJson() {
        var movieList: List<Movie> = listOf(Movie("a","b","c","d"),Movie("a","b","c","d"))
        val adapter = MovieAdapter(movieList)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}