package com.lucascarlos.cineticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.api.MyRetrofit
import com.lucascarlos.cineticket.model.Dates
import com.lucascarlos.cineticket.model.Days
import com.lucascarlos.cineticket.model.DaysAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BuyTicket : AppCompatActivity() {

    lateinit var recycleDates: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket)

        val movieId = intent.getStringExtra("movieId")
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


        recycleDates = findViewById(R.id.recycleDates)
        recycleDates.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        if (movieId != null) {
            getData(movieId)
        }
    }

    private fun getData(movieId: String) {
        val call: Call<List<Dates>> =
            MyRetrofit.instance?.datesApi()?.getDates(movieId) as Call<List<Dates>>
        call.enqueue(object : Callback<List<Dates>> {


            override fun onResponse(call: Call<List<Dates>>, response: Response<List<Dates>>) {

                val apiResponse = response.body()?.toList() as List<Dates>
                var daysList: List<Days> = emptyList()

                for (i in apiResponse) {
                    daysList = daysList + i.days
                }

                val adapter =
                    DaysAdapter(this@BuyTicket, daysList)
                recycleDates.adapter = adapter
            }

            override fun onFailure(call: Call<List<Dates>>, t: Throwable) {
                Toast.makeText(this@BuyTicket, t.message, Toast.LENGTH_LONG).show()
                t.message?.let { Log.e("Err", it) }
            }

        })
    }


}