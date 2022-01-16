package com.lucascarlos.cineticket.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R

class SeatsAdapter(
    private val context: Context, private val seatsList: List<Seats>,
) :
    RecyclerView.Adapter<SeatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatsViewHolder {
        return SeatsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.seats_item, parent, false)
        )
    }

    override fun getItemCount(): Int = seatsList.size

    override fun onBindViewHolder(holder: SeatsViewHolder, position: Int) {
        val item = seatsList[position]

        setSeatsItemRecycler(holder.recyclerSeat, item.seat.toList(), item.y)
    }

    private fun setSeatsItemRecycler(
        recyclerView: RecyclerView,
        seatsList: List<String>,
        y: String
    ) {
        val itemRecyclerAdapter = SeatAdapter(context, seatsList, y)

        recyclerView.layoutManager = object : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = width / seatsList.size
                return true
            }
        }
        recyclerView.adapter = itemRecyclerAdapter
    }
}

class SeatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val recyclerSeat: RecyclerView = itemView.findViewById(R.id.recyclerSeat)
}