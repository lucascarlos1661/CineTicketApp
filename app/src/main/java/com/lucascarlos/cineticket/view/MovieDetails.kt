package com.lucascarlos.cineticket.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.lucascarlos.cineticket.R

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        ContextCompat.getColor(this, R.color.black).also { window.statusBarColor = it }
        WindowInsetsControllerCompat(window, View(this)).isAppearanceLightStatusBars = false

        val bannerUrl = intent.getStringExtra("bannerUrl")
        val storyLine = intent.getStringExtra("movieStoryLine")
        val title = intent.getStringExtra("movieTitle")
        val releaseDate = intent.getStringExtra("movieReleaseDate")
        val age = intent.getStringExtra("movieAge")
        val runTime = intent.getStringExtra("runTime")
        val genre = intent.getStringExtra("movieGenre")

        val movieBanner: ImageView = findViewById(R.id.movie_banner)
        val movieTitle: TextView = findViewById(R.id.movie_title)
        val txtReleaseDate: TextView = findViewById(R.id.release_date)
        val movieReleaseDate: TextView = findViewById(R.id.movie_release_date)
        val movieStoryLine: TextView = findViewById(R.id.movie_story_line)
        val movieAge: TextView = findViewById(R.id.movie_age)
        val movieRunTime: TextView = findViewById(R.id.movie_run_time)
        val movieGenre: TextView = findViewById(R.id.movie_genre)
        val backButton: ImageView = findViewById(R.id.btn_back)

        if (releaseDate != null) {
            txtReleaseDate.visibility = View.VISIBLE
            movieReleaseDate.visibility = View.VISIBLE
            movieReleaseDate.text = releaseDate
        }

        backButton.setOnClickListener {
            this.finish()
        }

        Glide.with(this).load(bannerUrl).into(movieBanner)
        movieTitle.text = title
        movieStoryLine.text = storyLine
        movieRunTime.text = runTime
        movieAge.text = age
        movieGenre.text = genre

    }
}