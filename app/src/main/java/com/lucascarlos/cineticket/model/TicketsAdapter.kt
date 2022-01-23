package com.lucascarlos.cineticket.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.view.TicketDetails

class TicketsAdapter(
    private val context: Context,
    private val ticketsList: List<Ticket>,
) :
    RecyclerView.Adapter<TicketsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder =
        TicketsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ticket_item, parent, false)
        )

    override fun getItemCount(): Int = ticketsList.size

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val item = ticketsList[position]
        holder.movieTitle.text = item.movieTitle
        Glide.with(context).load(item.posterUrl).into(holder.moviePoster)
        holder.movieDate.text = item.movieDate
        holder.movieRoom.text = item.movieRoom
        holder.movieTime.text = item.movieTime
        holder.movieSeats.text = item.seats.joinToString(separator = ", ")

        holder.card.setOnClickListener {

            val ticketData = Ticket(
                movieTitle = item.movieTitle,
                posterUrl = item.posterUrl,
                movieRoom = item.movieRoom,
                movieDate = item.movieDate,
                movieTime = item.movieTime,
                seats = item.seats,
                seatType = SeatType(
                    item.seatType.entire,
                    item.seatType.half
                )
            )

            val gson = Gson()
            val jsonTicket = gson.toJson(ticketData)

            val intent = Intent(context, TicketDetails::class.java)
            intent.putExtra("ticket", jsonTicket)
            context.startActivity(intent)
        }
    }
}

class TicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val card: CardView = itemView.findViewById(R.id.card)
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
    val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)
    val movieDate: TextView = itemView.findViewById(R.id.movie_date)
    val movieRoom: TextView = itemView.findViewById(R.id.movie_room)
    val movieTime: TextView = itemView.findViewById(R.id.movie_time)
    val movieSeats: TextView = itemView.findViewById(R.id.movie_seats)
}

