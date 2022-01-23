package com.lucascarlos.cineticket.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.view.BuyTicket
import com.lucascarlos.cineticket.view.MovieDetails

class MoviesAdapter(
    private val context: Context,
    private val moviesList: List<Movies>,
    private val route: String?,
) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = moviesList[position]
        holder.movieTitle.text = item.title
        Glide.with(context).load(item.posterUrl).into(holder.movieBanner)

        if (route != null) {
            holder.movieReleaseDate.visibility = View.VISIBLE
            holder.movieReleaseDate.text = item.releaseDate
        }

        holder.movieCard.setOnClickListener {

            if (route != null) {
                val intent = Intent(context, MovieDetails::class.java)
                intent.putExtra("bannerUrl", item.bannerUrl)
                intent.putExtra("posterUrl", item.posterUrl)
                intent.putExtra("movieTitle", item.title)
                intent.putExtra("movieReleaseDate", item.releaseDate)
                intent.putExtra("movieRate", item.rating)
                intent.putExtra("movieAge", item.age)
                intent.putExtra("movieStoryLine", item.storyLine)
                intent.putExtra("runTime", item.runTime)
                intent.putExtra("movieGenre", item.genre)
                context.startActivity(intent)

            } else {

                val intent = Intent(context, BuyTicket::class.java)
                intent.putExtra("movieId", item.id)
                intent.putExtra("bannerUrl", item.bannerUrl)
                intent.putExtra("posterUrl", item.posterUrl)
                intent.putExtra("movieTitle", item.title)
                intent.putExtra("movieRate", item.rating)
                intent.putExtra("movieAge", item.age)
                intent.putExtra("movieStoryLine", item.storyLine)
                intent.putExtra("runTime", item.runTime)
                intent.putExtra("movieGenre", item.genre)
                context.startActivity(intent)
            }
        }
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieBanner: ImageView = itemView.findViewById(R.id.movie_banner)
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val movieCard: CardView = itemView.findViewById(R.id.card_movie)
    val movieReleaseDate: TextView = itemView.findViewById(R.id.movie_release_date)
}

