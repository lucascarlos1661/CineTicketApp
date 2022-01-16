package com.lucascarlos.cineticket.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R


class SeatAdapter(
    private val context: Context,
    private val seatList: List<String>,
    private val y: String,
) :
    RecyclerView.Adapter<SeatViewHolder>() {

    private lateinit var selectedSeat: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {

        return SeatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.seat_item, parent, false)
        )
    }

    override fun getItemCount(): Int = seatList.size

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val item = seatList[position]

        if (item == "true") {
            holder.rectangleAvaliable.visibility = View.VISIBLE
        } else if (item == "false") {
            holder.rectangleNoAvaliable.visibility = View.VISIBLE
        }

        holder.rectangleAvaliable.setOnClickListener {
            holder.rectangleSelected.visibility = View.VISIBLE
            holder.rectangleAvaliable.visibility = View.GONE

            selectedSeat = "${y}${position + 1}"

            updateSelectedSeat()
        }

        holder.rectangleSelected.setOnClickListener{
            holder.rectangleSelected.visibility = View.GONE
            holder.rectangleAvaliable.visibility = View.VISIBLE

            selectedSeat = "${y}${position + 1}"
            updateSelectedSeat()
        }
    }

    private fun updateSelectedSeat() {
        val intent = Intent("message_subject_intent")
        intent.putExtra("selectedSeat", selectedSeat)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

}

class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val rectangle: ImageView = itemView.findViewById(R.id.rectangle)
    val rectangleAvaliable: ImageView = itemView.findViewById(R.id.rectangleAvailable)
    val rectangleNoAvaliable: ImageView = itemView.findViewById(R.id.rectangleNoAvailable)
    val rectangleSelected: ImageView = itemView.findViewById(R.id.rectangleSelected)
}