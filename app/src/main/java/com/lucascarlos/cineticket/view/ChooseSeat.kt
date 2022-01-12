package com.lucascarlos.cineticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
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
        recyclerSeats.layoutManager = GridLayoutManager(this, 3)
        getData()
    }

    private fun getData() {
        val call: Call<List<Seats>> =
            MyRetrofit.instance?.seatsApi()?.getSeatsApi() as Call<List<Seats>>
        call.enqueue(object : Callback<List<Seats>> {

            override fun onResponse(call: Call<List<Seats>>, response: Response<List<Seats>>) {
                val apiResponse = response.body()?.toList() as List<Seats>

                var seatsList: List<String> = emptyList()

                for (i in apiResponse) {
                    seatsList = seatsList + i.seat
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