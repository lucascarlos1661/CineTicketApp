package com.lucascarlos.cineticket.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.view.BuyTicket
import com.lucascarlos.cineticket.view.MovieDetails

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
        Log.e("teste", item.toString())
        holder.movieTitle.text = item.movieTitle
    }
}

class TicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
}

