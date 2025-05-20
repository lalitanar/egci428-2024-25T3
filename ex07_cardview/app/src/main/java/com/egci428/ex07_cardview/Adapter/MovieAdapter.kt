package com.egci428.ex07_cardview.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egci428.ex07_cardview.Model.Movie
import com.egci428.ex07_cardview.R

/*
* Created by Lalita N. on 20/5/25
*/

class MovieAdapter(private val movieObject:List<Movie>):RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.txtName.text = movieObject[position].name
        holder.txtYear.text= movieObject[position].year
        holder.txtStar.text = movieObject[position].star
    }

    override fun getItemCount(): Int {
        return movieObject.size
    }

}

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var txtName = itemView.findViewById<TextView>(R.id.txtName)
    var txtYear = itemView.findViewById<TextView>(R.id.txtYear)
    var txtStar = itemView.findViewById<TextView>(R.id.txtStar)
}
