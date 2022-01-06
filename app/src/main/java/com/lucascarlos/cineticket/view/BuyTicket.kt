package com.lucascarlos.cineticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.lucascarlos.cineticket.R

class BuyTicket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket)

        val bannerUrl = intent.getStringExtra("bannerUrl")
        val posterUrl = intent.getStringExtra("posterUrl")
        val title = intent.getStringExtra("movieTitle")
        val rate = intent.getStringExtra("movieRate")
        val runTime = intent.getStringExtra("runTime")
        val genre = intent.getStringExtra("movieGenre")

        val movieBanner: ImageView = findViewById(R.id.movie_banner)
        val movieTitle: TextView = findViewById(R.id.movie_title)
        val moviePoster: ImageView = findViewById(R.id.movie_poster)
        val movieRate: TextView = findViewById(R.id.movie_rate)
        val movieRunTime: TextView = findViewById(R.id.movie_run_time)
        val movieGenre: TextView = findViewById(R.id.movie_genre)

        Glide.with(this).load(bannerUrl).into(movieBanner)
        Glide.with(this).load(posterUrl).into(moviePoster)
        movieTitle.text = title
        movieRunTime.text = runTime
        movieRate.text = rate
        movieGenre.text = genre

    }
}