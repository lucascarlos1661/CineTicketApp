package com.lucascarlos.cineticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.model.Ticket
import retrofit2.converter.gson.GsonConverterFactory

class TicketDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_details)

        val response = intent.getStringExtra("ticket")

        val gson = Gson()
        val ticket: Ticket = gson.fromJson(response, Ticket::class.java)

        val movieTitle: TextView = findViewById(R.id.movie_title)
        val moviePoster: ImageView = findViewById(R.id.movie_poster)
        val movieRoom: TextView = findViewById(R.id.movie_room)
        val movieDate: TextView = findViewById(R.id.movie_date)
        val movieTime: TextView = findViewById(R.id.movie_time)
        val movieSeats: TextView = findViewById(R.id.movie_seats)
        val btnBack: ImageView = findViewById(R.id.btn_back)

        movieTitle.text = ticket.movieTitle
        Glide.with(this).load(ticket.posterUrl).into(moviePoster)
        movieDate.text = ticket.movieDate
        movieRoom.text = ticket.movieRoom
        movieTime.text = ticket.movieTime
        movieSeats.text = ticket.seats.joinToString(separator = ",")

        btnBack.setOnClickListener{
            this.finish()
        }
    }
}