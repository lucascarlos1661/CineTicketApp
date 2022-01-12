package com.lucascarlos.cineticket.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R

class SeatAdapter(private val context: Context, private val seatList: List<String>) :
    RecyclerView.Adapter<SeatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder =
        SeatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.seat_item, parent, false)
        )

    override fun getItemCount(): Int = seatList.size

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val item = seatList[position]
        holder.mySeat.text = item

        Log.e("Seat", item)
    }

}

class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mySeat: TextView = itemView.findViewById(R.id.mySeat)
}