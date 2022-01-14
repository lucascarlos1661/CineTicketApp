package com.lucascarlos.cineticket.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.api.MyRetrofit
import com.lucascarlos.cineticket.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChooseSeat : AppCompatActivity() {

    lateinit var recyclerSeats: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seat)

        recyclerSeats = findViewById(R.id.recycleSeats)

        val title = intent.getStringExtra("movieTitle")
        val room = intent.getStringExtra("roomNumber")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")

        val movieTitle: TextView = findViewById(R.id.movie_title)
        val movieRoom: TextView = findViewById(R.id.movie_room)
        val movieDate: TextView = findViewById(R.id.movie_date)
        val movieTime: TextView = findViewById(R.id.movie_time)

        movieTitle.text = title
        movieRoom.text = room
        movieDate.text = date
        movieTime.text = time

        getData()

    }

    private fun getData() {
        val call: Call<List<Seats>> =
            MyRetrofit.instance?.seatsApi()?.getSeatsApi() as Call<List<Seats>>
        call.enqueue(object : Callback<List<Seats>> {

            override fun onResponse(call: Call<List<Seats>>, response: Response<List<Seats>>) {
                val apiResponse = response.body()?.toList() as List<Seats>

                recyclerSeats.layoutManager = object : LinearLayoutManager(this@ChooseSeat) {
                    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                        lp.height = height / apiResponse.size
                        return true
                    }
                }

                val adapter = SeatsAdapter(this@ChooseSeat, apiResponse)
                recyclerSeats.adapter = adapter
            }

            override fun onFailure(call: Call<List<Seats>>, t: Throwable) {
                Toast.makeText(this@ChooseSeat, t.message, Toast.LENGTH_LONG).show()
                t.message?.let { Log.e("Err", it) }
            }

        })
    }
}