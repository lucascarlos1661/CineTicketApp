package com.lucascarlos.cineticket.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascarlos.cineticket.R

class MoviesAdapter(private val context: Context, private val moviesList: List<Movies>) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = moviesList[position]
        holder.movieTitle.text = item.title
        holder.movieRunTime.text = item.runTime
        holder.movieGenre.text = item.genre
        holder.movieRate.text = item.rating
        holder.movieAge.text = item.age
        Glide.with(context).load(item.posterUrl).into(holder.movieBanner)
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieBanner: ImageView = itemView.findViewById(R.id.movie_banner)
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val movieRunTime: TextView = itemView.findViewById(R.id.movie_run_time)
    val movieGenre: TextView = itemView.findViewById(R.id.movie_genre)
    val movieRate: TextView = itemView.findViewById(R.id.movie_rate)
    val movieAge: TextView = itemView.findViewById(R.id.movie_age)
}

