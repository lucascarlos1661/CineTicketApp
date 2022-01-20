package com.lucascarlos.cineticket.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lucascarlos.cineticket.R

class ChooseTicketType : AppCompatActivity() {

    private var seatsList: List<String>? = null

    private var totalSeats: Int = 0

    private lateinit var quantityCounterEntire: TextView
    private lateinit var quantityCounterHalf: TextView

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
        val movieRoom: TextView = findViewById(R.id.movie_room)
        val movieDate: TextView = findViewById(R.id.movie_date)
        val movieTime: TextView = findViewById(R.id.movie_time)
        val movieSeats: TextView = findViewById(R.id.movie_seats)

        val btnMinusEntire: TextView = findViewById(R.id.btn_minus_entire)
        val btnPlusEntire: TextView = findViewById(R.id.btn_plus_entire)
        val btnMinusHalf: TextView = findViewById(R.id.btn_minus_half)
        val btnPlusHalf: TextView = findViewById(R.id.btn_plus_half)

        quantityCounterEntire = findViewById(R.id.quantity_counter_entire)
        quantityCounterHalf = findViewById(R.id.quantity_counter_half)


        seatsList = seats?.split(",")?.map { it.trim() }
        totalSeats = Integer.parseInt(seatsList?.size.toString())

        val seeAll: TextView = findViewById(R.id.see_all)

        movieTitle.text = title
        Glide.with(this).load(posterUrl).into(moviePoster)
        movieDate.text = date
        movieRoom.text = room
        movieTime.text = time

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

        btnMinusEntire.setOnClickListener {
            updateCounter("-", "entire")
//            quantity = Integer.parseInt(quantityCounter.text as String)
//            quantityCounter.text = (quantity - 1).toString()
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

    }

    private fun updateCounter(operation: String, type: String) {
        if ((operation == "-") and (Integer.parseInt(quantityCounterEntire.text.toString()) + Integer.parseInt(
                quantityCounterHalf.text.toString()
            ) != 0)
        ) {
            when (type) {
                "entire" -> {
                    quantityCounterEntire.text =
                        (Integer.parseInt(quantityCounterEntire.text.toString()) - 1).toString()
                }
                "half" -> {
                    quantityCounterHalf.text =
                        (Integer.parseInt(quantityCounterHalf.text.toString()) - 1).toString()
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
    }
}
