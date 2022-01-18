package com.lucascarlos.cineticket.view

import android.app.AlertDialog
import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
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
    private var selectedSeats = mutableListOf<String>()
    private lateinit var progressBar: ProgressBar
    private var room: String? = null
    private var seatsList: List<Seat> = emptyList()
    lateinit var seeAll: TextView

    override

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seat)

        recyclerSeats = findViewById(R.id.recycleSeats)

        val title = intent.getStringExtra("movieTitle")
        val posterUrl = intent.getStringExtra("posterUrl")
        room = intent.getStringExtra("roomNumber")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")

        val movieTitle: TextView = findViewById(R.id.movie_title)
        val movieRoom: TextView = findViewById(R.id.movie_room)
        val movieDate: TextView = findViewById(R.id.movie_date)
        val movieTime: TextView = findViewById(R.id.movie_time)
        val seatsSelected: TextView = findViewById(R.id.seats_selected)
        val btnContinue: Button = findViewById(R.id.btn_continue)

        seeAll = findViewById(R.id.see_all)
        progressBar = findViewById(R.id.progress_bar)

        val closeButton: ImageView = findViewById(R.id.close)

        movieTitle.text = title
        movieRoom.text = room
        movieDate.text = date
        movieTime.text = time

        getData()

        closeButton.setOnClickListener {
            this.finish()
        }

        seeAll.setOnClickListener {
            showSeeAllDialog()
        }

        btnContinue.setOnClickListener {
            val ticketData = Ticket(
                movieTitle = title,
                posterUrl = posterUrl,
                movieRoom = room,
                movieDate = date,
                movieTime = time,
                seats = selectedSeats
            )
            addTicket(ticketData)
        }

        val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val seat = intent.getStringExtra("selectedSeat")

                if (selectedSeats.contains(seat)) {
                    selectedSeats.remove(seat)
                } else {
                    seat?.let { selectedSeats.add(it) }
                }

                if (selectedSeats.size == 10) {
                    seeAll.visibility = View.VISIBLE
                    (selectedSeats.distinct()
                        .joinToString(separator = ",") + "...").also { seatsSelected.text = it }
                } else if (selectedSeats.size < 10) {
                    seeAll.visibility = View.GONE
                    seatsSelected.text = selectedSeats.distinct().joinToString(separator = ",")
                }
            }
        }

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mMessageReceiver, IntentFilter("message_subject_intent"))
    }

    private fun addTicket(ticketData: Ticket) {

        val retrofit = MyRetrofit.instance
        retrofit?.postTicketApi()?.addTicket(ticketData)?.enqueue(
            object : Callback<Ticket> {
                override fun onResponse(call: Call<Ticket>, response: Response<Ticket>) {
                    val response = response
                }

                override fun onFailure(call: Call<Ticket>, t: Throwable) {
                    Toast.makeText(this@ChooseSeat, t.message, Toast.LENGTH_LONG).show()
                    t.message?.let { Log.e("Err", it) }
                }
            }
        )
    }

    private fun showSeeAllDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.txt_selected_seats)
        builder.setMessage(selectedSeats.distinct().joinToString(separator = ", "))
        builder.setPositiveButton(
            "Ok"
        ) { _, _ ->
        }
        val alert = builder.create()
        alert.show()
    }

    private fun getData() {
        val call: Call<List<Seats>> =
            room?.let {
                MyRetrofit.instance?.seatsApi()?.getSeatsApi(it)
            } as Call<List<Seats>>
        call.enqueue(object : Callback<List<Seats>> {

            override fun onResponse(
                call: Call<List<Seats>>,
                response: Response<List<Seats>>
            ) {
                val apiResponse = response.body()?.toList() as List<Seats>

                for (i in apiResponse) {
                    seatsList = seatsList + i.seats
                }

                recyclerSeats.layoutManager = object : LinearLayoutManager(this@ChooseSeat) {
                    override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                        lp.height = height / seatsList.size
                        return true
                    }
                }

                val adapter = SeatsAdapter(this@ChooseSeat, seatsList)
                recyclerSeats.adapter = adapter
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Seats>>, t: Throwable) {
                Toast.makeText(this@ChooseSeat, t.message, Toast.LENGTH_LONG).show()
                t.message?.let { Log.e("Err", it) }
                progressBar.visibility = View.GONE
            }
        })
    }
}