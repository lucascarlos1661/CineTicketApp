package com.lucascarlos.cineticket.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.api.MyRetrofit
import com.lucascarlos.cineticket.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BuyTicket : AppCompatActivity() {

    lateinit var recycleDates: RecyclerView
    lateinit var recycleRooms: RecyclerView
    lateinit var backButton: ImageView
    lateinit var readMore: TextView
    private var daysList: List<Days> = emptyList()
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_ticket)

        val movieId = intent.getStringExtra("movieId")
        val bannerUrl = intent.getStringExtra("bannerUrl")
        val storyLine = intent.getStringExtra("movieStoryLine")
        val posterUrl = intent.getStringExtra("posterUrl")
        val title = intent.getStringExtra("movieTitle")
        val rate = intent.getStringExtra("movieRate")
        val age = intent.getStringExtra("movieAge")
        val runTime = intent.getStringExtra("runTime")
        val genre = intent.getStringExtra("movieGenre")

        val movieBanner: ImageView = findViewById(R.id.movie_banner)
        val movieTitle: TextView = findViewById(R.id.movie_title)
        val movieStoryLine: TextView = findViewById(R.id.movie_story_line)
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

        (storyLine?.take(83) + " ...").also { movieStoryLine.text = it }

        backButton = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            this.finish()
        }

        recycleDates = findViewById(R.id.recycleDates)
        recycleDates.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        if (movieId != null) {
            getData(movieId)
        }

        readMore = findViewById(R.id.read_more)

        readMore.setOnClickListener {
            val intent = Intent(this, MovieDetails::class.java)
            intent.putExtra("bannerUrl", bannerUrl)
            intent.putExtra("posterUrl", posterUrl)
            intent.putExtra("movieTitle", title)
            intent.putExtra("movieRate", rate)
            intent.putExtra("movieAge", age)
            intent.putExtra("movieStoryLine", storyLine)
            intent.putExtra("runTime", runTime)
            intent.putExtra("movieGenre", genre)
            this.startActivity(intent)
        }

        val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                selectedDate = intent.getStringExtra("selectedDate").toString()
                val fullDate: String = intent.getStringExtra("fullDate").toString()

                val filteredDate = daysList.filter { it.date == selectedDate }

                recycleRooms = findViewById(R.id.recyclerRooms)
                recycleRooms.layoutManager = LinearLayoutManager(this@BuyTicket)

                var roomsList: List<Rooms> = emptyList()
                for (i in filteredDate) {
                    roomsList = roomsList + i.rooms
                }

                val adapter = title?.let { RoomAdapter(this@BuyTicket, roomsList, fullDate, it) }
                recycleRooms.adapter = adapter
            }
        }
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mMessageReceiver, IntentFilter("message_subject_intent"))
    }

    private fun getData(movieId: String) {
        val call: Call<List<Dates>> =
            MyRetrofit.instance?.datesApi()?.getDates(movieId) as Call<List<Dates>>
        call.enqueue(object : Callback<List<Dates>> {

            override fun onResponse(call: Call<List<Dates>>, response: Response<List<Dates>>) {
                val apiResponse = response.body()?.toList() as List<Dates>

                for (i in apiResponse) {
                    daysList = daysList + i.days
                }

                val adapter = DaysAdapter(this@BuyTicket, daysList)
                recycleDates.adapter = adapter
            }

            override fun onFailure(call: Call<List<Dates>>, t: Throwable) {
                Toast.makeText(this@BuyTicket, t.message, Toast.LENGTH_LONG).show()
                t.message?.let { Log.e("Err", it) }
            }

        })
    }
}