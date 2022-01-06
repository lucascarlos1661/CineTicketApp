package com.lucascarlos.cineticket.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.view.BuyTicket

class MoviesAdapter(private val context: Context, private val moviesList: List<Movies>) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = moviesList[position]
        holder.movieTitle.text = item.title
        holder.movieRunTime.text = item.runTime
        holder.movieGenre.text = item.genre
        holder.movieRate.text = item.rating
        holder.movieAge.text = item.age
        Glide.with(context).load(item.posterUrl).into(holder.movieBanner)

        holder.btnSynopsis.setOnClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle(item.title)
                .setMessage(item.storyLine)
                .setPositiveButton("Ok") { _, _ -> }
                .show()
        }

        val rooms: List<Rooms> = item.rooms
        var roomsAudio = mutableListOf<String>()
        var roomsSchedules: List<String> = emptyList()

        for (i in rooms) {
            roomsAudio.add(i.audio)
            roomsSchedules = roomsSchedules + i.schedules
        }

        holder.movieSchedules.text = roomsSchedules.distinct().joinToString(separator = ", ")
        holder.movieAudio.text = roomsAudio.distinct().joinToString(separator = ", ")

        holder.btnBuyTicket.setOnClickListener {
            val intent = Intent(context, BuyTicket::class.java)
            intent.putExtra("bannerUrl", item.bannerUrl)
            intent.putExtra("posterUrl", item.posterUrl)
            intent.putExtra("movieTitle", item.title)
            intent.putExtra("movieRate", item.rating)
            intent.putExtra("runTime", item.runTime)
            intent.putExtra("movieGenre", item.genre)
            context.startActivity(intent)
        }
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieBanner: ImageView = itemView.findViewById(R.id.movie_banner)
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val movieRunTime: TextView = itemView.findViewById(R.id.movie_run_time)
    val movieGenre: TextView = itemView.findViewById(R.id.movie_genre)
    val movieRate: TextView = itemView.findViewById(R.id.movie_rate)
    val movieAge: TextView = itemView.findViewById(R.id.movie_age)
    val btnSynopsis: MaterialButton = itemView.findViewById((R.id.movie_story_line))
    val movieSchedules: TextView = itemView.findViewById(R.id.movie_schedules)
    val movieAudio: TextView = itemView.findViewById(R.id.movie_audio)
    val btnBuyTicket: Button = itemView.findViewById(R.id.btn_buy_ticket)
}

