package com.lucascarlos.cineticket.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.api.MyRetrofit
import com.lucascarlos.cineticket.model.Ticket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class ChooseTicketType : AppCompatActivity() {

    private var seatsList: List<String>? = null

    private var totalSeats: Int = 0

    private lateinit var quantityCounterEntire: TextView
    private lateinit var quantityCounterHalf: TextView
    private lateinit var selectedSeatCounter: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_ticket_type)

        val title = intent.getStringExtra("movieTitle")
        val posterUrl = intent.getStringExtra("posterUrl")
        val room = intent.getStringExtra("roomNumber")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val seats = intent.getStringExtra("seats")

        val movieTitle: TextView = findViewById(R.id.movie_title)
        val moviePoster: ImageView = findViewById(R.id.movie_poster)
        val btnBack: ImageView = findViewById(R.id.btn_back)
        val movieRoom: TextView = findViewById(R.id.movie_room)
        val movieDate: TextView = findViewById(R.id.movie_date)
        val movieTime: TextView = findViewById(R.id.movie_time)
        val movieSeats: TextView = findViewById(R.id.movie_seats)
        selectedSeatCounter = findViewById(R.id.selected_seat_counter)
        val seeAll: TextView = findViewById(R.id.see_all)
        val btnConfirm: MaterialButton = findViewById(R.id.btn_confirm)

        val btnMinusEntire: TextView = findViewById(R.id.btn_minus_entire)
        val btnPlusEntire: TextView = findViewById(R.id.btn_plus_entire)
        val btnMinusHalf: TextView = findViewById(R.id.btn_minus_half)
        val btnPlusHalf: TextView = findViewById(R.id.btn_plus_half)

        quantityCounterEntire = findViewById(R.id.quantity_counter_entire)
        quantityCounterHalf = findViewById(R.id.quantity_counter_half)

        seatsList = seats?.split(",")?.map { it.trim() }
        totalSeats = Integer.parseInt(seatsList?.size.toString())

        movieTitle.text = title
        Glide.with(this).load(posterUrl).into(moviePoster)
        movieDate.text = date
        movieRoom.text = room
        movieTime.text = time
        selectedSeatCounter.text = "0/${seatsList?.size}"

        if (seatsList?.size!! >= 15) {
            seeAll.visibility = View.VISIBLE
            (seatsList!!.take(15).joinToString(separator = ",") + "...").also {
                movieSeats.text = it
            }
        } else if (seatsList!!.size < 15) {
            seeAll.visibility = View.GONE
            movieSeats.text = seatsList!!.joinToString(separator = ",")
        }

        seeAll.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.txt_selected_seats)
            builder.setMessage(seatsList!!.distinct().joinToString(separator = ", "))
            builder.setPositiveButton(
                "Ok"
            ) { _, _ -> }
            val alert = builder.create()
            alert.show()
        }

        btnBack.setOnClickListener {
            this.finish()
        }

        btnMinusEntire.setOnClickListener {
            updateCounter("-", "entire")
        }

        btnPlusEntire.setOnClickListener {
            updateCounter("+", "entire")
        }

        btnMinusHalf.setOnClickListener {
            updateCounter("-", "half")
        }

        btnPlusHalf.setOnClickListener {
            updateCounter("+", "half")
        }

        btnConfirm.setOnClickListener {
            if (Integer.parseInt(quantityCounterEntire.text.toString()) + Integer.parseInt(
                    quantityCounterHalf.text.toString()
                ) == seatsList!!.size
            ) {
                val ticketData = Ticket(
                    movieTitle = title,
                    posterUrl = posterUrl,
                    movieRoom = room,
                    movieDate = date,
                    movieTime = time,
                    seats = seatsList!!
                )
                saveTicket(ticketData)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.txt_toast_select_seat_type),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun saveTicket(ticketData: Ticket) {
        val retrofit = MyRetrofit.instance
        retrofit?.postTicketApi()?.addTicket(ticketData)?.enqueue(
            object : Callback<Ticket> {
                override fun onResponse(call: Call<Ticket>, response: Response<Ticket>) {

                    val gson = Gson()
                    val jsonTicket = gson.toJson(ticketData)

                    val intent = Intent(this@ChooseTicketType, TicketDetails::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.putExtra("ticket", jsonTicket)
                    intent.putExtra("route", "newTicket")
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Ticket>, t: Throwable) {
                    Toast.makeText(this@ChooseTicketType, t.message, Toast.LENGTH_LONG).show()
                    t.message?.let { Log.e("Err", it) }
                }
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun updateCounter(operation: String, type: String) {
        if ((operation == "-") and (Integer.parseInt(quantityCounterEntire.text.toString()) + Integer.parseInt(
                quantityCounterHalf.text.toString()
            ) != 0)
        ) {
            when (type) {
                "entire" -> {
                    if (Integer.parseInt(quantityCounterEntire.text.toString()) > 0) {
                        quantityCounterEntire.text =
                            (Integer.parseInt(quantityCounterEntire.text.toString()) - 1).toString()
                    }
                }
                "half" -> {
                    if (Integer.parseInt(quantityCounterHalf.text.toString()) > 0) {
                        quantityCounterHalf.text =
                            (Integer.parseInt(quantityCounterHalf.text.toString()) - 1).toString()
                    }
                }
            }
        }

        if ((operation == "+") and (Integer.parseInt(quantityCounterEntire.text.toString()) + Integer.parseInt(
                quantityCounterHalf.text.toString()
            ) != totalSeats)
        ) {
            when (type) {
                "entire" -> {
                    quantityCounterEntire.text =
                        (Integer.parseInt(quantityCounterEntire.text.toString()) + 1).toString()
                }
                "half" -> {
                    quantityCounterHalf.text =
                        (Integer.parseInt(quantityCounterHalf.text.toString()) + 1).toString()
                }
            }
        }
        selectedSeatCounter.text = "${
            (Integer.parseInt(quantityCounterEntire.text.toString())) + (Integer.parseInt(
                quantityCounterHalf.text.toString()
            ))
        }/${seatsList?.size}"
    }
}
